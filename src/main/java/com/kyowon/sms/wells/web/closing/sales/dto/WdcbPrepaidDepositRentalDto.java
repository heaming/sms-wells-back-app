package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbPrepaidDepositRentalDto {
    // *********************************************************
    // Search Dto
    // *********************************************************
    // 선입금 렌탈 조회 Search Dto
    @ApiModel("WdcbPrepaidDepositRentalDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrDtlNo,
        @NotBlank
        String slClYmFrom,
        @NotBlank
        String slClYmTo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 선입금 렌탈 조회 Search Result Dto
    @ApiModel("WdcbPrepaidDepositRentalDto-SearchRes")
    public record SearchRes(
        String rentalTn,
        String prmMcn,
        String prmDpAmt,
        String prmPrd,
        String rentalAmt,
        String thmAtamDpAmt,
        String col7,
        String prmBlamEotAmt
    ) {}
}
