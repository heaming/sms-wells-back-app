package com.kyowon.sms.wells.web.fee.aggregate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfeaFeeBaseAmountDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    //  WELLS 수수료 기준금액 체크리스트 Search Request Dto
    @Builder
    @ApiModel("WfeaFeeBaseAmtCheckListDto-SearchReq")

    public record SearchReq(
        //실적년월
        String perfYm,
        //조직유형
        String ogtp
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 수수료 기준금액 체크리스트 Search Result Dto
    @ApiModel(value = "WfeaFeeBaseAmtCheckListDto-SearchRes")
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
        String col21,
        String col22,
        String col23
    ) {}

}
