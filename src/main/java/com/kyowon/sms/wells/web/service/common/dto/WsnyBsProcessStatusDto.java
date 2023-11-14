package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

public class WsnyBsProcessStatusDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnyBsProcessStatusDto-SearchReq")
    public record SearchReq(

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnyBsProcessStatusDto-SearchRes")
    public record SearchRes(
        String totalCnt,
        String incompleteCnt,
        String completeCnt,
        String completeRate
    ) {}
}
