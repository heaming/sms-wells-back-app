package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdccProductAccountDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Dto
    @ApiModel("WdccProductAccountDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYmFrom,
        @NotBlank
        String baseYmTo,
        String sellTpCd,
        String sellTpDtlCd,
        String ogTpCd,
        String prdtCateHigh,
        String prdtCateMid
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchTotalRes")
    public record SearchTotalRes(
        String baseYm,
        String sellTpCd,
        String sellTpDtlCd,
        String agrgCt1,
        String agrgCt2,
        String agrgCt3,
        String agrgCt4,
        String agrgCt5,
        String agrgCt6
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchProductRes")
    public record SearchProductRes(
        String baseYm,
        String sellTpCd,
        String sellTpDtlCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        String pdNm,
        String agrgCt1,
        String agrgCt2,
        String agrgCt3,
        String agrgCt4,
        String agrgCt5,
        String agrgCt6
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchExcelRes")
    public record SearchExcelRes(
        /* TODO: 상세내역 다운로드 추후 수정 */
        String baseYm,
        String sellTpCd,
        String sellTpDtlCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        String pdNm,
        String agrgCt1,
        String agrgCt2,
        String agrgCt3,
        String agrgCt4,
        String agrgCt5,
        String agrgCt6
    ) {}
}
