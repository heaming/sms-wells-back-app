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
        String taskDiv,
        String sellTp,
        String sellDv,
        String cntrDtlNo,
        String cstNo,
        String sapSlpno
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-SingleSearchRes")
    public record SingleSearchRes(
        String taskDiv,
        String sellTpCd,
        String pdDtlCd,
        String sellDv,
        String slDt,
        String cntrDtlNo,
        String cstNo,
        String cstKnm,
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
    @ApiModel("WdcbProductSalesDetailDto-RentalSearchRes")
    public record RentalSearchRes(
        String taskDiv,
        String sellTpCd,
        String pdDtlCd,
        String sellDv,
        String slDt,
        String cntrDtlNo,
        String cstNo,
        String cstKnm,
        String rgstQty,
        String rgstMpyAmt,
        String rgstVat,
        String rgstPurSlAmt,
        String rentalQty,
        String rentalMpyAmt,
        String rentalVat,
        String rentalPurSlAmt,
        String sumQty,
        String sumMpyAmt,
        String sumVat,
        String sumPurSlAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-MembershipSearchRes")
    public record MembershipSearchRes(
        String taskDiv,
        String sellTpCd,
        String pdDtlCd,
        String sellDiv,
        String slDt,
        String cntrDtlNo,
        String cstNo,
        String cstKnm,
        String sspcsQty,
        String sspcsMpyAmt,
        String sspcsVat,
        String sspcsPurSlAmt,
        String filtQty,
        String filtMpyAmt,
        String filtVat,
        String filtPurSlAmt,
        String sumQty,
        String sumMpyAmt,
        String sumVat,
        String sumPurSlAmt
    ) {}
}
