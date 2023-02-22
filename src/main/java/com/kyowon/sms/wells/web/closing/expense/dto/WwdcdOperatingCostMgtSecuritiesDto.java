package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WwdcdOperatingCostMgtSecuritiesDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 정산(유가증권) / 정산대상
    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtMscrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }

    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtMscrDto-SearchRes")
    public record SearchRes(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }

    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtMscrDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 정산(유가증권) / 원천세 정산 내역
}
