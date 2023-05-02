package com.kyowon.sms.wells.web.service.interfaces.dto;

import io.swagger.annotations.ApiModel;

public class WsniCenterUnuitmSaveInterfaceDto {
    @ApiModel(value = "WsniCenterUnuitmSaveInterfaceDto-CreateReq")
    public record CreateReq(

    ) {}

    @ApiModel(value = "WsniCenterUnuitmSaveInterfaceDto-CreateRes")
    public record CreateRes(
        String resultCode,
        String resultMessage
    ) {}
}
