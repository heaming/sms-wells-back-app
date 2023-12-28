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
        String perfYm, /*실적년월*/
        String prtnrNo /*파트너번호*/
    ) {}

    @ApiModel(value = "WfedBsProcessingRateDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYm, /*기준년월*/
        @NotBlank
        String ogTpCd, /*조직유형코드*/
        @NotBlank
        String prtnrNo, /*파트너번호*/
        @NotBlank
        String rowState, /*행상태*/
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
        String baseYm, /*기준년월*/
        String ogTpCd, /*조직유형코드*/
        String prtnrNo, /*파트너번호*/
        String ogCd, /*조직코드*/
        String prtnrKnm, /*파트너명*/
        String rsbDvCd, /*직책구분코드*/
        Integer sv01999901, /*관리건수*/
        Integer sv01999902, /*방문건수*/
        Integer sv01999903, /*전월건수*/
        Integer totSvCnt, /*완료건수*/
        Double sv01999909, /*비율*/
        Double sv01999910 /*수정비율*/
    ) {}

}
