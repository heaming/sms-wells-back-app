package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiRequidationDateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약건의 철거 정보 Find Request Dto
    @ApiModel("WctiRequidationDateDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        @NotBlank
        String CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약건의 철거 정보 Find Result Dto
    @ApiModel("WctiRequidationDateDto-FindRes")
    public record FindRes(
        String CNTR_NO,
        String CNTR_SN,
        String CNTR_DT,
        String IST_DT,
        String REQD_DT,
        String REQD_IST_DC_GAP
    ) {}
}
