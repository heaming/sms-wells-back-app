package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncContactHistoryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncContactHistoryDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstSvAsnNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncContactHistoryDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String cntcDt,
        String cntcHh,
        String absncRsonCd,
        String cntcCn
    ) {}
}
