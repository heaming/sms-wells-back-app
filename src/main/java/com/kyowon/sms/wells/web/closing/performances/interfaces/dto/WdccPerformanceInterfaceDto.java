package com.kyowon.sms.wells.web.closing.performances.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotBlank
        @JsonProperty("CNTR_SN")
        int cntrSn
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불(할부)_실적정보 Result Dto
    @Builder
    @ApiModel("WdccPerformanceInterfaceDto-FindRes")
    public record FindRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("DP_SUM_AMT")
        String dpSumAmt,
        @JsonProperty("DLQ_MCN")
        String dlqMcn,
        @JsonProperty("UC_AMT")
        String ucAmt,
        @JsonProperty("FULPY_DT")
        String fulpyDt,
        @JsonProperty("BU_NOTI_DT")
        String buNotiDt,
        @JsonProperty("REDF_DT")
        String redfDt,
        @JsonProperty("ADSB_DT")
        String adsbDt,
        @JsonProperty("FULPY_EXP_AMT")
        String fulpyExpAmt,
        @JsonProperty("MPY_TN")
        String mpyTn,
        @JsonProperty("RES_TN")
        String resTn,
        @JsonProperty("EOT_DLQ_AMT")
        String eotDlqAmt
    ) {
    }
}
