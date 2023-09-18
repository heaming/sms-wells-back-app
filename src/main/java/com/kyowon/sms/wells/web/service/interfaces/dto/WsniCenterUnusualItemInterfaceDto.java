package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

public class WsniCenterUnusualItemInterfaceDto {
    @ApiModel(value = "WsniCenterUnusualItemInterfaceDto-CreateReq")
    public record CreateReq(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        String cntrSn,
        @JsonProperty(value = "WK_PRTNR_NO")
        String wkPrtnrNo,
        @JsonProperty(value = "CST_UNUITM_CN")
        String cstUnuitmCn
    ) {}

    @ApiModel(value = "WsniCenterUnusualItemInterfaceDto-CreateRes")
    public record CreateRes(
        @JsonProperty("RESULT_CODE")
        String resultCode,
        @JsonProperty("RESULT_MESSAGE")
        String resultMessage
    ) {}
}
