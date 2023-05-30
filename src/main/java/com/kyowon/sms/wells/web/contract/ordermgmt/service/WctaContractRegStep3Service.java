package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep3Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep3Service {
    private final WctaContractRegStep3Mapper mapper;

    private final WctaContractRegService regService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep3Dvo step3Dvo = new WctaContractRegStep3Dvo();

        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        List<WctaContractDtlDvo> dtls = regService.selectContractDtl(cntrNo);
        List<WctaContractAdrpcBasDvo> adrpcs = regService.selectContractAdrpcBas(cntrNo);

        // 계약자 기준 기본주소지 조회
        WctaContractAdrpcBasDvo basAdrpc = mapper.selectAdrInfoByCntrCstNo(bas.getCntrCstNo());
        basAdrpc.setCntrtRelCd("01");
        step3Dvo.setBasAdrpc(basAdrpc);

        if (regService.isNewCntr(bas.getCntrPrgsStatCd(), CtContractConst.CNTR_PRGS_STAT_CD_TEMP_STEP3)) {

            // wellsDtl 기본vo 생성
            WctaContractWellsDtlDvo basWellsDtl = WctaContractWellsDtlDvo.builder()
                .istPlcTpCd("1")
                .fmmbN(1)
                .useElectTpCd("1")
                .srcwtTpCd("1")
                .wprsItstTpCd("1")
                .build();

            dtls.forEach((dtl) -> {
                dtl.setAdrType("1");
                dtl.setAdrpc(basAdrpc);
                dtl.setWellsDtl(basWellsDtl);
            });
        } else {
            dtls.forEach((dtl) -> {
                int cntrSn = dtl.getCntrSn();
                WctaContractAdrRelDvo adrRel = regService.selectContractAdrRel(cntrNo, cntrSn);
                dtl.setAdrRel(adrRel);
                // 주소관계를 바탕으로 주소지기본 세팅
                dtl.setAdrpc(
                    adrpcs.stream().filter((adrpc) -> adrpc.getCntrAdrpcId().equals(adrRel.getCntrAdrpcId()))
                        .findFirst().orElse(new WctaContractAdrpcBasDvo())
                );

            });
        }
        step3Dvo.setCntrNo(cntrNo);
        step3Dvo.setBas(bas);
        step3Dvo.setDtls(dtls);
        return WctaContractRegDvo.builder()
            .step3(step3Dvo)
            .build();
    }

    @Transactional
    public String saveContractStep3(WctaContractRegStep3Dvo dvo) {
        String now = DateUtil.todayNnow();
        String cntrNo = dvo.getBas().getCntrNo();

        // step3, 4 정보 삭제
        regService.removeStep3Data(cntrNo);
        regService.removeStep4Data(cntrNo);

        // 0. 계약기본
        regService.updateCntrPrgsStatCd(dvo.getCntrNo(), "14");
        mapper.deleteStlmRelsStep3(cntrNo);
        mapper.deleteAdrpcBasStep3(cntrNo);
        mapper.deleteAdrRelsStep3(cntrNo);

        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );

        // 1. 계약주소지기본(EDU는 한 건 적재)
        // mapper.insertCntrAdrpcBasStep3(adrpcBasDvo);

        for (WctaContractDtlDvo dtl : dvo.getDtls()) {
            // 2-1. 계약상세
            dtl.setIstmIntAmt(0l);
            dtl.setRveCrpCd("J0"); // TODO 전집멤버십 20?
            dtl.setStlmTpCd(dvo.getStlmTpCd());
            dtl.setCrncyDvCd("KRW");
            dtl.setSppDuedt(dvo.getSppDuedt());
            mapper.updateCntrDtlStep3(dtl);
            // 2-2. 계약상세이력
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(dtl.getCntrNo())
                    .cntrSn(dtl.getCntrSn())
                    .histStrtDtm(now)
                    .build()
            );

            // 3. 계약결제관계
            if ("10".equals(dvo.getStlmTpCd())) {
                // 3-1. 결제유형 일시불
            } else if ("20".equals(dvo.getStlmTpCd())) {
                // 3-2. 결제유형 할부
                for (WctaContractStlmRelDvo stlmRel : dtl.getStlmRels()) {
                    // stlmRel은 모두 마일리지, 계약금과 할부금은 dtl에 저장해서 내려옴
                    if (ObjectUtils.isNotEmpty(stlmRel.getStlmAmt())) {
                        stlmRel.setVlStrtDtm(now);
                        stlmRel.setVlEndDtm(CtContractConst.END_DTM);
                        stlmRel.setCntrUnitTpCd("020");
                        stlmRel.setDtlCntrNo(dtl.getCntrNo());
                        stlmRel.setDtlCntrSn(dtl.getCntrSn());
                        stlmRel.setRveDvCd("01"); // 마일리지는 계약금에만 사용
                        stlmRel.setIslndIncmdcTpCd(""); // TODO 도서지역소득공제유형코드
                        mapper.insertCntrStlmRelStep3(stlmRel);
                    }
                }
                // TODO 계약금 0원일 때 저장여부 확인
                if (0 != dtl.getCntram().intValue()) {
                    mapper.insertCntrStlmRelStep3(
                        WctaContractStlmRelDvo.builder()
                            .vlStrtDtm(now)
                            .vlEndDtm(CtContractConst.END_DTM)
                            .cntrUnitTpCd("020")
                            .dtlCntrNo(dtl.getCntrNo())
                            .dtlCntrSn(dtl.getCntrSn())
                            .rveDvCd("01")
                            .dpTpCd(dvo.getCntramDpTpCd())
                            .islndIncmdcTpCd("")
                            .stlmAmt(dtl.getCntram())
                            .build()
                    );
                }
                // TODO 할부금 0원일 때 저장여부 확인
                if (0 != dtl.getMmIstmAmt().intValue()) {
                    mapper.insertCntrStlmRelStep3(
                        WctaContractStlmRelDvo.builder()
                            .vlStrtDtm(now)
                            .vlEndDtm(CtContractConst.END_DTM)
                            .cntrUnitTpCd("020")
                            .dtlCntrNo(dtl.getCntrNo())
                            .dtlCntrSn(dtl.getCntrSn())
                            .rveDvCd("03")
                            .dpTpCd(dvo.getIntamDpTpCd())
                            .islndIncmdcTpCd("")
                            .stlmAmt(dtl.getMmIstmAmt())
                            .build()
                    );
                }
            }

            // 4. 계약주소관계
            mapper.insertCntrAdrRelStep3(
                WctaContractAdrRelDvo.builder()
                    .vlStrtDtm(now)
                    .vlEndDtm(CtContractConst.END_DTM)
                    .adrpcTpCd("2")
                    .cntrUnitTpCd("020")
                    .dtlCntrNo(dtl.getCntrNo())
                    .dtlCntrSn(dtl.getCntrSn())
                    // .cntrAdrpcId(adrpcBasDvo.getCntrAdrpcId())
                    // 전집의 경우 입력받은 값, 학습지는 무조건 택배
                    .sppMthdTpCd("1".equals(dtl.getSellTpCd()) ? dvo.getSppMthdTpCd() : "2")
                    // TODO 배송담당자 조회 서비스 사용, 배송담당사무소코드와 배송담당사용자ID 세팅
                    .build()
            );
        }
        return dvo.getCntrNo();
    }
}
