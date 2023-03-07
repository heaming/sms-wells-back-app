package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 신채널 수수료 생성관리 Dto
 * </pre>
 *
 * @author haejin.lee
 * @since 2023-03-02
 */
public class WfeaNchnFeeDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 신채널 수수료 생성관리-실적집계 Save Request Dto
    @Builder
    @ApiModel(value = "WfeaNchnFeeDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String perfYm
    ) {}
}
