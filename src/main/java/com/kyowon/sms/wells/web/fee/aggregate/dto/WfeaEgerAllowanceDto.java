package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 엔지니어 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfeaEgerAllowanceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 엔지니어 실적 집계 Search Request Dto
    @ApiModel(value = "WfeaEgerAwPerfAgrgMgtDto-SearchReq")
    public record SearchReq(

    ) {}

    @Builder
    @ApiModel(value = "WfeaEgerAwPerfAgrgMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String perfYm,
        String rsbTp
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfeaEgerAwPerfAgrgMgtDto-SearchRes")
    public record SearchRes(

    ) {}
}
