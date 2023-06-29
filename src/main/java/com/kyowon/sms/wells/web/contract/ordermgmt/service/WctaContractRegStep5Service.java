package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCreditCardApprovalDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbCreditCardApprovalDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbCreditCardApprovalService;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractDtlStatCdChService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractSettlementConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractSettelmentDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractSettlementMapper;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaTaxInvoiceInquiryMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.*;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.response.SaveResponse;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.sds.sflex.system.config.validation.BizAssert.isTrue;

@Slf4j
@Service
@RequiredArgsConstructor
public class WctaContractRegStep5Service {
    public static final String AG_DRM_DV_CD_CNTR = "03"; /* 동의식별코드 : 03 계약 */

    private final WctaContractSettlementMapper mapper;
    private final WctaTaxInvoiceInquiryMapper taxInvoiceMapper;
    private final WctaContractSettlementConverter converter;
    private final WctaContractRegService contractRegService;
    private final ZwdzWithdrawalService rveReqService;
    private final ZwdbCreditCardApprovalService paymentService;
    private final WctbContractDtlStatCdChService cntrStatChService;

    /**
     * 고객 접근 URL 로그인 화면을 위한 결제 최소 정보를 제공합니다.
     *
     * @param req 요청값
     * @return 결제 고객명을 포함한 최소한의 정보
     */
    public FindBasicInfoRes getCntrBasicInfo(SearchBasicInfoReq req) {
        WctaContractForAuthDvo dvo = getContractForAuth(req.cntrNo());
        return converter.mapWctaContractForAuthDvoToFindBasicInfoRes(dvo);
    }

    WctaContractForAuthDvo getContractForAuth(String cntrNo) {
        WctaContractForAuthDvo dvo = mapper.selectCntrBasicInfo(cntrNo)
            .orElseThrow(() -> new BizException("가능한 계약이 없습니다."));
        CtContractProgressStatus contractProgressStatus = CtContractProgressStatus.of(dvo.getCntrPrgsStatCd());
        CtContractProgressStatus[] allowed = {
            CtContractProgressStatus.WRTE_FSH,
            CtContractProgressStatus.STLM_STNB,
            CtContractProgressStatus.STLM_ING,
            CtContractProgressStatus.STLM_FSH
        };
        if (Arrays.stream(allowed).noneMatch((stat) -> stat.equals(contractProgressStatus))) {
            throw new BizException("결제 가능한 계약이 없습니다.");
        }
        return dvo;
    }

    /**
     * 고객 접근 URL 고객 확인 화면 인증 서비스
     * 화면에 들어오자 마자 계약 기본의 상태를 바꿔버린다.
     *
     * @param req 인증 요청
     * @return 인가값
     */
    @Transactional
    public Authorization authorize(AuthenticationReq req) {
        String cntrNo = req.cntrNo();
        boolean valid = getAuth(req);
        editContractProgressStatus(cntrNo, CtContractProgressStatus.STLM_ING);
        return new Authorization(valid, "");
    }

    boolean getAuth(AuthenticationReq req) {
        WctaContractForAuthDvo dvo = getContractForAuth(req.cntrNo());
        switch (CtCopnDvCd.of(dvo.getCopnDvCd())) {
            case INDIVIDUAL -> BizAssert.isTrue(Objects.equals(req.cntrCstBryyMmdd(), dvo.getCntrCstBryyMmdd()), "생년월일을 확인해주세요.");
            case COOPERATION -> BizAssert.isTrue(Objects.equals(req.bzrno(), dvo.getBzrno()), "사업자번호를 확인해주세요.");
        }
        return true;
    }

    @Transactional
    void editContractProgressStatus(String cntrNo, CtContractProgressStatus status) {
        log.debug("계약기본 update: {}, {}", cntrNo, status.getCode());
        int result = mapper.updateContractProgressStatus(cntrNo, status.getCode());
        isTrue(result == 1, "MSG_ALT_SVE_ERR");
        result = mapper.insertContractChHist(cntrNo);
        isTrue(result == 1, "MSG_ALT_SVE_ERR");
    }

