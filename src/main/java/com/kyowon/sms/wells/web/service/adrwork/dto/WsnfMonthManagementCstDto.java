package com.kyowon.sms.wells.web.service.adrwork.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnfMonthManagementCstDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnfMonthManagementCstDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String managementYm,
        @NotBlank
        String createTarget
    ) {}

    @ApiModel(value = "WsnfMonthManagementCstDto-DeleteReq")
    public record DeleteReq(
        @NotBlank
        String managementYm,
        @NotBlank
        String createTarget
    ) {}
}
