package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiRentalAdditionalServiceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈 부가서비스 Search Request Dto
    @ApiModel("WctiRentalAdditionalServiceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO, //계약번호(필수)
        @NotBlank
        String CNTR_SN //계약일련번호(필수)
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈 부가서비스 Search Result Dto
    @ApiModel("WctiRentalAdditionalServiceDto-SearchRes")
    public record SearchRes(
        String IST_DT,
        String REQD_DT,
        String ADN_SV_CS_AMT,
        String RENTAL_AMT,
        String TOT_AMT
    ) {}
}


