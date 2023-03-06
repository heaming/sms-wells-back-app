package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * WM 방문실적 수수료관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.03
 */
public class WfedWelsMngerBsFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WfedWelsMngerBsFeeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}

    @ApiModel(value = "WfedWelsMngerBsFeeDto-SaveFeeReq")
    public record SaveFeeReq(
        String col1,
        int col2,
        int col3,
        int col4
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 개인별 수수료 관리 Search Result Dto

    @ApiModel(value = "WfedWelsMngerBsFeeDto-FindHumanRes")
    public record FindHumanRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6
    ) {}

    @ApiModel(value = "WfedWelsMngerBsFeeDto-SearchVisitRes")
    public record SearchVisitRes(
        String col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8
    ) {}
    @ApiModel(value = "WfedWelsMngerBsFeeDto-SearchFeeRes")
    public record SearchFeeRes(
        String col1,
        int col2,
        int col3,
        int col4
    ) {}

    @ApiModel(value = "WfedWelsMngerBsFeeDto-FindVisitAgrgRes")
    public record FindVisitAgrgRes(
        int col1,
        String col2,
        int col3,
        int col4,
        int col5,
        int col6
    ) {}
    @ApiModel(value = "WfedWelsMngerBsFeeDto-FindFeeAgrgRes")
    public record FindFeeAgrgRes(
        int col1,
        int col2
    ) {}
}
