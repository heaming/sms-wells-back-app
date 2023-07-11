package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbLossRentFeeClearingDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 위약금 손료 초기화 서비스 Save Req Dto
    @ApiModel("WdcbLossRentFeeClearingDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cntrNo, /*계약번호*/
        @NotBlank
        String cntrSn, /*계약일련번호*/
        @NotBlank
        String reqdDt /*철거일자*/
    ) {}
}
