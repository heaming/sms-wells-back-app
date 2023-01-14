package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnyBeforeServiceBizClDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnyBeforeServiceBizClDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String managementYear,
        String managementItem
    ) {}

    @ApiModel(value = "WsnyBeforeServiceBizClDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String mngtItm,
        String strtdt,
        String strtHh,
        String enddt,
        String endHh
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnyBeforeServiceBizClDto-SearchRes")
    public record SearchRes(
        String mngtYm,
        String strtdt,
        String strtHh,
        String enddt,
        String endHh,
        String mngtItm
    ) {}
}
