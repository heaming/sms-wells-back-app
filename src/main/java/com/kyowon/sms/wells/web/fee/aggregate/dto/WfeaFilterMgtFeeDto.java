package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfeaFilterMgtFeeDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 필터관리수수료 현황 Search Request Dto
    @Builder
    @ApiModel("WfeaFilterMgtFeeDto-SearchReq")

    public record SearchReq(
        @NotBlank
        String perfYm, //실적년월
        String param1, //번호
        @NotBlank
        String param2 //업무구분

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 필터관리수수료 현황 Search Result Dto
    @ApiModel(value = "WfeaFilterMgtFeeDto-SearchRes")
    public record SearchRes(
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
        String col21
    ) {}
}
