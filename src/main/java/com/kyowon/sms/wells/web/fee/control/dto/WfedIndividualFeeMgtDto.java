package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 개인별 수수료 관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.03
 */
public class WfedIndividualFeeMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerReq")
    public record SearchMngerReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 개인별 수수료 관리 Search Result Dto

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstEntrpRes")
    public record FindHmstEntrpRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstBasicRes")
    public record FindHmstBasicRes(
        int col1,
        int col2,
        String col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstTotalSumRes")
    public record FindHmstTotalSumRes(
        int col1,
        int col2,
        int col3
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstFeeRes")
    public record FindHmstFeeRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8,
        int col9,
        int col10,
        int col11,
        int col12,
        int col13,
        int col14,
        int col15,
        int col16,
        int col17,
        int col18,
        int col19,
        int col20
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstDeductionRes")
    public record FindHmstDeductionRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchHmstControlRes")
    public record SearchHmstControlRes(
        String col1,
        String col2,
        int col3,
        int col4,
        String col5,
        String col6,
        String col7
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarEntrpRes")
    public record FindPlarEntrpRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarBasicRes")
    public record FindPlarBasicRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8,
        int col9
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarTotalSumRes")
    public record FindPlarTotalSumRes(
        int col1,
        int col2,
        int col3
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarFeeRes")
    public record FindPlarFeeRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8,
        int col9,
        int col10,
        int col11,
        int col12,
        int col13,
        int col14,
        int col15,
        int col16,
        int col17,
        int col18
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarDeductionRes")
    public record FindPlarDeductionRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarControlRes")
    public record SearchPlarControlRes(
        String col1,
        String col2,
        int col3,
        int col4,
        String col5,
        String col6,
        String col7
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerEntrpRes")
    public record FindMngerEntrpRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerBasicRes")
    public record FindMngerBasicRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8,
        int col9,
        int col10,
        int col11,
        int col12,
        int col13,
        int col14,
        int col15
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerBeforeServiceRes")
    public record SearchMngerBeforeServiceRes(
        String col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8,
        int col9
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerTotalSumRes")
    public record FindMngerTotalSumRes(
        int col1,
        int col2,
        int col3
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerFeeRes")
    public record FindMngerFeeRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8,
        int col9,
        int col10,
        int col11,
        int col12,
        int col13,
        int col14,
        int col15,
        int col16,
        int col17,
        int col18,
        int col19,
        int col20,
        int col21,
        int col22,
        int col23,
        int col24,
        int col25,
        int col26,
        int col27,
        int col28,
        int col29,
        int col30,
        int col31,
        int col32
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerDeductionRes")
    public record FindMngerDeductionRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerControlRes")
    public record SearchMngerControlRes(
        String col1,
        String col2,
        int col3,
        int col4,
        String col5,
        String col6,
        String col7
    ) {}
}
