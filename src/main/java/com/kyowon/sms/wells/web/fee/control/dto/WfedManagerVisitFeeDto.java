package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfedManagerVisitFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WfedManagerVisitFeeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String inqrDv,

        String ogLevl1,
        String ogLevl2,
        String ogLevl3,
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WfedManagerVisitFeeDto-SearchRes")
    public record SearchRes(

        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        int col7,
        int col8,
        String col9,
        String col10,
        String col11

    ) {}
}
