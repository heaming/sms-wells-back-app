package com.kyowon.sms.wells.web.contract.interfaces.dto;

import io.swagger.annotations.ApiModel;

public class WctiTaxInvoicePersonDto {
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
