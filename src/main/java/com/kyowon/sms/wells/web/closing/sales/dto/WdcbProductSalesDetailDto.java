package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbProductSalesDetailDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseDtmnFrom,
        @NotBlank
        String baseDtmnTo,
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtl,
        String cntrNo,
        String cntrSn,
        String cstNo,
        String sapSlpno,
        String sapPdDvCd
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchSingleRes")
    public record SearchSingleRes(
        String sellTpCd,
        String sellTpDtlCd,
        String pdCd,
        String pdNm,
        String sellInflwChnlDtlCd,
        String slRcogDt,
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sapPdDvCd,
        String sellQty,
        String sellAmt,
        String sellSplAmt,
        String sellAmtVat,
        String pvdaAmt,
        String chQty,
        String slChAmt,
        String chSplAmt,
        String chVat,
        String chPvdaAmt,
        String canQty,
        String slCanAmt,
        String canSplAmt,
        String canVat,
        String canPvdaAmt,
        String totQty,
        String totAmt,
        String totSplAmt,
        String totVat,
        String totPvdaAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchRentalRes")
    public record SearchRentalRes(
        String sellTpCd,
        String sellTpDtlCd,
        String pdCd,
        String pdNm,
        String sellInflwChnlDtlCd,
        String slRcogDt,
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sapPdDvCd,
        String rentalRgstCost,
        String rentalRgstCostSpl,
        String rentalRgstCostVat,
        String nomSlAmt,
        String splAmt,
        String vat,
        String totSlAmt,
        String totSplAmt,
        String totVat
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String sellTpCd,
        String sellTpDtlCd,
        String pdCd,
        String pdNm,
        String sellInflwChnlDtlCd,
        String slRcogDt,
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sapPdDvCd,
        String sellAmt,
        String sellSplAmt,
        String sellAmtVat,
        String filSellAmt,
        String filSellSplAmt,
        String filSellAmtVat,
        String totSellAmt,
        String totSellSplAmt,
        String totSellAmtVat
    ) {}
}
