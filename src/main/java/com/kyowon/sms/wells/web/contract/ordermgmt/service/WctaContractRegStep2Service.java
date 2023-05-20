package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrPrccchHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep2Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep2Service {
    private final WctaContractRegStep2Mapper mapper;

    private final WctaContractRegService regService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep2Dvo step2Dvo = new WctaContractRegStep2Dvo();
        step2Dvo.setCntrNo(cntrNo);
        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        step2Dvo.setBas(bas);
        List<WctaContractDtlDvo> dtls = mapper.selectContractDtlWithPdInfo(cntrNo);
        for (WctaContractDtlDvo dtl : dtls) {
            dtl.setPdCd(dtl.getBasePdCd());
            dtl.setSuscMm(dtl.getCntrPtrm()); // 계약기간 혹은 약정기간
            // 계약가격산출내역 조회
            List<WctaContractPrcCmptIzDvo> prcCmptIzDvos = regService
                .selectContractPrcCmptIz(dtl.getCntrNo(), dtl.getCntrSn());
            if (CollectionUtils.isNotEmpty(prcCmptIzDvos)) {
                WctaContractPrcCmptIzDvo prcCmptIz = prcCmptIzDvos.get(0);
                dtl.setPdPrcFnlDtlId(prcCmptIz.getPdPrcFnlDtlId());
                dtl.setVerSn(prcCmptIz.getVerSn());
                dtl.setFxamFxrtDvCd(prcCmptIz.getFxamFxrtDvCd());
                dtl.setCtrVal(prcCmptIz.getCtrVal());
                dtl.setPdPrcId(prcCmptIz.getPdPrcId());
            }
            // 학습지라면(전집 1이 아닌 경우) 계약학습지상세 조회
            if (!dtl.getSellTpCd().equals("1")) {
                List<WctaContractHsmtrlDtlDvo> hsmtrlDtlDvos = regService
                    .selectCntrHsmtrlDtl(dtl.getCntrNo(), dtl.getCntrSn());
                if (CollectionUtils.isNotEmpty(hsmtrlDtlDvos)) {
                    WctaContractHsmtrlDtlDvo hsmtrlDtl = hsmtrlDtlDvos.get(0);
                    dtl.setLrnnStrtRqdt(hsmtrlDtl.getLrnnStrtRqdt());
                }
            }
            // 상품 유형에 따라 select option 조회
            String pdCd = dtl.getBasePdCd();
            if (isContainSuscMm(dtl.getMclsfVal(), dtl.getLclsfVal()))
                dtl.setSuscMms(
                    mapper.selectProductSuscMms(
                        pdCd, dtl.getSellTpCd(), bas.getSellInflwChnlDtlCd(), dtl.getLrnnLvGrpDvCd()
                    )
                );
            if (isContainLrnnLv(dtl.getMclsfVal(), dtl.getLclsfVal()))
                dtl.setLrnnLvs(mapper.selectProductLrnnLvs(pdCd));
            if (isContainStrtLv(dtl.getMclsfVal(), dtl.getLclsfVal()))
                dtl.setStrtLvs(mapper.selectProductStrtLvs(pdCd));
        }
        step2Dvo.setDtls(dtls);
        return WctaContractRegDvo.builder()
            .step2(step2Dvo)
            .build();
    }

    private boolean isContainSuscMm(String mclsfVal, String lclsfVal) {
        return StringUtils.containsAny(mclsfVal, "01002", "02001", "02002", "02003", "04001")
            || StringUtils.containsAny(lclsfVal, "01003001", "01003002", "01003004", "01003005", "04001001");
    }

    private boolean isContainLrnnLv(String mclsfVal, String lclsfVal) {
        return StringUtils.containsAny(lclsfVal, "01003001", "01003002");
    }

    private boolean isContainStrtLv(String mclsfVal, String lclsfVal) {
        return StringUtils.containsAny(
            lclsfVal,
            "02001003", "02001004", "03001001", "03001003", "03001004", "03001005", "03002001",
            "03002003", "03002004", "04001005"
        );
    }

    public List<WctaContractRegStep2Dvo.PdSvcDvo> selectProductdServiceInfo(String pdCd) {
        return mapper.selectProductdServiceInfo(pdCd);
    }

    public WctaContractRegStep2Dvo.PdAmtDvo selectProductPrices(WctaContractDto.SearchPdAmtReq dto) {
        return mapper.selectProductPrices(dto);
    }

    public WctaContractRegStep2Dvo selectLrnrPds(String cntrNo, String pdFilter) {
        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        WctaContractCstRelDvo lrnr = regService.selectContractCstRel(cntrNo).stream()
            .filter((cst) -> cst.getCntrCstRelTpCd().equals("20")).findFirst().orElseThrow();

        WctaContractRegStep2Dvo pdDvo = new WctaContractRegStep2Dvo();

        pdDvo.setPdClsf(mapper.selectPdClsfs());
        int lrnrAge = regService.calcAge(regService.getBryyMmdd(lrnr.getCstNo()));

        String sellChnlCd = bas.getSellInflwChnlDtlCd();
        // FIXME 판매유입채널상세코드 1010 세팅
        sellChnlCd = "1010";
        List<WctaContractRegStep2Dvo.PdDvo> pds = mapper.selectProducts(sellChnlCd, pdFilter);
        // 필터링 후 상품별로 구독개월, 시작단계 목록 조회
        for (WctaContractRegStep2Dvo.PdDvo pd : pds) {
            String pdCd = pd.getPdCd(); // 기준상품코드
            String sellTpCd = pd.getSellTpCd(); // 판매유형코드
            String lrnnLvGrpDvCd = pd.getLrnnLvGrpDvCd(); // 학습단계구분코드
            if (isContainSuscMm(pd.getMclsfVal(), pd.getLclsfVal()))
                pd.setSuscMms(mapper.selectProductSuscMms(pdCd, sellTpCd, sellChnlCd, lrnnLvGrpDvCd));
            if (isContainLrnnLv(pd.getMclsfVal(), pd.getLclsfVal()))
                pd.setLrnnLvs(mapper.selectProductLrnnLvs(pdCd));
            if (isContainStrtLv(pd.getMclsfVal(), pd.getLclsfVal()))
                pd.setStrtLvs(mapper.selectProductStrtLvs(pdCd));
        }

        pdDvo.setProducts(pds);
        return pdDvo;
    }

    @Transactional
    public String saveContractStep2(WctaContractRegStep2Dvo dvo) {
        String now = DateUtil.todayNnow();
        String cntrNo = dvo.getBas().getCntrNo();
        // 0. 계약기본
        regService.updateCntrPrgsStatCd(cntrNo, "12");

        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );

        // 1. step 저장은 계약기본 제외하고 delete and insert
        regService.removeStep2Data(cntrNo);
        regService.removeStep3Data(cntrNo);
        regService.removeStep4Data(cntrNo);

        for (WctaContractDtlDvo dtl : dvo.getDtls()) {
            dtl.setCntrNo(cntrNo);
            dtl.setBasePdCd(dtl.getPdCd());
            dtl.setCntrPtrmUnitCd("20"); // 계약기간단위코드
            dtl.setCntrPtrm(dtl.getSuscMm());
            dtl.setStplPtrmUnitCd("20"); // 약정기간단위코드
            dtl.setStplPtrm(dtl.getSuscMm());
            dtl.setBlgCrpCd("J0");
            dtl.setRveCrpCd("J0");
            dtl.setCoCd("J0");
            dtl.setBooSellTpCd(""); // TODO
            dtl.setSellDscDvCd("");
            dtl.setSellDscrCd("");
            dtl.setSellDscCtrAmt(0l);
            dtl.setSellDscTpCd("");
            dtl.setPdBaseAmt(Math.multiplyExact(dtl.getPdQty(), dtl.getFnlAmt()));
            dtl.setSellAmt(dtl.getFnlAmt());
            dtl.setDscAmt(0l);
            dtl.setSellFee(0l);
            dtl.setCntrTam(dtl.getFnlAmt());
            dtl.setResubYn("N"); // TODO
            dtl.setAlncmpCd(""); // TODO

            // 2-1. 계약상세
            mapper.insertCntrDtlStep2(dtl);
            // 2-2. 계약상세이력
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(dtl.getCntrSn())
                    .histStrtDtm(now)
                    .build()
            );

            // 기준상품에 서비스가 있는경우
            List<WctaContractRegStep2Dvo.PdSvcDvo> pdSvcs = selectProductdServiceInfo(dtl.getBasePdCd());
            for (WctaContractRegStep2Dvo.PdSvcDvo pdSvc : pdSvcs) {
                // 4. 계약상품관계
                mapper.insertCntrPdRelStep2(
                    WctaContractPdRelDvo.builder()
                        .cntrNo(cntrNo)
                        .cntrSn(dtl.getCntrSn())
                        .vlStrtDtm(now)
                        .vlEndDtm(CtContractConst.END_DTM)
                        .pdRelId(pdSvc.getPdRelId())
                        .basePdCd(pdSvc.getBasePdCd())
                        .ojPdCd(pdSvc.getOjPdCd())
                        .pdRelTpCd(pdSvc.getPdRelTpCd())
                        .pdQty(dtl.getPdQty())
                        .build()
                );
            }
            /* TODO 프로모션, 사은품 정의되면 처리
            // 5. 계약프로모션내역(프로모션을 선택한 경우)
            mapper.insertCntrPmotIzStep2(
                WctaContractPmotIzDvo.builder()
                    .build()
            );
            // 6. 사은품접수내역(사은품이 있는 경우)
            mapper.insertFgptRcpIzStep2(
                WctaFgptRcpIzDvo.builder()
                    .build()
            );
             */
            // 7-1. 계약가격산출내역
            mapper.insertCntrPrcCmptIzStep2(
                WctaContractPrcCmptIzDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(dtl.getCntrSn())
                    .pdCd(dtl.getPdCd())
                    .pdPrcFnlDtlId(dtl.getPdPrcFnlDtlId())
                    .verSn(dtl.getVerSn())
                    .fxamFxrtDvCd(dtl.getFxamFxrtDvCd())
                    .ctrVal(dtl.getCtrVal())
                    .fnlVal(dtl.getFnlAmt())
                    .pdPrcId(dtl.getPdPrcId())
                    .build()
            );
            // 7-2. 계약가격산출변경이력
            historyService.createCntrPrccchHistory(
                WctzCntrPrccchHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(dtl.getCntrSn())
                    .histStrtDtm(now)
                    .build()
            );
        }

        return cntrNo;
    }
}
