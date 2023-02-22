package com.kyowon.sms.wells.closing.expense.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WwdcdOperatingCostMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SearchRes")
    public record SearchRes(
        String col1,
        String col2,
        String col3
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 유가증권 원천세 확인서
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 유가증권 원천세 확인서
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SaveRes")
    public record SaveRes(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 유가증권 원천세 확인서
    @ApiModel(value = "WwdcdOperatingCostMgtDto-FileReq")
    public record FileReq(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }
}
