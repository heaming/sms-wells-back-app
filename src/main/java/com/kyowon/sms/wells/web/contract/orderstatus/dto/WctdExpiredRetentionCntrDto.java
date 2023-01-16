package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import java.util.Arrays;
import java.util.List;

import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctdExpiredRetentionCntrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // wells 렌탈 설치 약정만료 현황 조회 Search Request Dto
    @Builder
    @ApiModel("WctdExpiredRetentionCntrDto-SearchReq")
    public record SearchReq(
        String cntrPdEnddtStrtdt,
        String cntrPdEnddtEnddt,
        String pdHclsfId,
        String pdMclsfId,
        String basePdCd,
        String pdNm,
        String isExcdCan,
        List<String> canDtlCds
    ) {
        public SearchReq {
            canDtlCds = Arrays.asList(CtContractConst.CntrStatCd.CANCELLATION.getDtlCds());
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells 렌탈 설치 약정만료 현황 조회 Search Result Dto
    @ApiModel("WctdExpiredRetentionCntrDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cntrCstNo,
        String cstKnm,
        String basePdCd,
        String pdNm,
        String istmMcn,
        String recapDutyPtrmN,
        String slDt,
        String dutyUseDt,
        String exnDt,
        String canDt,
        String canCstDutyUseExprYn,
        String mshCntrNo,
        String mshCntrSn,
        String mshCntrDt,
        String mshCanDt,
        String mshWdwalDt,
        String cntrtCralLocaraTno,
        String cntrtMexnoEncr,
        String cntrtCralIdvTno,
        String istllCralLocaraTno,
        String istllMexnoEncr,
        String istllCralIdvTno
    ) {}
}
