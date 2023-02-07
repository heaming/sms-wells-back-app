package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctbCancellationMtrSetDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctbCancellationMtrSetDto-SearchReq")
    public record SearchReq(
        String businessType,
        String performanceYm
    ) {}
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctbCancellationMtrSetDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cntrCanDtm
    ) {}
}