    /**
     * 결제 동의 화면에서 필요한 결제 정보를 조회한다
     *
     * @param req 요청
     * @return 결재 기본 정보와 상품 정보들, 동의 목록 포함.
     */
    public FindContractForStlmRes getContractForSettlements(AuthenticationReq req) {
        /* TODO: getAuth check 로직 필요  */
        String cntrNo = req.cntrNo();
        WctaContractBasDvo contrctBasDvo = getContractBasForSettlements(cntrNo);

        List<WctaContractDtlDvo> productInfos = contractRegService.selectProductInfos(cntrNo);

        /* 주소지 통째로 꺼내서 맵 만들기 FIXME: 다중에 계약상세 정보에 조인해서 들고 오면 더 좋겠다.*/
        List<WctaContractAdrpcBasDvo> adrpcBasDvos = contractRegService.selectContractAdrpcBas(cntrNo);
        Map<String, WctaContractAdrpcBasDvo> adrpcPkMap = new HashMap<>();
        adrpcBasDvos.forEach(wctaContractAdrpcBasDvo -> adrpcPkMap.put(wctaContractAdrpcBasDvo.getCntrAdrpcId(), wctaContractAdrpcBasDvo));

        Set<String> cntrStlmIds = new HashSet<>();

        productInfos.forEach((dtl) -> {
            /* 설치 주소 */
            WctaContractAdrRelDvo contractAdrRelDvo = contractRegService.selectContractAdrRel(cntrNo, dtl.getCntrSn());
            String cntrAdrpcId = contractAdrRelDvo.getCntrAdrpcId();
            BizAssert.isTrue(adrpcPkMap.containsKey(cntrAdrpcId), "계약주소지가 정확하지 않습니다.");
            WctaContractAdrpcBasDvo adrpc = adrpcPkMap.get(contractAdrRelDvo.getCntrAdrpcId());
            dtl.setAdrpc(adrpc);

            /* 결제 관계*/
            List<WctaContractStlmRelDvo> stlmRels = contractRegService.selectContractStlmRels(dtl.getCntrNo(), dtl.getCntrSn());
            dtl.setStlmRels(stlmRels);
            stlmRels.forEach((stlmRel) -> cntrStlmIds.add(stlmRel.getCntrStlmId()));

        });

        List<WctaContractStlmBasDvo> stlms;
        BizAssert.isFalse(cntrStlmIds.isEmpty(), "해당 계약에 대해 생성된 결제가 없습니다.");
        stlms = getContractStlms(cntrStlmIds);

        /* 에듀는 배송지가 단 하나다. 계약자 주소지 정보면 충분하답니다. */
        WctaContractCstRelDvo cntrCstInfo = contractRegService.selectCntrtInfoByCstNo(contrctBasDvo.getCntrCstNo());

        /* TODO: 동의 내역은 변동 될 여지가 충분하다. */
        List<WctaAgreeItemDtlDvo> agIzs = new ArrayList<>();
        WctaAgreeItemDtlDvo tempAgreeItem = WctaAgreeItemDtlDvo.builder()
            .agAtcDvCd(CtAgAtcDvCd.AG_001.getCode())
            .agStatCd(CtAgStatCd.UNDEF.getCode())
            .mndtYn("Y")
            .build();
        WctaAgreeItemDtlDvo tempAgreeItem2 = WctaAgreeItemDtlDvo.builder()
            .agAtcDvCd(CtAgAtcDvCd.AG_002.getCode())
            .agStatCd(CtAgStatCd.UNDEF.getCode())
            .mndtYn("Y")
            .build();
        agIzs.add(tempAgreeItem);
        agIzs.add(tempAgreeItem2);

        /*파트너 정보*/
        WctaContractPrtnrRelDvo sellPrtnr = getSellPrtnrRel(cntrNo).orElse(null);

        return FindContractForStlmRes.builder()
            .base(contrctBasDvo)
            .agIzs(agIzs)
            .cntrCstInfo(cntrCstInfo)
            .dtls(productInfos)
            .stlms(stlms)
            .prtnr(sellPrtnr)
            .build();
    }

