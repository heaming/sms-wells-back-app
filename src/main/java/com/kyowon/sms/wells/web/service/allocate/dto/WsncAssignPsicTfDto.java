package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncAssignPsicTfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncAssignPsicTfDto-SearchProcessPkReq")
    public record SearchProcessPkReq(
        @NotBlank
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncAssignPsicTfDto-SearchProcessReq")
    public record SearchProcessReq(
        String cstSvAsnNo
    ) {}
}
