package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WdcdOperatingCostMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtDto-CodeReq")
    public record CodeReq(
        String ogTpCd
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtDto-CodeRes")
    public record CodeRes(
        String dgr2LevlOgId,
        String dgr2LevlOgNm,
        String dgr3LevlOgId,
        String dgr3LevlOgNm,
        String dgr4LevlOgId,
        String dgr4LevlOgNm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String registration,
        String entrpDvCd
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @ApiModel(value = "WwdcdOperatingCostMgtDto-AmountRes")
    public record AmountRes(
        String befJanAmt,
        String cardThmDsb,
        String cardLimAmt,
        String cardUseAmt,
        String cardCanAmt,
        String cardResAmt
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SummaryRes")
    public record SummaryRes(
        String aprDt,
        String usrSmryCn,
        String opcsWhtxCfdcApnFileId
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