    Optional<WctaContractPrtnrRelDvo> getSellPrtnrRel(String cntrNo) {
        List<WctaContractPrtnrRelDvo> prtnrs = contractRegService.selectContractPrtnrRel(cntrNo);
        String CNTR_PRTNR_TP_CD_SELLER = "010";
        return prtnrs.stream()
            .filter((prtnr) -> Objects.equals(prtnr.getCntrPrtnrTpCd(), CNTR_PRTNR_TP_CD_SELLER))
            .findFirst();
    }

    WctaContractBasDvo getContractBasForSettlements(String cntrNo) {
        WctaContractBasDvo contractBasDvo = contractRegService.selectContractBas(cntrNo);

        //region 계약접수완료일시 확인
        LocalDate cntrRcpFshDt = parseDate(contractBasDvo.getCntrRcpFshDtm());

        boolean expired = cntrRcpFshDt.isBefore(LocalDate.now());
        if (expired) {
            editContractProgressStatus(cntrNo, CtContractProgressStatus.TEMP_STEP1);
            throw new BizException("가격 정보 재 조회 필요! 임시저장 상태로 변경 됩니다.");
            /* TODO: MSG 가격 정보 재 조회 필요! 임시저장 상태로 변경 됩니다. */
        }

    //endregion

        return contractBasDvo;
    }

    List<WctaContractStlmBasDvo> getContractStlms(Collection<String> pks) {
        return mapper.selectCntrStlms(pks);
    }

    WctaContractStlmBasDvo getCntrStlmByPk(String cntrStlmId) {
        return mapper.selectCntrStlmByPk(cntrStlmId)
            .orElseThrow(() -> new BizException("가능한 결제 정보가 없습니다."));
    }

    LocalDate parseDate(String dateTimeStr) {
        if (dateTimeStr.length() < 8) {
            throw new BizException("특정 날짜 값이 잘못 된 듯?");
        }
        String pattern = "yyyyMMdd";
        String cutoff = StringUtils.rightPad(dateTimeStr, pattern.length(), "0")
            .substring(0, pattern.length());
        return LocalDate.parse(cutoff, DateTimeFormatter.ofPattern(pattern));
    }

    @Transactional
    public SaveRes saveContractSettlements(SaveReq req) {
        /* FIXME: validate contract: 계약 기본 정보 조회 후, 상태 검사 후 튕겨 내기 */
        String cntrNo = req.cntrNo();
        getContractForAuth(cntrNo);

        /* 동의 정보 생성 */
        createAgreeInfos(cntrNo, req.agIzs());

        /* 확정 로직을 위한 임시 플래그*/
        boolean paid = true;

        /* 요청받은 계약결제기본 업데이트*/
        List<WctaContractStlmBasDvo> updateContractBasDvos = req.stlmBases();
        BizAssert.notNull(updateContractBasDvos, "요청 결제 정보가 없습니다.");
        for (WctaContractStlmBasDvo dvo : updateContractBasDvos) {
            /* FIXME: 우선 요청 받은 결제 정보가 있고, 입급유형타입과 결제번호만 검사합니다. */
            WctaContractStlmBasDvo WctaContractStlmBasDvo = getCntrStlmByPk(dvo.getCntrStlmId());
            BizAssert.isTrue(WctaContractStlmBasDvo.getDpTpCd().equals(dvo.getDpTpCd()), "결제가 상이합니다.");
            BizAssert.isTrue(WctaContractStlmBasDvo.getCntrNo().equals(cntrNo), "결제가 상이합니다.");

            /* 입금유형에 다라 분기 처리, TODO: 각 입금 유형에 따른 요청 validation 추가 할 것.*/
            CtDpTpCd dpTpCd = CtDpTpCd.of(WctaContractStlmBasDvo.getDpTpCd());
            switch (dpTpCd) {
                case IDV_RVE_VAC -> {
                    log.debug("계약결제기본-가상계좌 update: {}", dvo);
                    updateContractSettlement(dvo);
                    paid = false;
                    editContractProgressStatus(cntrNo, CtContractProgressStatus.STLM_FSH);
                }
                case CRDCD_AFTN, AC_AFTN -> {
                    log.debug("계약결제기본-카드자동이체 update: {}", dvo);
                    updateContractSettlement(dvo);
                }
                case IDV_RVE_CRDCD, YMDR_CARD_VCH -> {
                    log.debug("계약결제기본-신용카드 update: {}", dvo);
                    updateContractSettlement(dvo);
                }
                default -> throw new BizException("지원하지 않는 입금 유형입니다.");
            }
        }

        /* 계상상태코드 결제기본생성완료*/
        List<WctaContractAdrpcBasDvo> adrpcs = req.adrpcs();
        for (WctaContractAdrpcBasDvo adrpc : adrpcs) {
            editContractAdrpcBas(adrpc);
        }

        if (paid) {
            confirmContract(cntrNo);
        }

        /* 20230627 세금계산서 발행 추가.. saveTaxInvoice? */
        putTaxInvoice(cntrNo);

        return SaveRes.builder()
            .result(true)
            .build();
    }

