package com.kyowon.sms.wells.web.closing.performances.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdccPerformanceInterfaceDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불(할부)_실적정보 Result Dto
    @ApiModel("WdccPerformanceInterfaceDto-FindReq")
    public record FindReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cNTRNO,
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cNTRSN
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불(할부)_실적정보 Result Dto
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
