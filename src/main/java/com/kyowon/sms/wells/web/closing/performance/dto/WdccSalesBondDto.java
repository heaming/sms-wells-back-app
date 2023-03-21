package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccSalesBondDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 searchReq
    @ApiModel(value = "WdccDelinquentDto-SearchReq")
    public record SearchReq(
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 searchRes
    @ApiModel(value = "WdccDelinquentDto-SearchRes")
    public record SearchRes(
    ) {
    }
}
