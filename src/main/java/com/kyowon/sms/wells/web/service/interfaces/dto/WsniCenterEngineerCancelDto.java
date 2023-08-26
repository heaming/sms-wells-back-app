package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WsniCenterEngineerCancelDto {
    @ApiModel(value = "WsniCenterEngineerCancelDto-FindReq")
    public record FindReq(
        @JsonProperty("USER_ID")
        String userId
    ) {}

    @ApiModel(value = "WsniCenterEngineerCancelDto-FindRes")
    public record FindRes(
        @JsonProperty("CANCEL_COUNT")
        String cancelCount
    ) {}
}
