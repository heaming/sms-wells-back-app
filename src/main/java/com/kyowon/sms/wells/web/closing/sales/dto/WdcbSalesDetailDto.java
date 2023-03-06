package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbSalesDetailDto {
    // *********************************************************
    // Search Dto
    // *********************************************************
    // 매출 상세정보 Search Dto
    @ApiModel("WdcbSalesDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrDtlNo,
        @NotBlank
        String taskDiv
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-RentalSearchRes")
    public record RentalSearchRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8,
        String col9,
        String col10,
        String col11,
        String col12,
        String col13,
        String col14,
        String col15,
        String col16,
        String col17,
        String col18,
        String col19,
        String col20
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-MembershipSearchRes")
    public record MembershipSearchRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8,
        String col9,
        String col10
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-SingleSearchRes")
    public record SingleSearchRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8,
        String col9,
        String col10
    ) {}
}
