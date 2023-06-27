package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbRentalPerformanceCprAsDto {

    @ApiModel(value = "WsnbRentalPerformanceCprAsDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String baseY,

        String svType,

        String badDivide,

        String pdGrp,

        String pdCd

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnbRentalPerformanceCprAsDto-SearchRes")
    public record SearchRes(
        String atcNm,
        String dYear,
        String m01,
        String m02,
        String m03,
        String m04,
        String m05,
        String m06,
        String m07,
        String m08,
        String m09,
        String m10,
        String m11,
        String m12,

        String totalCnt,

        String maxVal,

        String minVal,

        String avgVal

    ) {}
}
