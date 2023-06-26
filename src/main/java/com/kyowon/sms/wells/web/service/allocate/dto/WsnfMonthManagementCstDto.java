package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnfMonthManagementCstDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnfMonthManagementCstDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String createTarget
    ) {}

    @ApiModel(value = "WsnfMonthManagementCstDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String createTarget
    ) {}
}
