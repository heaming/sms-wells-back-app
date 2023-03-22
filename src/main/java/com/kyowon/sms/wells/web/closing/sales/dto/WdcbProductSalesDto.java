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
        String col20,
        String col21,
        String col22,
        String col23,
        String col24,
        String col25
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황(일시불, 금융리스, 정기배송 / 상품) Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchSinglePdRes")
    public record SearchSinglePdRes(
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
        String col20,
        String col21,
        String col22,
        String col23,
        String col24,
        String col25
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchRentalAgrgRes")
    public record SearchRentalAgrgRes(
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
        String col16
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchRentalPdRes")
    public record SearchRentalPdRes(
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
        String col16
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDto-SearchMembershipRes")
    public record SearchMembershipRes(
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
        String col14
    ) {}
}
