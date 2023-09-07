package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfeaFeeMeetingAttendanceDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 미팅집계 Search Request Dto

    @Builder

    @ApiModel(value = "WfeaFeeMeetingAttendanceDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String ogTpCd, /* 조직유형 */
        @NotBlank
        String perfYm, /* 실적년월 */
        @NotBlank
        String rsbTpCd, /* 직책유형코드 */
        @NotBlank
        String feeTcntDvCd /* 수수료차수구분코드 */
    ) {}
}
