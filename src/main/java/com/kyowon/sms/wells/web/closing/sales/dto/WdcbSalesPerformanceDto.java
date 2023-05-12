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
        String cntrNo,
        @NotBlank
        String cntrSn,
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
        String sellTpCd,
        String sellTpDtlCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기본정보(렌탈) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRentalRes")
    public record SearchRentalRes(
        String sellTpDtlCd,
        String cntrDtlNo,
        String cstKnm,
        String pdNm,
        String lcmgu2Nm,
        String lcetc8Nm,
        String cntrDt,
        String slRcogDt,
        String canDt,
        String fshDt,
        String redfDt,
        String adsbDt,
        String lcgub3,
        String sellDscDvCd,
        String rentalRgstCost,
        String rentalPtrm,
        String slAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String slDpAggAmt,
        String thmUcBlam,
        String thmOcDlqAmt,
        String slStpAmt,
        String eotDlqAddAmt,
        String dfaProcsAmt,
        String dfaAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기본정보(리스) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchLeaseRes")
    public record SearchLeaseRes(
        String sellTpDtlCd,
        String cntrDtlNo,
        String cstKnm,
        String pdNm,
        String lcmgu2Nm,
        String lcetc8Nm,
        String cntrDt,
        String slRcogDt,
        String canDt,
        String fshDt,
        String redfDt,
        String adsbDt,
        String lcgub3,
        String sellDscDvCd,
        String rentalRgstCost,
        String rentalPtrm,
        String slAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String slDpAggAmt,
        String thmUcBlam,
        String thmOcDlqAmt,
        String slStpAmt,
        String eotDlqAddAmt,
        String dfaProcsAmt,
        String dfaAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기본정보(멤버십) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String sellTpDtlCd,
        String cntrDtlNo,
        String cstKnm,
        String pdNm,
        String sellDscDvCd,
        String cntrDt,
        String slRcogDt,
        String canDt,
        String fshDt,
        String redfDt,
        String adsbDt,
        String rentalRgstCost,
        String dscAmt,
        String slAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String slDpAggAmt,
        String thmUcBlam,
        String thmOcDlqAmt,
        String dfaProcsAmt,
        String dfaAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기본정보(정기배송) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRegularRes")
    public record SearchRegularRes(
        String sellTpDtlCd,
        String cntrDtlNo,
        String cstKnm,
        String cntrDt,
        String slRcogDt,
        String pdNm,
        String lcck04,
        String canDt,
        String sppDtm,
        String sellDscDvCd,
        String slAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String slDpAggAmt,
        String thmUcBlam,
        String eotUcAmt,
        String thmOcDlqAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기본정보(렌탈) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRentalMonthlyRes")
    public record SearchRentalMonthlyRes(
        String cntrDtlNo,
        String slClYm,
        String slStpYn,
        String rentalTn,
        String prmMcn,
        String lcam16,
        String lcam4446,
        String lciamt,
        String lcam3t,
        String thmUcBlam,
        String thmOcDlqAmt,
        String dlqMcn,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqDpSumAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt,
        String lcam96
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기본정보(리스) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchLeaseMonthlyRes")
    public record SearchLeaseMonthlyRes(
        String cntrDtlNo,
        String slClYm,
        String slStpYn,
        String rentalTn,
        String prmMcn,
        String lcam16,
        String lcam4446,
        String lciamt,
        String lcam3t,
        String thmUcBlam,
        String thmOcDlqAmt,
        String dlqMcn,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqDpSumAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt,
        String lcam96
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기본정보(멤버십) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchMembershipMonthlyRes")
    public record SearchMembershipMonthlyRes(
        String cntrDtlNo,
        String slClYm,
        String rentalTn,
        String lcmgub,
        String thmSlSumAmt,
        String lciamt,
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
    // 기본정보(정기배송) Search Result Dto
    @ApiModel("WdcbSalesPerformanceDto-SearchRegularMonthlyRes")
    public record SearchRegularMonthlyRes(
        String cntrDtlNo,
        String rentalTn,
        String slStpYn,
        String thmSlSumAmt,
        String thmAtamDpAmt,
        String eotAtam,
        String thmUcBlam,
        String thmOcDlqAmt,
        String eotDlqAmt
    ) {}
}
