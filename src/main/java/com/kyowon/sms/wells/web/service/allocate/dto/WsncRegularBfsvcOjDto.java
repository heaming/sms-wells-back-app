package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncRegularBfsvcOjDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncRegularBfsvcOjDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String allocateYm,
        @NotBlank
        String createTarget
    ) {}
}