    @Transactional
    void putTaxInvoice(String cntrNo) {
        WctaTaxInvoiceInquiryDvo taxInvoiceInquiryDvo = mapper.selectBasTaxInvoiceInquiry(cntrNo);
        /* 해당 dvo 에 dec anno 없음. 자체적으로 풀어서 다시 암호화 해서 넣기 */
        taxInvoiceInquiryDvo.setExnoEncr(DbEncUtil.dec(taxInvoiceInquiryDvo.getExnoEncr()));
        List<WctaContractDtlDvo> dtlDvos = contractRegService.selectContractDtl(cntrNo);

        dtlDvos.forEach(dtlDvo -> {
            CtSellTpCd sellTpCd = CtSellTpCd.of(dtlDvo.getSellTpCd());
            CtTxinvPdDvCd ctTxinvPdDvCd = CtTxinvPdDvCd.of(sellTpCd).orElse(null);
            if (ctTxinvPdDvCd != null && "Y".equals(dtlDvo.getTxinvPblOjYn())) {
                taxInvoiceInquiryDvo.setCntrSn(dtlDvo.getCntrSn());
                taxInvoiceInquiryDvo.setTxinvPdDvCd(ctTxinvPdDvCd.getCode());
                taxInvoiceInquiryDvo.setTxinvPblDvCd(CtTxinvPblDvCd.of(sellTpCd).getCode());
                taxInvoiceMapper.updateTaxInvoiceInquiry(taxInvoiceInquiryDvo);
                taxInvoiceInquiryDvo.setMexnoEncr(DbEncUtil.dec(taxInvoiceInquiryDvo.getMexnoEncr()));
                taxInvoiceMapper.insertTaxInvoiceReceiptBaseHist(taxInvoiceInquiryDvo);
            }
        });
    }

    @Transactional
    void createAgreeInfos(String cntrNo, List<WctaAgreeItemDtlDvo> agIzs) {
        isTrue(agIzs.size() > 0, "동의 내역이 없습니다.");


        WctaAgreeItemDvo agreeItemDvo = new WctaAgreeItemDvo();
        String cstAgId = mapper.selectMaxCntrCstAgId();
        agreeItemDvo.setCstAgId(cstAgId);
        agreeItemDvo.setAgDrmDvCd(AG_DRM_DV_CD_CNTR); /* 동의식별코드 : 03 계약 */
        agreeItemDvo.setAgDrmRefkVal(cntrNo); /* 동의식별 참조 키값은 계약번호*/
        agreeItemDvo.setCntcPrtnrNo(getSellPrtnrRel(cntrNo)
            .map(WctaContractPrtnrRelDvo::getPrtnrNo)
            .orElse(null));
        createAgreeItem(agreeItemDvo);

        for (WctaAgreeItemDtlDvo agreeItemDtlDvo : agIzs) {
            CtAgAtcDvCd agAtcDvCd = CtAgAtcDvCd.of(agreeItemDtlDvo.getAgAtcDvCd());
            CtAgStatCd agStatCd = CtAgStatCd.of(agreeItemDtlDvo.getAgStatCd());
            boolean required = "Y".equals(agreeItemDtlDvo.getMndtYn());
            isTrue(required && agStatCd == CtAgStatCd.AG, agAtcDvCd.getCodeName() + "항목에 동의해주세요.");

            agreeItemDtlDvo.setCstAgId(cstAgId);
            createAgreeItemDtl(agreeItemDtlDvo);
        }
    }

