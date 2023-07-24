package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdccEtcPerformanceInterfaceDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불외_실적정보 Result Dto
    @ApiModel("WdccEtcPerformanceInterfaceDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        int CNTR_SN,
        String CNTRT_NM,
        @NotBlank
        String SL_CL_YM1,
        @NotBlank
        String SL_CL_YM2
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불외_실적정보 Result Dto
    @ApiModel("WdccEtcPerformanceInterfaceDto-FindRes")
    public record FindRes(
        String CNTR_NO,
        String CNTR_SN,
        String SL_CL_YM,
        String RENTAL_TN,
        String SL_STP_YN,
        String THM_SL_SUM_AMT,
        String SL_DP_AGG_AMT,
        String ACU_OC_ATAM,
        String EOT_ATAM,
        String EOT_DLQ_AMT,
        String EOT_DLQ_ADD_AMT,
        String CLCTAM_DV_CD,
        String CLCTAM_DV_CD_NM,
        String PRTNR_NO,
        String PRTNR_FNM
    ) {}
}
