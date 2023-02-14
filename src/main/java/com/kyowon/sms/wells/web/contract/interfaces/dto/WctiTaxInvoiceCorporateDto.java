package com.kyowon.sms.wells.web.contract.interfaces.dto;

import io.swagger.annotations.ApiModel;

public class WctiTaxInvoiceCorporateDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 세금계산서 사업자번호 조회 Search Result Dto
    @ApiModel("WctiTaxInvoiceCorporateDto-SearchRes")
    public record SearchRes(
        String DLPNR_CD,    //거래처코드
        String DLPNR_NM,    //거래처명
        String BZRNO,       //사업자등록번호
        String DLGPS_NM     //대표자명
    ) {}
}