    @Transactional
    void createAgreeItem(WctaAgreeItemDvo dvo) {
        int processCount = mapper.insertAgreeItem(dvo);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
    }

    @Transactional
    void createAgreeItemDtl(WctaAgreeItemDtlDvo dvo) {
        int processCount = mapper.insertAgreeItemDtl(dvo);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
    }

    @Transactional
    void updateContractSettlement(WctaContractStlmBasDvo dvo) {
        log.debug("결제 기본 update: {}", dvo);
        int result = mapper.updateContractSettlement(dvo);
        isTrue(result == 1, "MSG_ALT_SVE_ERR");
        result = mapper.insertContractSettlementChHist(dvo.getCntrStlmId());
        isTrue(result == 1, "MSG_ALT_SVE_ERR");
    }

    @Transactional
    void confirmContract(String cntrNo) {
        editContractProgressStatus(cntrNo, CtContractProgressStatus.CNFM);
//         계약상세상태코드 변경
        List<WctaContractDtlDvo> cntrDtls = contractRegService.selectContractDtl(cntrNo);
        for (WctaContractDtlDvo dtl : cntrDtls) {
            WctbContractDtlStatCdChDvo dtlStatDvo = new WctbContractDtlStatCdChDvo();
            dtlStatDvo.setCntrNo(cntrNo);
            dtlStatDvo.setCntrSn(dtl.getCntrSn().toString());
            dtlStatDvo.setCntrDtlStatCd("101"); // 정상!
            try {
                cntrStatChService.editContractDtlStatCdCh(dtlStatDvo);
            } catch (Exception e) {
                throw new BizException("계약 상태 변경 실패");
            }
        }
    }

    @Transactional
    void editContractAdrpcBas(WctaContractAdrpcBasDvo updateDvo) {
        /*
         * EDU는 계약시 다건이더라도 배송지는 같다.
         * 따라서, 결제시 입력받은 주소ID와 계약주소관계 테이블의 첫번째 데이터로 찾은 계약주소지기본 테이블의 주소ID가 다른 경우에,
         * 기존 계약주소관계 테이블 및 계약주소지기본의 데이터를 삭제하고 (계약단계 이므로 물리삭제)
         * 계약주소지기본에 입력된 데이터를 저장하고,
         * (물리 삭제 + 생성 이면 그냥 수정... 아닌가요..?)
         * 계약주소관계 테이블을 상세일련번호 개수만큼 생성하여 계약주소지ID 를 매핑하여 저장한다.
         * */
        WctaContractAdrpcBasDvo contractAdrpcBasDvo = getContractAdrpcBas(updateDvo.getCntrAdrpcId());

        contractAdrpcBasDvo.setAdr(updateDvo.getAdr());
        contractAdrpcBasDvo.setAdrDtl(updateDvo.getAdrDtl());
        contractAdrpcBasDvo.setAdrId(updateDvo.getAdrId());
        contractAdrpcBasDvo.setZip(updateDvo.getZip());
        contractAdrpcBasDvo.setAdrDvCd(updateDvo.getAdrDvCd());
        contractAdrpcBasDvo.setCralLocaraTno(updateDvo.getCralLocaraTno());
        contractAdrpcBasDvo.setMexnoEncr(updateDvo.getMexnoEncr());
        contractAdrpcBasDvo.setCralIdvTno(updateDvo.getCralIdvTno());
        contractAdrpcBasDvo.setRcgvpKnm(updateDvo.getRcgvpKnm());

        updateAdrpcBas(contractAdrpcBasDvo);
    }

    @Transactional
    void updateAdrpcBas(WctaContractAdrpcBasDvo dvo) {
        int result = mapper.updateContractAdrpcBas(dvo);
        isTrue(result == 1, "MSG_ALT_SVE_ERR");
    }

