package com.kyowon.sms.wells.web.fee.simulation.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfefFeeDeductionPresentStateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 수수료 공제현황 Search Request Dto
    @Builder
    @ApiModel("WfefFeeDeductionPresentStateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String ogTpCd,
        String rsbDvCd,
        String ogLevl1,
        String ogLevl2,
        String ogLevl3

    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 수수료 공제현황 Search Result Dto
    @ApiModel("WfefFeeDeductionPresentStateDto-SearchRes")
    public record SearchRes(
        String level2Nm,
        String level3Nm,
        String level4Nm,
        String prtnrNm,
        String prtnrNo,
        String rsbDvNm,
        String dsbOjAmt,
        String ddtnAft,
        String ddctam,
        String rdsTax,
        String einsr,
        String telPc,
        String redf,
        String dlqRedf,
        String pnpyam,
        String buDdtn,
        String mutuRedf,
        String inddInsr,
        String rdsBlam,
        String pnpyamBlam
    ) {
    }
}
