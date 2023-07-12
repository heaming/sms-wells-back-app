package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sflex.common.common.dto.SujiewonDto;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.common.service.WctzContractNumberService;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaReceiptBulkUploadConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaReceiptBulkUploadMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtCntrTpCd;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtCntrwTpCd;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractProgressStatus;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtCopnDvCd;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WctaReceiptBulkUploadService {

    private final WctaReceiptBulkUploadConverter converter;
    private final WctaReceiptBulkUploadMapper mapper;
    private final SujiewonService sujiewonService;
    private final WctzHistoryService historyService;
    private final WctzContractNumberService cntrNoService;

    public ValidateProspectRes validateProspect(ValidateProspectReq req) {
        SujiewonDto.FormatRes adr = getFormattedAddresses(req.adr1(), req.adr2());

        WctzPdBasDvo pdBas = getPdBas(req.basePdCd());

        if (StringUtil.isNotBlank(req.svPdCd())) {
            boolean svExist = mapper.isExistServiceProduct(req.svPdCd());
            BizAssert.isTrue(svExist, "서비스상품코드를 확인해 주시길 바랍니다.");
        }

        return ValidateProspectRes.builder()
            .adr(adr)
            .pdBas(pdBas)
            .build();
    }

    @Transactional(timeout = 600)
    public int createProspectCsts(List<CreateProspectCstReq> reqs) {
        reqs.forEach(this::createProspectCst);
        return 1;
    }

    @Transactional(timeout = 600)
    public int createBulkProspectCsts(List<CreateProspectCstReq> reqs) {
        List<WctaBulkProspectCustomerDvo> wctaBulkProspectCustomerDvos = reqs.stream()
            .map(this::mapCreateProspectCstReqToWctaProspectCustomerDvo)
            .toList();

        /* 쿼리에서 한방에 처리하기 위해서 키가 정수형이라는 가정이 있어야합니다. 아니면 시퀀스라도 있으면 좋을 것 같아요. */
        String firstPspcCstId = mapper.selectPspcCstIdForNewPspcCstBas();
        long iHateThis1 = Long.parseLong(firstPspcCstId);

        String firstPspcCstCnslId = mapper.selectPspcCstCnslIdForNewPspcCstCnslBas();
        long iHateThis2 = Long.parseLong(firstPspcCstCnslId);

        for (WctaBulkProspectCustomerDvo wctaBulkProspectCustomerDvo : wctaBulkProspectCustomerDvos) {
            wctaBulkProspectCustomerDvo.setPspcCstId(String.format("%015d", iHateThis1++));
            wctaBulkProspectCustomerDvo.setPspcCstCnslId(String.format("%015d", iHateThis2++));
            wctaBulkProspectCustomerDvo.setPspcCstCnslSn(1);
        }

        int result = mapper.insertProspectCustomers(wctaBulkProspectCustomerDvos);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");

        return 1;
    }

    public ValidateBulkRentalRes validateBulkRental(ValidateBulkRentalReq req) {

        WctzCstBasDvo basDvo = converter.mapValidateBulkRentalReqToWctaCstBasDvo(req);
        basDvo.setMexnoEncr(DbEncUtil.enc(basDvo.getMexnoEncr())); /* 특정 메소드 전치사만 어노테이션 작동한다...*/
        BizAssert.isTrue(mapper.isExistCstBas(basDvo), "고객 정보를 조회할 수 없습니다. 고객 정보를 다시 확인해주세요.");

        SujiewonDto.FormatRes adr = getFormattedAddresses(req.adr1(), req.adr2());

        WctzPdBasDvo pdBas = getPdBas(req.basePdCd());

        List<WctzPdRelDvo> pdRelDvos = getPdRels(req.basePdCd());

        WctzPdRelDvo pdctPdRel = pdRelDvos.stream()
            .filter(wctzPdRelDvo -> "05".equals(wctzPdRelDvo.getPdRelTpCd()))
            .findFirst()
            .orElseThrow(
                () -> new BizException("헤당 상품에 해당하는 제품이 없습니다.")
            );

        boolean svExist = mapper.isExistServiceProduct(req.svPdCd());
        BizAssert.isTrue(svExist, "서비스상품코드를 확인해 주시길 바랍니다.");

        WctzPdRelDvo svPdRel = pdRelDvos.stream()
            .filter(wctzPdRelDvo ->
                "03".equals(wctzPdRelDvo.getPdRelTpCd()) && req.svPdCd().equals(wctzPdRelDvo.getOjPdCd()))
            .findFirst()
            .orElseThrow(
                () -> new BizException("대상 기준 상품에 해당하는 서비스 상품이 아닙니다. 서비스상품코드를 확인해 주시길 바랍니다.")
            );

        WctzMmPrtnrIzDvo partner = mapper.selectAlncmpDgPrtnr(req.alncmpDgPrtnrMapngCd(), req.alncmpDgPrtnrOgTpCd())
            .orElseThrow(() -> new BizException("파트너조회에 실패했습니다.\n관리자에게 문의해 주세요.."));

        WctaRentalFinalPriceDvo fnlDtlSearchParams = converter.mapValidateBulkRentalReqToFnlDtlSearchParam(req);
        fnlDtlSearchParams.setSellChnlCd(partner.getSellInflwChnlDtlCd());

        WctaRentalFinalPriceDvo price = mapper.selectRentalPdPrcFnlDtl(fnlDtlSearchParams)
            .orElseThrow(() -> new BizException("상품가격조회에 실패했습니다.\n입력된 상품 및 서비스 정보를 확인하세요."));

        if (req.sellDscCtrAmt() != null) {
            Double fnlVal = price.getFnlVal();
            BizAssert.isTrue(fnlVal >= req.sellDscCtrAmt(), "법인할인금액이 월 렌탈료를 초과했습니다.");
        }

        return ValidateBulkRentalRes.builder()
            .adrId(adr.adrCd())
            .pdHclsfId(pdBas.getPdHclsfId())
            .pdMclsfId(pdBas.getPdMclsfId())
            .pdLclsfId(pdBas.getPdLclsfId())
            .pdDclsfId(pdBas.getPdDclsfId())
            .sellTpCd(pdBas.getSellTpCd())
            .sellTpDtlCd(pdBas.getSellTpDtlCd())
            .cntrTam(price.getFnlVal() * req.cntrPtrm())
            .ackmtPerfRt(pdBas.getAckmtPerfRt())
            .ackmtPerfAmt(0L) /* todo 상품 기본에 없음. */
            .feeAckmtCt(pdBas.getAckmtCt())
            .feeAckmtBaseAmt(pdBas.getFeeAmt())
            .svPrd(0) /* todo 상품 기본에 없음. */
            .pdBaseAmt(price.getBasVal())
            .cntramDscAmt(0L) /* todo 모르겠음. */
            .fnlAmt(price.getFnlVal())
            .sellAmt(price.getFnlVal()) /* 우선 최종금액과 같다고 함.*/
            .dscAmt(price.getBasVal() - price.getFnlVal())
            .vat(price.getVat())
            .sellInflwChnlDtlCd(partner.getSellInflwChnlDtlCd())
            .pdctPdRelId(pdctPdRel.getPdRelId())
            .pdctPdCd(pdctPdRel.getOjPdCd())
            .pdctVlStrtDtm(pdctPdRel.getVlStrtDtm())
            .pdctVlEndDtm(pdctPdRel.getVlEndDtm())
            .pdctPdQty(pdctPdRel.getItmQty())
            .svPdRelId(svPdRel.getPdRelId())
            .svVlStrtDtm(svPdRel.getVlStrtDtm())
            .svVlEndDtm(svPdRel.getVlEndDtm())
            .svPdQty(svPdRel.getItmQty())
            .build();
    }

    private List<WctzPdRelDvo> getPdRels(String basePdCd) {
        return mapper.selectPdRels(basePdCd);
    }

    @Transactional(timeout = 600)
    public int createBulkRentals(List<CreateBulkRentalReq> reqs) {
        List<WctaBulkRentalDvo> wctaBulkRentalDvos = reqs.stream()
            .map(converter::mapCreateBulkRentalReqToWctaBulkRentalDvo)
            .toList();

        String firstCntrPrtnrRelId = mapper.selectCntrPrtnrRelIdForNewCntrPrtnrRel();
        long iHateThis1 = Long.parseLong(firstCntrPrtnrRelId);
        String firstCntrCstRelId = mapper.selectCntrCstRelIdForNewCntrCstRel();
        long iHateThis2 = Long.parseLong(firstCntrCstRelId);
        String firstCntrPdRelId = mapper.selectCntrPdRelIdForNewCntrPdRel();
        long iHateThis3 = Long.parseLong(firstCntrPdRelId);

        for (WctaBulkRentalDvo wctaBulkRentalDvo : wctaBulkRentalDvos) {
            String cntrNo = cntrNoService.getContractNumber("").cntrNo();
            int cntrSn = 1;
            wctaBulkRentalDvo.setCntrNo(cntrNo);
            wctaBulkRentalDvo.setCntrSn(cntrSn);
            switch (CtCopnDvCd.of(wctaBulkRentalDvo.getCopnDvCd())) {
                case INDIVIDUAL -> {
                    wctaBulkRentalDvo.setCntrTpCd(CtCntrTpCd.INDIVIDUAL.getCode());
                    wctaBulkRentalDvo.setTxinvPblOjYn("N");
                }
                case COOPERATION -> {
                    wctaBulkRentalDvo.setCntrTpCd(CtCntrTpCd.COOPERATION.getCode());
                    wctaBulkRentalDvo.setTxinvPblOjYn("Y");
                }
            }
            wctaBulkRentalDvo.setCntrPrgsStatCd(CtContractProgressStatus.TEMP_STEP2.getCode());
            wctaBulkRentalDvo.setPdQty(1);
            wctaBulkRentalDvo.setCntrwTpCd(CtCntrwTpCd.RENTAL.getCode());
            wctaBulkRentalDvo.setBlgCrpCd("DO"); /* (주)교원크리에이티브 */
            wctaBulkRentalDvo.setRveCrpCd("DO"); /* (주)교원크리에이티브 */
            wctaBulkRentalDvo.setCoCd("DO"); /* (주)교원크리에이티브 */
            wctaBulkRentalDvo.setCoCd("DO"); /* (주)교원크리에이티브 */
            wctaBulkRentalDvo.setCntrPrtnrRelId(String.format("%015d", iHateThis1++));
            wctaBulkRentalDvo.setCntrPrtnrTpCd("101"); /* 파트너 */
            wctaBulkRentalDvo.setCntrCstRelId(String.format("%015d", iHateThis2++));
            wctaBulkRentalDvo.setCntrUnitTpCd("010"); /* 계약단위유형코드 - '010' (계약) */
            wctaBulkRentalDvo.setCntrCstRelTpCd("010"); /* 계약고객관계유형코드 = '010' (계약자)*/
            wctaBulkRentalDvo.setCntrtRelCd("01");/* 계약자관계코드 = '01' (본인) */
            wctaBulkRentalDvo.setPdctCntrPdRelId(String.format("%015d", iHateThis3++));
            wctaBulkRentalDvo.setSvCntrPdRelId(String.format("%015d", iHateThis3++));
        }

        int result = mapper.insertBulkRentals(wctaBulkRentalDvos);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");

        return 1;
    }

    // default
    WctzPdBasDvo getPdBas(String pdCd) {
        return mapper.selectPdBasByPk(pdCd).orElseThrow(() -> new BizException("상품코드를 확인해 주시길 바랍니다."));
    }

    /**
     * 가망고객기본 단건 조회
     */
    WctzPspcCstBasDvo getPspcCstBasByPk(String pspcCstId) {
        return mapper.selectPspcCstBasByPk(pspcCstId).orElseThrow(() -> new BizException("MSG_ALT_SVE_ERR"));
    }

    /**
     * 상세 주소 기준으로 수지원넷에 요청하고 정제된 주소 정보를 조회 한다. 화면에서 조용히 실행하기 위해 biz exception 으로 감싸 rethrow 한다.
     *
     * @param adr1 주소1
     * @param adr2 주소2
     * @return 주소객체
     */
    @Transactional
    SujiewonDto.FormatRes getFormattedAddresses(String adr1, String adr2) {
        try {
            /* 상세 주소 기준으로 수지원넷에 요청하고 정제된 주소 정보를 조회 한다. 화면에서 조용히 실행하기 위해 biz exception 으로 감싸 rethrow 한다. */
            String searchWord = (adr1 + (" " + adr2)).trim();
            SujiewonDto.FormatRes formatRes = sujiewonService.getFormattedAddressesForRestApi(searchWord);

            if (formatRes.adrCd().startsWith("IF_ERR")) {
                throw new BizException("주소를 확인해 주시기 바랍니다.");
            }

            return formatRes;
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    @Transactional
    void createProspectCst(CreateProspectCstReq req) {
        WctzPspcCstBasDvo pspcCstBasDvo = converter.mapCreateProspectCstReqToWctaPspcCstBasDvo(req);
        String pspcCstId = createPspcCst(pspcCstBasDvo);
        WctzPspcCstCnslBasDvo pspcCstCnslBasDvo = converter.mapCreateProspectCstReqToWctaPspcCstCnslBasDvo(req);
        pspcCstCnslBasDvo.setPspcCstId(pspcCstId);
        String pspcCstCnslId = createPspcCstCnsl(pspcCstCnslBasDvo);
        WctzPspcCstCnslRcmdIzDvo pspcCstCnslRcmdIzDvo = new WctzPspcCstCnslRcmdIzDvo(pspcCstCnslId, 1); /* 생성이니 일련번호는 1이 아닐까?*/
        pspcCstCnslRcmdIzDvo.setPdCd(req.basePdCd());
        createPspcCstCnslRcmdIz(pspcCstCnslRcmdIzDvo);
    }

    @Transactional
    void createPspcCst(WctzPspcCstBasDvo dvo, String pspcCstId) {
        dvo.setPspcCstId(pspcCstId);
        int result = mapper.insertPspcCstBas(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstChHistory(pspcCstId);
    }

    @Transactional
    String createPspcCst(WctzPspcCstBasDvo dvo) {
        String pspcCstId = mapper.selectPspcCstIdForNewPspcCstBas();
        createPspcCst(dvo, pspcCstId);
        return pspcCstId;
    }

    @Transactional
    void createPspcCstCnsl(WctzPspcCstCnslBasDvo dvo, String pspcCstCnslId) {
        dvo.setPspcCstCnslId(pspcCstCnslId);
        int result = mapper.insertPspcCstCnslBas(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstCnslChHistory(pspcCstCnslId);
    }

    @Transactional
    String createPspcCstCnsl(WctzPspcCstCnslBasDvo dvo) {
        String pspcCstCnslId = mapper.selectPspcCstCnslIdForNewPspcCstCnslBas();
        createPspcCstCnsl(dvo, pspcCstCnslId);
        return pspcCstCnslId;
    }

    @Transactional
    void createPspcCstCnslRcmdIz(WctzPspcCstCnslRcmdIzDvo dvo) {
        int result = mapper.insertPspcCstCnslRcmdIz(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstCnslRchHistory(dvo.getPspcCstCnslId(), dvo.getPspcCstCnslSn());
    }

    WctaBulkProspectCustomerDvo mapCreateProspectCstReqToWctaProspectCustomerDvo(CreateProspectCstReq req) {
        return WctaBulkProspectCustomerDvo.builder()
            .wctzPspcCstBasDvo(converter.mapCreateProspectCstReqToWctaPspcCstBasDvo(req))
            .wctzPspcCstCnslBasDvo(converter.mapCreateProspectCstReqToWctaPspcCstCnslBasDvo(req))
            .wctzPspcCstCnslRcmdIzDvo(new WctzPspcCstCnslRcmdIzDvo())
            .build();
    }

}
