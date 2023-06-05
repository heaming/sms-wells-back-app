package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncBsMngtSchdInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncBsMngtSchdInqrDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String fxnPrtnrNo,
        @NotBlank
        String baseDateFrom,

        @NotBlank
        String baseDateTo
    ) {}
}
