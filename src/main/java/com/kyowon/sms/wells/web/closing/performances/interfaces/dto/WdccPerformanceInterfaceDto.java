package com.kyowon.sms.wells.web.closing.performances.interfaces.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WdccPerformanceInterfaceDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불(할부)_실적정보 Result Dto
    @Builder
    @ApiModel("WdccPerformanceInterfaceDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        @NotBlank
        String CNTR_SN
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불(할부)_실적정보 Result Dto
    @Builder
    @ApiModel("WdccPerformanceInterfaceDto-FindRes")
    public record FindRes(
        String CNTR_NO,
        String CNTR_SN,
        String DP_SUM_AMT,
        String DLQ_MCN,
        String UC_AMT,
        String FULPY_DT,
        String BU_NOTI_DT,
        String REDF_DT,
        String ADSB_DT,
        String FULPY_EXP_AMT,
        String MPY_TN,
        String RES_TN,
        String EOT_DLQ_AMT
    ) {
    }
}
