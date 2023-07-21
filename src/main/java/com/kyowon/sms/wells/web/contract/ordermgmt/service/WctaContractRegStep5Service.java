package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.ZwdaCardNumberEffectivenessCheckReqDvo;
import com.kyowon.sms.common.web.withdrawal.bilfnt.service.ZwdaKiccReceiveProcessService;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCreditCardApprovalDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbVirtualAccountIsMgtDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbCreditCardApprovalDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdzWithdrawalVirtualAccountIssueDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbCreditCardApprovalService;
import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbVirtualAccountIsMgtService;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractDtlStatCdChService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractSettlementConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractSettelmentDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractSettlementMapper;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaTaxInvoiceInquiryMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.*;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.response.SaveResponse;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.sds.sflex.system.config.validation.BizAssert.isTrue;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WctaContractRegStep5Service {
    public static final String AG_DRM_DV_CD_CNTR = "03"; /* 동의식별코드 : 03 계약 */

    private final WctaContractSettlementMapper mapper;
    private final WctaTaxInvoiceInquiryMapper taxInvoiceMapper;
    private final WctaContractSettlementConverter converter;
    private final WctaContractRegService contractRegService;
    private final ZwdzWithdrawalService rveReqService;
    private final ZwdbCreditCardApprovalService paymentService;
    private final ZwdbVirtualAccountIsMgtService virtualAccountIsMgtService;
    private final ZwdaKiccReceiveProcessService KiccReceiveService;

    private final WctbContractDtlStatCdChService cntrStatChService;
    private final WctzHistoryService historyService;

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
        boolean valid = getAuth(req);
        return new Authorization(valid, "");
    }

    boolean getAuth(AuthenticationReq req) {
        WctaContractForAuthDvo dvo = getContractForAuth(req.cntrNo());
        switch (CtCopnDvCd.of(dvo.getCopnDvCd())) {
            case INDIVIDUAL ->
                BizAssert.isTrue(Objects.equals(req.cntrCstBryyMmdd(), dvo.getCntrCstBryyMmdd()), "생년월일을 확인해주세요.");
            case COOPERATION -> BizAssert.isTrue(Objects.equals(req.bzrno(), dvo.getBzrno()), "사업자번호를 확인해주세요.");
        }
        return true;
    }

    @Transactional
    void editContractProgressStatus(String cntrNo, CtContractProgressStatus status) {
        log.debug("계약기본 update: {}, {}", cntrNo, status.getCode());

        /* 변경하는 값이 기존과 같으면 넘어간다. */
        WctaContractBasDvo basDvo = contractRegService.selectContractBas(cntrNo);
        if (status.equals(CtContractProgressStatus.of(basDvo.getCntrPrgsStatCd()))) {
            return;
        }

        int result = mapper.updateContractProgressStatus(cntrNo, status.getCode());
        isTrue(result == 1, "MSG_ALT_SVE_ERR");

        WctzCntrBasicChangeHistDvo histDvo = historyService.getContractBasicChangeHistory(cntrNo);

        if (!histDvo.getHistStrtDtm().equals(DateUtil.todayNnow())) {
            historyService.expireContractBasicChangeHistory(cntrNo); /* pk 중복이 가능하다. 충분히. 발생 하지 않는다면 기존 값을 expire 한다.*/
        }
        result = mapper.upsertContractChHist(cntrNo);

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


        WctaContractBasDvo contrctBasDvo = null;
        try {
            contrctBasDvo = getContractBasForSettlements(cntrNo);
        } catch (BizException e) {
            editContractProgressStatus(cntrNo, CtContractProgressStatus.TEMP_STEP1);
            throw e;
        }

        editContractProgressStatus(cntrNo, CtContractProgressStatus.STLM_ING);

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
                    /* FIXME: 사실 여기서 카드 결제 요청을 날려야 하는게 아닐까? */
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
                taxInvoiceInquiryDvo.setExnoEncr(DbEncUtil.dec(taxInvoiceInquiryDvo.getExnoEncr()));
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
    public void confirmContract(String cntrNo) {
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

    public String getFnitCd(String creditCardNo) {
        String fnitCd = mapper.selectFnitCdInfo(creditCardNo);
        BizAssert.notNull(fnitCd, "조회되지 않은 금융기관 카드번호입니다");
        BizAssert.isFalse(fnitCd.length() == 0, "조회되지 않은 금융기관 카드번호입니다");
        return fnitCd;
    }

    /**
     * 수납요청 기본 및 상세 데이터 생성 후 카드결제 승인 API 호출
     *
     * @param req : 수납 요청 정보
     * @return SaveResponse
     */
    @Transactional
    public CreditRes requestCreditCardApproval(CreditReq req) {
        WctaContractStlmBasDvo contractStlmBasDvo = getCntrStlmByPk(req.cntrStlmId());
        List<WctaContractStlmRelDvo> stlmRelDvos = getContractStlmRels(req.cntrStlmId()); /* 아마 1 건 일거다.*/
        long totStlmAmt = stlmRelDvos.stream()
            .mapToLong(WctaContractStlmRelDvo::getStlmAmt)
            .sum();
        WctaContractForRveAkDvo contractForRveAkInfo = mapper.selectContractForRveAk(contractStlmBasDvo.getCntrNo());

        /* 수납 요청 기본 생성 */
        ZwdzWithdrawalReceiveAskDvo receiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
        receiveAskDvo.setKyowonGroupCompanyCd(CtCoCd.KYOWON_PROPERTY.getCode());
        receiveAskDvo.setCustomNumber(contractForRveAkInfo.getCntrCstNo());
        receiveAskDvo.setRveAkMthdCd(contractForRveAkInfo.getCstStlmInMthCd());
        receiveAskDvo.setRveAkPhCd(CtRvePhCd.SELL_CARD.getCode());
        receiveAskDvo.setRvePrtnrOgTpCd(contractForRveAkInfo.getSellOgTpCd());
        receiveAskDvo.setRvePrtnrNo(contractForRveAkInfo.getSellPrtnrNo());
        receiveAskDvo.setReceiveAskAmount(Long.toString(totStlmAmt));
        receiveAskDvo.setReceiveAskDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        receiveAskDvo.setReceiveAskStatusCode(CtRveAkStatCd.RECEIPT.getCode());
        receiveAskDvo.setReceiveCompanyCd(CtCoCd.KYOWON_PROPERTY.getCode());

        String receiveAskNumber = rveReqService.createReceiveAskBase(receiveAskDvo);

        /* 수납 요청 상세 생성 */
        receiveAskDvo.setReceiveAskNumber(receiveAskNumber);

        receiveAskDvo.setDepositDivideCode(CtDpDvCd.DEPOSIT.getCode());
        receiveAskDvo.setReceiveStatusCode(CtRveStatCd.REQUEST.getCode());

        receiveAskDvo.setAccountOwnerName(req.owrKnm());
        receiveAskDvo.setFinancialInstitutionCd(req.fnitCd());
        receiveAskDvo.setCreditCardNumberEncr(req.crcdnoEncr());
        receiveAskDvo.setCreditCardExpireDate(req.cardExpdtYm());
        receiveAskDvo.setCreditCardIstmMcn(req.istmMcn());

        /* TODO 올바르지 않은 정보입니다. check requred. */
        // receiveAskDvo.setCrdcdBzrno(contractForRveAkInfo.getBzrno());
        // receiveAskDvo.setCrdcdBryyMmdd(contractForRveAkInfo.getBryyMmdd());
        // receiveAskDvo.setCrdcdCopnDvCd(contractForRveAkInfo.getCopnDvCd());

        List<ZwdzWithdrawalReceiveAskDvo> receiveDetailAskDvos = stlmRelDvos.stream()
            .map(stlmRelDvo -> {
                ZwdzWithdrawalReceiveAskDvo receiveDetailAskDvo = new ZwdzWithdrawalReceiveAskDvo();
                try {
                    BeanUtils.copyProperties(receiveDetailAskDvo, receiveAskDvo);
                } catch (Exception e) {
                    throw new BizException("IGNORE");
                }
                CtDpTpCd dpTpCd = CtDpTpCd.of(stlmRelDvo.getDpTpCd());
                CtRveDvCd rveDvCd = CtRveDvCd.of(stlmRelDvo.getRveDvCd());
                receiveDetailAskDvo.setDepositTypeCode(dpTpCd.getCode());
                receiveDetailAskDvo.setDepositMeansCode(dpTpCd.getDpMesCd());
                receiveDetailAskDvo.setReceiveDivideCode(rveDvCd.getCode());
                receiveDetailAskDvo.setContractNumber(stlmRelDvo.getDtlCntrNo());
                receiveDetailAskDvo.setContractSerialNumber(stlmRelDvo.getDtlCntrSn().toString());
                receiveDetailAskDvo.setReceiveAskAmount(stlmRelDvo.getStlmAmt().toString());
                return receiveDetailAskDvo;
            }).toList();

        receiveDetailAskDvos.forEach(rveReqService::createReceiveAskDetail);
        rveReqService.createReceiveAskDetailHistory(receiveAskDvo);

        String aprNo = resolveReceiveAskByCardApproval(receiveAskNumber);

        return CreditRes.builder()
            .aprNo(aprNo)
            .build();

        /*
        WctaContractStlmBasDvo updateApprovalStlmBasDvo = new WctaContractStlmBasDvo();

        SaveResponse saveResponse = paymentService.saveCreditCardApproval(List.of(getCreditCardApprovalSaveReq(receiveAskDvo)));
        @SuppressWarnings("unchecked") List<ZwdbCreditCardApprovalDvo> responses = (List<ZwdbCreditCardApprovalDvo>) saveResponse.getData();

        if (CollectionUtils.isEmpty(responses)) {
            updateApprovalStlmBasDvo.setFnitAprRsCd(CtFnitAprRsCd.ERROR.getCode());
            updateContractSettlement(updateApprovalStlmBasDvo);
            throw new BizException("신용승인 요청 실패");
        }

        ZwdbCreditCardApprovalDvo cardApprovalDvo = responses.get(0);

        if (!cardApprovalDvo.getErrorCd().equals("S")) {
            updateApprovalStlmBasDvo.setFnitAprRsCd(CtFnitAprRsCd.ERROR.getCode());
            updateContractSettlement(updateApprovalStlmBasDvo);
            throw new BizException("신용승인 요청 실패");
        }

        updateApprovalStlmBasDvo.setFnitAprRsCd(CtFnitAprRsCd.APPROVAL.getCode());
        updateApprovalStlmBasDvo.setFnitAprFshDtm(cardApprovalDvo.getStlmDtm());
        updateApprovalStlmBasDvo.setCardFntImpsCd(cardApprovalDvo.getAprCd());
        updateContractSettlement(updateApprovalStlmBasDvo);

        return CreditRes.builder()
            .aprNo(cardApprovalDvo.getAprNo())
            .fnitAprRsCd(CtFnitAprRsCd.APPROVAL.getCode())
            .fnitAprFshDtm(cardApprovalDvo.getStlmDtm())
            .build();
        */
    }

    /**
     * 저장된 수납 요청으로 부터 카드 승인 요청을 하고,
     * 결과를 계약기본을 업데이트 하고,
     * 성공시 승인 번호를 리턴합니댜.
     *
     * @param receiveAskNumber - 수납요청을 조회하기 위한 key
     * @return aprNo : 승인번호
     */
    @Transactional
    String resolveReceiveAskByCardApproval(String receiveAskNumber) {
        WctaContractStlmBasDvo updateApprovalStlmBasDvo = new WctaContractStlmBasDvo();

        try {
            ZwdzWithdrawalReceiveAskDvo receiveAskDvo = mapper.getReceiveAskByPk(receiveAskNumber).orElseThrow(
                () -> new BizException("수납 요청이 필요합니다.")
            );

            String crcdnoEncr = receiveAskDvo.getCreditCardNumberEncr();

            SaveResponse saveResponse = paymentService.saveCreditCardApproval(List.of(new ZwdbCreditCardApprovalDto.SaveReq(
                receiveAskDvo.getKyowonGroupCompanyCd(),
                "",
                "",
                receiveAskDvo.getReceiveAskNumber(),
                receiveAskDvo.getCustomNumber(),
                receiveAskDvo.getFinancialInstitutionCd(),
                crcdnoEncr.substring(0, 4),
                crcdnoEncr.substring(4, 8),
                crcdnoEncr.substring(8, 12),
                crcdnoEncr.substring(12),
                null,
                receiveAskDvo.getCreditCardExpireDate(),
                receiveAskDvo.getAccountOwnerName(),
                receiveAskDvo.getCrdcdBryyMmdd(),
                receiveAskDvo.getCrdcdBzrno(), //  bas.getCopnDvCd() == "2" ? bas.getBzrno() : "",
                "N", // TODO: 소득공제여부
                receiveAskDvo.getCreditCardIstmMcn(),
                receiveAskDvo.getReceiveAskAmount(),
                "",
                receiveAskDvo.getRveAkMthdCd(),
                receiveAskDvo.getRveAkPhCd(),
                receiveAskDvo.getRvePrtnrOgTpCd(),
                receiveAskDvo.getRvePrtnrNo(),
                "1", // TODO: 법인카드를 구분?
                null)));
            @SuppressWarnings("unchecked") List<ZwdbCreditCardApprovalDvo> responses = (List<ZwdbCreditCardApprovalDvo>) saveResponse.getData();

            BizAssert.notEmpty(responses, "신용승인 요청 실패");

            ZwdbCreditCardApprovalDvo cardApprovalDvo = responses.get(0);

            BizAssert.isTrue(cardApprovalDvo.getErrorCd().equals("S"), "신용승인 요청 실패");

            updateApprovalStlmBasDvo.setFnitAprRsCd(CtFnitAprRsCd.APPROVAL.getCode());
            updateApprovalStlmBasDvo.setFnitAprFshDtm(cardApprovalDvo.getStlmDtm());
            updateApprovalStlmBasDvo.setCardFntImpsCd(cardApprovalDvo.getAprCd());
            return cardApprovalDvo.getAprNo();
        } catch (RuntimeException e) {
            updateApprovalStlmBasDvo.setFnitAprRsCd(CtFnitAprRsCd.ERROR.getCode());
            if (e instanceof BizException) {
                throw e;
            } else {
                throw new BizException("신용승인 요청 실패: " + e.getMessage());
            }
        } finally {
            updateContractSettlement(updateApprovalStlmBasDvo);
        }
    }

    /**
     * 가상계좌 발급 요청
     */
    @Transactional
    public VacIsRveAskRes requestVacIsRveAsk(VacIsRveAskReq req) {
        WctaContractStlmBasDvo contractStlmBasDvo = getCntrStlmByPk(req.cntrStlmId());
        List<WctaContractStlmRelDvo> stlmRelDvos = getContractStlmRels(req.cntrStlmId()); /* 아마 1 건 일거다.*/
        long totStlmAmt = stlmRelDvos.stream()
            .mapToLong(WctaContractStlmRelDvo::getStlmAmt)
            .sum();
        WctaContractForRveAkDvo contractForRveAkInfo = mapper.selectContractForRveAk(contractStlmBasDvo.getCntrNo());

        List<ZwdbVirtualAccountIsMgtDto.SaveContractDetailReq> contracts = stlmRelDvos.stream()
            .map((stlmRelDvo -> {
                CtRveDvCd rveDvCd = CtRveDvCd.of(stlmRelDvo.getRveDvCd());
                return new ZwdbVirtualAccountIsMgtDto.SaveContractDetailReq(
                    stlmRelDvo.getDtlCntrNo(), // cntrNo
                    Integer.toString(stlmRelDvo.getDtlCntrSn()), // cntrSn
                    null, // vacIsId
                    null, // vacIsDtlSn
                    null, // virtualAccount
                    Long.toString(stlmRelDvo.getStlmAmt()), // rveAkAmt
                    CtCoCd.KYOWON_PROPERTY.getCode(), // kwGrpCoCd
                    null, // rveAkNo
                    null, // rveAkSn
                    contractForRveAkInfo.getCopnDvCd(), // indvCrpCntrDvCd
                    null, // pdDvCd
                    null, // pdCd
                    null, // rveAkOjDrmNo1
                    null, // rveAkOjDrmNo2
                    rveDvCd.getCode(), // rveDvCd
                    contractForRveAkInfo.getCstKnm(), // vacIsCstNm
                    req.bnkCd() // bankCd
                );
            })).toList();

        String vncoDvCd = "003"; // KICC 만? -> 맞다. 일회성 가상 계좌는 KICC 에서 발급 받는다고 합니다.

        ZwdbVirtualAccountIsMgtDto.SaveReq saveReq = new ZwdbVirtualAccountIsMgtDto.SaveReq(
            CtCoCd.KYOWON_PROPERTY.getCode(), /*kwGrpCoCd*/
            null, /*rveAkNo*/
            null, /*virtualAccount*/
            "01", /*acountUsePeriod 일회성 - 01*/
            "01", /*acountUseGb 고객 - 01*/
            req.bnkCd(), /*bankCd*/
            vncoDvCd, /*vacVanCd KICC - 003*/
            contractForRveAkInfo.getCntrCstNo(), /*itgCstNo*/
            contractForRveAkInfo.getCstKnm(), /*cstKnm*/
            Long.toString(totStlmAmt), /*rveAkAmt*/
            "1", /*vacDpCndtCd 입금액=설정액 - 1*/
            "0", /*vacDpTmsCd 1회만 입금 - 0 */
            "1", /*vacIsDvCd 고객 - 1*/
            "1", /*virtualAccountDivide 고객 - 1*/
            contractForRveAkInfo.getSellOgTpCd(), /*vacIzPrtnrOgTpCd*/
            contractForRveAkInfo.getSellPrtnrNo(), /*vacIzPrtnrNo*/
            contractForRveAkInfo.getCstStlmInMthCd(), /*rveAkMthdCd*/
            CtRvePhCd.SELL_AC.getCode(),
            contractForRveAkInfo.getSellOgTpCd(), /*rvePrtnrOgTpCd*/
            contractForRveAkInfo.getSellPrtnrNo(), /*rvePrtnrNo*/
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), /*fnlMdfcDtm*/
            null, /*vacStatCd*/
            contracts
        );

        ZwdzWithdrawalVirtualAccountIssueDvo responseData;
        try {
            responseData = (ZwdzWithdrawalVirtualAccountIssueDvo) virtualAccountIsMgtService.saveVirtualAccountIssue(saveReq).getData();
            BizAssert.hasText(responseData.getVacNo(), "가상계좌 생성에 실패했습니다.");
            BizAssert.hasText(responseData.getVacAcownNm(), "가상계좌 생성에 실패했습니다.");
            BizAssert.hasText(responseData.getVacDpPsbEndDtm(), "가상계좌 생성에 실패했습니다.");
        } catch (ParseException e) {
            throw new BizException("가상계좌 생성에 실패했습니다.");
        }

        updateContractSettlementAfterVacIs(req.cntrStlmId(), responseData);

        return VacIsRveAskRes.builder()
            .acnoEncr(responseData.getVacNo())
            .owrKnm(responseData.getVacAcownNm())
            .fnitAprFshDtm(responseData.getVacDpPsbEndDtm())
            .stlmAmt(totStlmAmt)
            .build();
    }

    void updateContractSettlementAfterVacIs(String cntrStlmId, ZwdzWithdrawalVirtualAccountIssueDvo accountIssueDvo) {
        WctaContractStlmBasDvo updateApprovalStlmBasDvo = new WctaContractStlmBasDvo();
        updateApprovalStlmBasDvo.setCntrStlmId(cntrStlmId);
        updateApprovalStlmBasDvo.setBnkCd(accountIssueDvo.getVacBnkCd());
        updateApprovalStlmBasDvo.setAcnoEncr(accountIssueDvo.getVacNo());
        updateApprovalStlmBasDvo.setOwrKnm(accountIssueDvo.getVacAcownNm());
        updateApprovalStlmBasDvo.setVncoDvCd(accountIssueDvo.getVacVncoDvCd());
        updateApprovalStlmBasDvo.setFnitAprFshDtm(accountIssueDvo.getVacDpPsbEndDtm());
        updateApprovalStlmBasDvo.setFnitAprRsCd(CtFnitAprRsCd.NO_APPROVAL.getCode());
        updateContractSettlement(updateApprovalStlmBasDvo);
    }

    List<WctaContractStlmRelDvo> getContractStlmRels(String cntrStlmId) {
        return mapper.selectContractStlmRels(cntrStlmId);
    }

    public List<Integer> getRegularFundTransfersDayOptions(String dpTpCd) {
        CtDpTpCd ctDpTpCd = CtDpTpCd.of(dpTpCd);
        List<Integer> options;

        if (Objects.requireNonNull(ctDpTpCd) == CtDpTpCd.AC_AFTN) {
            options = mapper.getRegularFundTransfersDayOptions("01");
        } else if (ctDpTpCd == CtDpTpCd.CRDCD_AFTN) {
            options = mapper.getRegularFundTransfersDayOptions("02");
            /*case PG_AFTN -> options = mapper.getRegularFundTransfersDayOptions("01"); 몰루?*/
        } else {
            throw new BizException("해당 입금 유형은 정규 이체일이 필요하지 않습니다.");
        }

        BizAssert.notEmpty(options, "선택 가능한 정규이체일이 없습니다.");
        return options;
    }

    boolean checkCardNumberEffectiveness(
        String crdcdNo,
        String cardExpdtYm,
        String cstNo
    ) {
        WctaContractCstRelDvo cntrCstInfo = contractRegService.selectCntrtInfoByCstNo(cstNo);

        String copnDvDrmVal = CtCopnDvCd.of(cntrCstInfo.getCopnDvCd()) == CtCopnDvCd.INDIVIDUAL ? cntrCstInfo.getBryyMmdd()
        : CtCopnDvCd.of(cntrCstInfo.getCopnDvCd()) == CtCopnDvCd.COOPERATION ? cntrCstInfo.getBzrno()
            : "";

        String telephoneNumber = cntrCstInfo.getCralLocaraTno() + cntrCstInfo.getMexnoEncr() + cntrCstInfo.getCralIdvTno();

        return checkCardNumberEffectiveness(crdcdNo, cardExpdtYm, cntrCstInfo.getCopnDvCd(), copnDvDrmVal, telephoneNumber);
    }

    boolean checkCardNumberEffectiveness(
        String crdcdNo,
        String cardExpdtYm,
        String copnDvCd,
        String copnDvDrmVal,
        String telephoneNumber
    ) {
        ZwdaCardNumberEffectivenessCheckReqDvo cardNumberEffectivenessCheckReqDvo = new ZwdaCardNumberEffectivenessCheckReqDvo();
        cardNumberEffectivenessCheckReqDvo.setCrcdnoEncr(crdcdNo);
        cardNumberEffectivenessCheckReqDvo.setCardExpdtYm(cardExpdtYm);
        cardNumberEffectivenessCheckReqDvo.setCopnDvCd(copnDvCd);
        cardNumberEffectivenessCheckReqDvo.setCopnDvDrmVal(copnDvDrmVal);
        cardNumberEffectivenessCheckReqDvo.setTmlNo(telephoneNumber);
        cardNumberEffectivenessCheckReqDvo.setSysDvCd("W");
        return "1".equals(KiccReceiveService.saveCardNumberEffectivenessCheck(cardNumberEffectivenessCheckReqDvo).getStateCode());
    }

}
