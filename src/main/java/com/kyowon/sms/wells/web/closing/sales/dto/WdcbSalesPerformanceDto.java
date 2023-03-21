package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbSalesPerformanceDto {
    // *********************************************************
    // Search Dto
    // *********************************************************
    // 매출 상세정보 Search Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrDtlNo,
        @NotBlank
        String baseYearFrom,
        @NotBlank
        String baseYearTo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRes")
    public record SearchRes(
        String sellTpCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRentalRes")
    public record SearchRentalRes(
        String sellTpCd,
        String cntrDtlNo,
        String cstKnm,
        String pdNm,
        String col5,
        String col6,
        String cntrRcpFshDtm,
        String slDt,
        String cntrCanDtm,
        String col10,
        String redfDt,
        String adsbDt,
        String dscTpCd,
        String dpTpCd,
        String rentalRgstCost,
        String rentalPtrm,
        String slAggAmt,
        String col18,
        String col19,
        String slDpAggAmt,
        String col21,
        String thmOcDlqAmt,
        String col23,
        String eotDlqAddAmt,
        String dfaAmt,
        String dfaDpAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String sellTpCd,
        String cntrDtlNo,
        String cstKnm,
        String pdNm,
        String dpTpCd,
        String cntrDt,
        String cntrCnfmDtm,
        String cntrCanDtm,
        String col9,
        String col10,
        String col11,
        String sellAmt,
        String dscAmt,
        String slAggAmt,
        String col15,
        String col16,
        String slDpAggAmt,
        String thmUcBlam,
        String thmOcDlqAmt,
        String dfaAmt,
        String dfaDpAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRegularRes")
    public record SearchRegularRes(
        String sellTpCd,
        String cntrDtlNo,
        String cstKnm,
        String cntrRcpFshDtm,
        String slDt,
        String col6,
        String col7,
        String col8,
        String sppDtm,
        String dpTpCd,
        String slAggAmt,
        String col12,
        String col13,
        String slDpAggAmt,
        String thmUcBlam,
        String col16,
        String thmOcDlqAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRentalMonthlyRes")
    public record SearchRentalMonthlyRes(
        String cntrDtlNo,
        String slDt,
        String cntrDtlStatCd,
        String col4,
        String col5,
        String prmMcn,
        String thmSlSumAmt,
        String col8,
        String col9,
        String eotAtam,
        String thmUcBlam,
        String thmOcDlqAmt,
        String dlqMcn,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqDpSumAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchMembershipMonthlyRes")
    public record SearchMembershipMonthlyRes(
        String cntrDtlNo,
        String slDt,
        String col3,
        String col4,
        String thmSlSumAmt,
        String thmAtamDpAmt,
        String eotAtam,
        String thmOcDlqAmt,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqDpSumAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRegularMonthlyRes")
    public record SearchRegularMonthlyRes(
        String cntrDtlNo,
        String col2,
        String slDt,
        String col4,
        String thmSlSumAmt,
        String thmAtamDpAmt,
        String eotAtam,
        String thmUcBlam,
        String col8,
        String thmOcDlqAmt
    ) {}
}
