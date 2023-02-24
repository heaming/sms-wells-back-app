package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiFreeASPeriodDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 무상 AS 기간 Search Request Dto
    @ApiModel("WctiFreeASPeriodDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        @NotBlank
        String CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 무상 AS 기간 Search Result Dto
    @ApiModel("WctiFreeASPeriodDto-FindRes")
    public record FindRes(
        String FRISU_AS_MCN,
        String SS_FRISU_AS_MCN
    ) {}
}
