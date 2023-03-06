package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfeaActivityPeopleDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // EDU 실활동인원관리 Search Request Dto
    @Builder
    @ApiModel("WfeaActivityPeopleDto-SearchReq")
    public record SearchReq(
        String perfDt,
        String ogType,
        String rsbType,
        String confirmDiv,
        String ogLevel1,
        String ogLevel2,
        String ogLevel3
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // EDU 실활동인원관리 Search Result Dto
    @ApiModel("WfeaActivityPeopleDto-SearchRes")
    public record SearchRes(
    ) {
    }
}
