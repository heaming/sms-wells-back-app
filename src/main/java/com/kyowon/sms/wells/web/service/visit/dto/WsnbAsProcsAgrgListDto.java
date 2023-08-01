package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0304M01 A/S 처리 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.21
 */
public class WsnbAsProcsAgrgListDto {
    @ApiModel(value = "WsnbAsProcsAgrgListDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String taskType,
        String pdGrpCd,
        String pdCd,
        String chkYn
    ) {}

    @ApiModel(value = "WsnbAsProcsAgrgListDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String cntGb,
        int cntTotal,
        int cnt01,
        int cnt02,
        int cnt03,
        int cnt04,
        int cnt05,
        int cnt06,
        int cnt07,
        int cnt08,
        int cnt09,
        int cnt10,
        int cnt11,
        int cnt12,
        int cnt13,
        int cnt14,
        int cnt15,
        int cnt16,
        int cnt17,
        int cnt18,
        int cnt19,
        int cnt20,
        int cnt21,
        int cnt22,
        int cnt23,
        int cnt24,
        int cnt25,
        int cnt26,
        int cnt27,
        int cnt28,
        int cnt29,
        int cnt30,
        int cnt31
    ) {}
}
