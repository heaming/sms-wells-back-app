package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncAssignPsicTfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncAssignPsicTfDto-SearchPkReq")
    public record SearchPkReq(
        @NotBlank
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncAssignPsicTfDto-SearchReq")
    public record SearchReq(
        String cstSvAsnNo
    ) {}
}
