package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * BS처리율 조정 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.02
 */
public class WfedBsProcessingRateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // BS처리율 조정 관리 Search Request Dto
    @ApiModel(value = "WfedBsProcessingRateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        String prtnrNo
    ) {}

    @ApiModel(value = "WfedBsProcessingRateDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String prtnrNo,
        @NotBlank
        String rowState,
        @NotNull
        double sv01999909, /*처리율*/
        @NotNull
        double sv01999910 /*수정비율*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // BS처리율 조정 관리 Search Result Dto
    @ApiModel(value = "WfedBsProcessingRateDto-SearchRes")
    public record SearchRes(
        String baseYm,
        String ogTpCd,
        String prtnrNo,
        String ogCd,
        String prtnrKnm,
        String rsbDvNm,
        Integer sv01999901, /*관리건수*/
        Integer sv01999902, /*방문건수*/
        Integer sv01999903, /*전월건수*/
        Integer totSvCnt, /*완료건수*/
        Double sv01999909, /*비율*/
        Double sv01999910 /*수정비율*/
    ) {}

}
