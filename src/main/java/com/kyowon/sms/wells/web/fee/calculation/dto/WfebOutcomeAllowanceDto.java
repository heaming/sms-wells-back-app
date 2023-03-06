package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfebOutcomeAllowanceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 성과수당현황 Search Request Dto
    @Builder
    @ApiModel("WfebOutcomeAllowanceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfDt,
        String leaderDiv,
        String levelDiv
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 성과수당현황 Search Result Dto
    @ApiModel("WfebOutcomeAllowanceDto-SearchRes")
    public record SearchRes(
    ) {
    }
}
