package com.kyowon.sms.wells.web.service.interfaces.dto;

import io.swagger.annotations.ApiModel;

public class WsniCenterUnusualItemInterfaceDto {
    @ApiModel(value = "WsniCenterUnusualItemInterfaceDto-CreateReq")
    public record CreateReq(
        String cntrNo,
        String cntrSn,
        String wkPrtnrNo,
        String cstUnuitmCn
    ) {}

    @ApiModel(value = "WsniCenterUnusualItemInterfaceDto-CreateRes")
    public record CreateRes(
        String resultCode,
        String resultMessage
    ) {}
}
