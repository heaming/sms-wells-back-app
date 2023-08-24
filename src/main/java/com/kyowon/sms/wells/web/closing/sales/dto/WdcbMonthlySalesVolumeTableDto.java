package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbMonthlySalesVolumeTableDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String sellTpCd,
        String slStartDt,
        String slEndDt,
        String sppMthdTpCd,
        String sellInflwChnlDtlCd,
        String copnDvCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchRes")
    public record SearchRentalRes(
        String sapMatCd,
        String matPdCd,
        String pdCd,
        String pdNm,
        String svPdTpCd,
        String cnt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchRes")
    public record SearchPaymentRes(
        String sapMatCd,
        String matPdCd,
        String basePdCd,
        String pdNm,
        String stlmTpCd1,
        String stlmTpCd2,
        String tot
    ) {}

}
