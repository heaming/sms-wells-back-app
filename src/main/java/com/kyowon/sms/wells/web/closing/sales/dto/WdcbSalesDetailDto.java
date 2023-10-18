package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbSalesDetailDto {
    @ApiModel(value = "WdcbSalesDetailDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String slRcogDt
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-SearchRentalRes")
    public record SearchRentalRes(
        String rentalRgstCost,
        String dscAmt,
        String cntrTam,
        String sellTpDtlCd,
        String ojDtlCntr1,
        String ojDtlCntr2,
        String rentalAmt,
        String rentalDscAmt,
        String cntrPtrm,
        String rentalAmt2,
        String rentalDscAmt2,
        String rentalPtrm2,
        String dutyUseMcn,
        String sellDscDvCd,
        String sellDscTpCd,
        String svPrd,
        String ojDtlCntr3,
        String slRcogDt,
        String reqdDtm,
        String canDt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String sellAmt,
        String stplPtrm,
        String svPrd1,
        String sellDscTpCd,
        String dscAmt,
        String cntrPdStrtdt,
        String canDt,
        String sppDuedt,
        String vstNmnN,
        String svPrd2
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-SearchSingleRes")
    public record SearchSingleRes(
        String frisuBfsvcPtrmN,
        String frisuAsPtrmN1,
        String frisuAsPtrmN2,
        String sellAmt,
        String fnlAmt,
        String cntrAmt1,
        String cntrAmt2,
        String istmPcamAmt,
        String mmIstmAmt,
        String istmMcn
    ) {}
}
