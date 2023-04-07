package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbProductSalesDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Dto
    @ApiModel("WdcbProductSalesDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseDtmnFrom,
        @NotBlank
        String baseDtmnTo,
        String taskDiv,
        String inqrDv,
        String sellTp,
        String sellDv
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황(일시불, 금융리스, 정기배송 / 집계) Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchSingleAgrgRes")
    public record SearchSingleAgrgRes(
        String slDt,
        String sellTpCd,
        String col1,
        String normalSellQty,
        String normalMpyAmt,
        String normalMmIstmPcamAmt,
        String normalVat,
        String normalPurSlAmt,
        String chgSellQty,
        String chgMpyAmt,
        String chgMmIstmPcamAmt,
        String chgVat,
        String chgPurSlAmt,
        String canSellQty,
        String canMpyAmt,
        String canMmIstmPcamAmt,
        String canVat,
        String canPurSlAmt,
        String sumSellQty,
        String sumMpyAmt,
        String sumMmIstmPcamAmt,
        String sumVat,
        String sumPurSlAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황(일시불, 금융리스, 정기배송 / 상품) Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchSinglePdRes")
    public record SearchSinglePdRes(
        String slDt,
        String sellTpCd,
        String pdDtlCd,
        String pdNm,
        String col1,
        String normalSellQty,
        String normalMpyAmt,
        String normalMmIstmPcamAmt,
        String normalVat,
        String normalPurSlAmt,
        String chgSellQty,
        String chgMpyAmt,
        String chgMmIstmPcamAmt,
        String chgVat,
        String chgPurSlAmt,
        String canSellQty,
        String canMpyAmt,
        String canMmIstmPcamAmt,
        String canVat,
        String canPurSlAmt,
        String sumSellQty,
        String sumMpyAmt,
        String sumMmIstmPcamAmt,
        String sumVat,
        String sumPurSlAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchRentalAgrgRes")
    public record SearchRentalAgrgRes(
        String slDt,
        String sellTpCd,
        String rgstQty,
        String rgstCost,
        String rgstVat,
        String rgstSlAmt,
        String rentalQty,
        String rentalCost,
        String rentalVat,
        String rentalSlAmt,
        String sumQty,
        String sumCost,
        String sumVat,
        String sumSlAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchRentalPdRes")
    public record SearchRentalPdRes(
        String slDt,
        String sellTpCd,
        String pdDtlCd,
        String pdNm,
        String rgstQty,
        String rgstCost,
        String rgstVat,
        String rgstSlAmt,
        String rentalQty,
        String rentalCost,
        String rentalVat,
        String rentalSlAmt,
        String sumQty,
        String sumCost,
        String sumVat,
        String sumSlAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String slDt,
        String sellTpCd,
        String sspcsSellQty,
        String sspcsMpyAmt,
        String sspcsVat,
        String sspcsSlAmt,
        String filtSellQty,
        String filtMpyAmt,
        String filtVat,
        String filtSlAmt,
        String sumSellQty,
        String sumMpyAmt,
        String sumVat,
        String sumSlAmt
    ) {}
}