    WctaContractAdrpcBasDvo getContractAdrpcBas(String cntrAdrpcId) {
        return mapper.selectContractAdrpcBasByPk(cntrAdrpcId).orElseThrow(() -> new BizException("주소지가 없는 계약건이 있습니다."));
    }

    /**
     * 수납요청 기본 및 상세 데이터 생성 후 카드결제 승인 API 호출
     * 고객번호
     * 수납요청방식코드 (대면/비대면)
     * 수납요청파트너번호
     * 수납요청파트너조직유형코드
     *
     * @param req : 수납 요청 정보
     * @return SaveResponse
     */
    @Transactional
    public List<CreditRes> requestCreditCardApproval(CreditReq req) {
        // 신용카드 금융기관코드 조회
        String fnitCd = mapper.selectFnitCdInfo(req.crcdnoEncr());
        BizAssert.notNull(fnitCd, "조회되지 않은 금융기관 카드번호입니다");

        WctaContractStlmBasDvo contractStlmBasDvo = getCntrStlmByPk(req.cntrStlmId());
        List<WctaContractStlmRelDvo> stlmRelDvos = getContractStlmRels(req.cntrStlmId()); /* 아마 1 건 일거다.*/

        WctaSettlementCntrBasDvo bas = mapper.selectContractBasic(contractStlmBasDvo.getCntrNo());

        long totSmltAmt = stlmRelDvos.stream()
            .mapToLong(WctaContractStlmRelDvo::getStlmAmt)
            .sum();

        // 수납요청 기본 데이터 생성 후 수납요청번호 리턴
        ZwdzWithdrawalReceiveAskDvo withdrawalReceiveAskDvo = createReceiveAsk(bas, totSmltAmt);

        setCreditInfosToWithdrawalReceiveAsk(req, fnitCd, withdrawalReceiveAskDvo);

        stlmRelDvos.forEach(dvo -> {
            withdrawalReceiveAskDvo.setDepositTypeCode(dvo.getDpTpCd());
            withdrawalReceiveAskDvo.setContractNumber(dvo.getDtlCntrNo());
            withdrawalReceiveAskDvo.setContractSerialNumber(dvo.getDtlCntrSn().toString());
            rveReqService.createReceiveAskDetail(withdrawalReceiveAskDvo);
        });
        rveReqService.createReceiveAskDetailHistory(withdrawalReceiveAskDvo);

        List<ZwdbCreditCardApprovalDto.SaveReq> creditCardApprovalSaveReqs =  stlmRelDvos
            .stream()
            .map((dvo) -> getCreditCardApprovalSaveReq(withdrawalReceiveAskDvo, dvo.getStlmAmt()))
            .toList();
        SaveResponse response = paymentService.saveCreditCardApproval(creditCardApprovalSaveReqs);
        /* 이거 이렇게 준단다. */
        @SuppressWarnings("unchecked") List<ZwdbCreditCardApprovalDvo> responses = (List<ZwdbCreditCardApprovalDvo>) response.getData();
        BizAssert.isTrue(responses.size() > 0 && responses.get(0).getErrorCd().equals("S"), "신용승인 요청 실패");
        return responses.stream().map((dvo) -> CreditRes.builder()
            .aprNo(dvo.getAprNo())
            .cdcoCd(fnitCd)/* FIXME: 결제 쪽 api 스펙 변경시 수정할 것 */
            .fnitAprFshDtm(dvo.getStlmDtm())
            .build()).toList();
    }

    List<WctaContractStlmRelDvo> getContractStlmRels(String cntrStlmId) {
        return mapper.selectContractStlmRels(cntrStlmId);
    }

