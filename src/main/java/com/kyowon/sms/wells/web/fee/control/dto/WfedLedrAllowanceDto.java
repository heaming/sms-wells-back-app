package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 단장 수당 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfedLedrAllowanceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 단장 수당 관리 Search Request Dto
    @ApiModel(value = "WfedLeaderAllowanceMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        String rsbTp,
        String inqrDv,
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 단장 수당 관리 Search Result Dto
    @ApiModel(value = "WfedLeaderAllowanceMgtDto-SearchRes")
    public record SearchRes(
        String col1, /*실적년월*/
        String col2, /*직책유형*/
        String col3, /*소속명*/
        String col4, /*소속*/
        String col5, /*성명*/
        int col6, /*번호*/
        int col7, /*지점수*/
        int col8, /*기본급*/
        int col9, /*시간외수당*/
        int col10, /*직책수당*/
        int col11, /*고정급계*/
        int col12, /*목표*/
        int col13, /*실적*/
        int col14, /*달성률*/
        int col15, /*목표달성수당*/
        int col16, /*등급*/
        int col17, /*평가수당*/
        int col18, /*업적수당계*/
        int col19, /*우수사업부*/
        int col20, /*인센티브*/
        int col21, /*원거리출퇴근*/
        int col22, /*월세지원*/
        int col23, /*강사료*/
        int col24, /*기타*/
        int col25, /*기타수당계*/
        int col26, /*고정급+업적수당*/
        int col27, /*지급계*/
        int col28, /*건강보험*/
        int col29, /*장기요양보험*/
        int col30, /*국민연금*/
        int col31, /*고용보험*/
        int col32, /*소득세*/
        int col33, /*주민세*/
        int col34, /*비정도영업조치금*/
        int col35, /*기타*/
        int col36, /*공제계*/
        int col37 /*총지급액*/
    ) {}

}
