package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbSalesDetailDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-SearchRentalRes")
    public record SearchRentalRes(
        String rentalRgstCost,
        String rentalAmt,
        String col1,
        String reqdDtm,
        String col2,
        String rentalDscAmt,
        String col3,
        String col4,
        String istmAmt,
        String rentalPtrm,
        String col5,
        String col6,
        String rentalAmt2,
        String col7,
        String col8,
        String rentalDscAmt2,
        String col9,
        String col10,
        String rentalPtrm2,
        String slDt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String sellAmt,
        String col1,
        String col2,
        String col3,
        String istmMcn,
        String dscAmt,
        String col4,
        String col5,
        String col6,
        String col7
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesDetailDto-SearchSingleRes")
    public record SearchSingleRes(
        String frisuYn,
        String sellAmt,
        String tkAmt,
        String istmMcn,
        String col1,
        String cntrTam,
        String istmAmt,
        String col2,
        String subscAmt,
        String mmIstmAmt
    ) {}
}
