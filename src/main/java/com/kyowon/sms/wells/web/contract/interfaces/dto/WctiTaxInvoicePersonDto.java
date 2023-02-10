package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiTaxInvoicePersonDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 세금계산서 담당자 정보 Search Request Dto
    @Builder
    @ApiModel("WctiTaxInvoicePersonDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String PSIC_NM
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 세금계산서 담당자 정보 Search Result Dto
    @ApiModel("WctiTaxInvoicePersonDto-SearchRes")
    public record SearchRes(
        String PSIC_ID, //담당자ID
        String PSIC_NM, //담당자명
        String DEPT_NM  //부서명
    ) {}
}
