package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsniRegistrationBarcodeInterfaceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniRegistrationBarcodeInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String qrcd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniRegistrationBarcodeInterfaceDto-SearchRes")
    public record SearchRes(
        int resultcode,
        int regi
    ) {}

    @ApiModel(value = "WsniRegistrationBarcodeInterfaceDto-SearchJsonRes")
    public record SearchJsonRes(
        String resultCode,
        String regi,
        String resultMessage
    ) {}

}
