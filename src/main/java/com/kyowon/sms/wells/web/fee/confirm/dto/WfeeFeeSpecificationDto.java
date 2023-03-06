package com.kyowon.sms.wells.web.fee.confirm.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfeeFeeSpecificationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // EDU 수수료 지급명세서 Search Request Dto
    @Builder
    @ApiModel("WfeeFeeSpecificationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfDt,
        String orgType,
        String positionType,
        String ogLevel1,
        String ogLevel2,
        String ogLevel3
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // EDU 수수료 지급명세서 Search Result Dto
    @ApiModel("WfeeFeeSpecificationDto-SearchRes")
    public record SearchRes(
    ) {
    }
}
