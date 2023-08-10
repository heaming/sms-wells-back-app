package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WsniCubigVisitStopDto {

    @ApiModel(value = "WsniCubigVisitStopDto-CreateReq")
    public record CreateReq(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("APY_DT")
        String apyDt,
        @JsonProperty("SPP_STP_DV_CD")
        String sppStpDvCd,
        @JsonProperty("TN1_STP_YM")
        String tn1StpYm,
        @JsonProperty("TN2_STP_YM")
        String tn2StpYm,
        @JsonProperty("TN3_STP_YM")
        String tn3StpYm,
        @JsonProperty("TN4_STP_YM")
        String tn4StpYm,
        @JsonProperty("SPP_AK_SPF_DT")
        String sppAkSpfDt,
        @JsonProperty("OG_TP_CD")
        String ogTpCd,
        @JsonProperty("PRTNR_NO")
        String prtnrNo
    ) {}

    @ApiModel(value = "WsniCubigVisitStopDto-CreateRes")
    public record CreateRes(
        @JsonProperty("RESULT_CODE")
        String resultCode,
        @JsonProperty("RESULT_MESSAGE")
        String resultMessage
    ) {}
}
