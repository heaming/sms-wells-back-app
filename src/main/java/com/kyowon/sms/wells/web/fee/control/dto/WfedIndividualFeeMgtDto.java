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
        String emplNm,
        String blg,
        String rsb,
        String metg,
        String qlf,
        String indvElhm,
        String indvElhmExcp,
        String indvAdp,
        String ogElhm,
        String ogElhmExcp,
        String ogAdp,
        String indvChng,
        String indvMutu,
        String ogMutu,
        String intbsSum,
        String ddtnSum,
        String aclDsbAmt
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarFeeRes")
    public record FindPlarFeeRes(
        String w01,
        String w02,
        String w03,
        String w04,
        String w05,
        String w21,
        String w23,
        String w24,
        String w25,
        String w11,
        String w12,
        String w13,
        String w15,
        String w16,
        String w17,
        String w22,
        String w20,
        String w30
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarDeductionRes")
    public record FindPlarDeductionRes(
        String rds,
        String erntx,
        String rsdntx,
        String hirInsr,
        String buDdtn,
        String pnpyam
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarControlRes")
    public record SearchPlarControlRes(
        String div,
        String item,
        String ctrBf,
        String ctrAf,
        String rsn,
        String ctrDtm,
        String ctrr
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerEntrpRes")
    public record FindMngerEntrpRes(
        String emplNm,
        String blg,
        String rsb,
        String metg,
        String welsMnger,
        String qlf,
        String elhmRental,
        String elhmSnglPmnt,
        String elhmFxam,
        String elhmExcpSnglPmnt,
        String elhmExcpFxam,
        String indvCount,
        String ogElhmRental,
        String ogElhmSnglPmnt,
        String ogElhmExcpSnglPmnt,
        String ogCount,
        String ogNewSell,
        String indvNewSell,
        String bsPdAccCnt,
        String w1Count,
        String w2Count

    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerBeforeServiceRes")
    public record SearchMngerBeforeServiceRes(
        String cdNm,
        String cnt1,
        String cnt2,
        String amt1,
        String cnt3,
        String cnt4,
        String amt2,
        String sumAmt,
        String cntRat
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerFeeRes")
    public record FindMngerFeeRes(
        String sd04,
        String sd05,
        String sd06,
        String sd08,
        String sd09,
        String sd10,
        String sd11,
        String sd12,
        String sd13,
        String sd14,
        String sd15,
        String sd16,
        String sd17,
        String sd18,
        String sd19,
        String sd22,
        String sd23,
        String sd26,
        String sd29,
        String sd31,
        String sd34,
        String sd36,
        String sd39,
        String sd40,
        String sd42,
        String sd43,
        String sd48,
        String sd49,
        String sd50,
        String md62,
        String md63,
        String md95
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerDeductionRes")
    public record FindMngerDeductionRes(
        String rds,
        String erntx,
        String rsdntx,
        String hirInsr,
        String buDdtn,
        String pnpyam
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerControlRes")
    public record SearchMngerControlRes(
        String div,
        String item,
        String ctrBf,
        String ctrAf,
        String rsn,
        String ctrDtm,
        String ctrr
    ) {}
}