    @Transactional
    ZwdzWithdrawalReceiveAskDvo createReceiveAsk(
        WctaSettlementCntrBasDvo bas,
        long stmlAmt
    ) {
        ZwdzWithdrawalReceiveAskDvo receiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
        receiveAskDvo.setKyowonGroupCompanyCd("2000");
        receiveAskDvo.setCustomNumber(bas.getCstNo());
        receiveAskDvo.setReceiveAskAmount(Long.toString(stmlAmt));
        receiveAskDvo.setReceiveAskStatusCode("01");
        receiveAskDvo.setReceiveAskDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        receiveAskDvo.setRveAkMthdCd(bas.getRveAkMthdCd());
        receiveAskDvo.setRveAkPhCd("01");
        receiveAskDvo.setRvePrtnrOgTpCd(bas.getRveAkPrtnrOgTpCd());
        receiveAskDvo.setRvePrtnrNo(bas.getRveAkPrtnrNo());
        receiveAskDvo.setReceiveCompanyCd("2000");

        // 수납요청 기본 데이터 생성 후 수납요청번호 리턴
        String receiveAskNumber = rveReqService.createReceiveAskBase(receiveAskDvo);
        log.debug("수납요청번호: {}", receiveAskNumber);
        receiveAskDvo.setReceiveAskNumber(receiveAskNumber);
        receiveAskDvo.setCrdcdBryyMmdd(CtCopnDvCd.of(bas.getCopnDvCd()) == CtCopnDvCd.INDIVIDUAL ? bas.getBryyMmdd() : "");
        receiveAskDvo.setCrdcdBzrno(CtCopnDvCd.of(bas.getCopnDvCd()) == CtCopnDvCd.COOPERATION ? bas.getBzrno() : "");
        return receiveAskDvo;
    }

    private static void setCreditInfosToWithdrawalReceiveAsk(CreditReq req, String fnitCd, ZwdzWithdrawalReceiveAskDvo withdrawalReceiveAskDvo) {
        withdrawalReceiveAskDvo.setDepositDivideCode("1"); // 입금구분코드 - 입금
        withdrawalReceiveAskDvo.setDepositMeansCode("02"); // 입금수단코드 - 신용카드
        withdrawalReceiveAskDvo.setReceiveStatusCode("02"); // 수납상태코드 - 수납요청
        withdrawalReceiveAskDvo.setFinancialInstitutionCd(fnitCd); // 금융기관코드
        withdrawalReceiveAskDvo.setAccountOwnerName(req.owrKnm()); // 카드주
        withdrawalReceiveAskDvo.setCreditCardNumberEncr(req.crcdnoEncr()); // 카드번호암호화
        withdrawalReceiveAskDvo.setCreditCardExpireDate(req.cardExpdtYm());
        withdrawalReceiveAskDvo.setCreditCardIstmMcn(req.istmMcn());
        withdrawalReceiveAskDvo.setReceiveDivideCode("01"); //수납구분코드 계약(청약)금
    }

    private static ZwdbCreditCardApprovalDto.SaveReq getCreditCardApprovalSaveReq(ZwdzWithdrawalReceiveAskDvo withdrawalReceiveAskDvo, long stlmAmt) {
        String crcdnoEncr = withdrawalReceiveAskDvo.getCreditCardNumberEncr();
        return new ZwdbCreditCardApprovalDto.SaveReq(
            "2000",
            "",
            "",
            withdrawalReceiveAskDvo.getReceiveAskNumber(),
            withdrawalReceiveAskDvo.getCustomNumber(),
            withdrawalReceiveAskDvo.getFinancialInstitutionCd(),
            crcdnoEncr.substring(0, 4),
            crcdnoEncr.substring(5, 8),
            crcdnoEncr.substring(8, 12),
            crcdnoEncr.substring(12),
            crcdnoEncr,
            withdrawalReceiveAskDvo.getCreditCardExpireDate(),
            withdrawalReceiveAskDvo.getAccountOwnerName(),
            withdrawalReceiveAskDvo.getCrdcdBryyMmdd(),
            withdrawalReceiveAskDvo.getCrdcdBzrno(), //  bas.getCopnDvCd() == "2" ? bas.getBzrno() : "",
            "N", // TODO: 소득공제여부
            withdrawalReceiveAskDvo.getCreditCardIstmMcn(),
            Long.toString(stlmAmt),
            "",
            withdrawalReceiveAskDvo.getRveAkMthdCd(),
            "01",
            withdrawalReceiveAskDvo.getRvePrtnrOgTpCd(),
            withdrawalReceiveAskDvo.getRvePrtnrNo(),
            "1", // TODO: 법인카드를 구분?
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }
}
