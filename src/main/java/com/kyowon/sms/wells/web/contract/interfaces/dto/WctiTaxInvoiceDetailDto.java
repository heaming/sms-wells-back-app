package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiTaxInvoiceDetailDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 세금계산서 상세목록 Search Request Dto
    @ApiModel("WctiTaxInvoiceDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String TXINV_ID         //세금계산서ID
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 세금계산서 상세목록 Search Result Dto
    @ApiModel("WctiTaxInvoiceDetailDto-SearchRes")
    public record SearchRes(
        String TRD_DT,			//거래일자
        String TXINV_PD_DV_NM,  //세금계산서상품구분명
        String CNTR_NO,         //계약번호
        String CNTR_SN,         //계약일련번호
        String CNTRT_NM,        //계약자명
        String PD_QTY,          //상품수량
        String SPL_AMT,         //공급금액
        String VAT_AMT,         //부가가치세금액
        String SUM_AMT          //합계금액
    ) {}
}
