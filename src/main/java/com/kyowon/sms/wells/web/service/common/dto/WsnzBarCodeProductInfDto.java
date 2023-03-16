package com.kyowon.sms.wells.web.service.common.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnzBarCodeProductInfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnzBarCodeProductInfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String qrcd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnzBarCodeProductInfDto-SearchRes")
    public record SearchRes(
        int resultcode,
        int regi
    ) {}
}
