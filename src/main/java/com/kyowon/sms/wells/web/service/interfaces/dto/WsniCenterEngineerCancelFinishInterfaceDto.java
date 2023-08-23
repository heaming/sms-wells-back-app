package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WsniCenterEngineerCancelFinishInterfaceDto {
    @ApiModel(value = "WsniCenterEngineerCancelFinishInterfaceDto-EditReq")
    public record EditReq(
        @JsonProperty("AS_IST_OJ_NO")
        String asIstOjNo
    ) {}

    @ApiModel(value = "WsniCenterEngineerCancelFinishInterfaceDto-EditRes")
    public record EditRes(
        @JsonProperty("MSG")
        String msg,
        @JsonProperty("RESULT")
        String result
    ) {}
}
