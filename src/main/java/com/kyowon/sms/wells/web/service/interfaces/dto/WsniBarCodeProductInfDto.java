package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsniBarCodeProductInfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniBarCodeProductInfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String qrcd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniBarCodeProductInfDto-SearchRes")
    public record SearchRes(
        int resultcode,
        int regi
    ) {}
}
