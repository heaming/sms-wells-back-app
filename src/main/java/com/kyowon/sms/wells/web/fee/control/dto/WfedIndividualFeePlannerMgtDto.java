package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * P조직 개인별 수수료 관리
 * </pre>
 *
 * @author gs.piit272
 * @since 2023.10.17
 */
public class WfedIndividualFeePlannerMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 개인별 수수료 관리 Search Result Dto

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarEntrpRes")
    public record FindPlarEntrpRes(
        String emplNm,
        String blg,
        String rsb,
        String metg,
        String qlf,
        String intbsSum,
        String ddtnSum,
        String aclDsbAmt
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarEtcRes")
    public record SearchPlarEtcRes(
        String indvElhm,
        String indvElhmExcp,
        String indvAdp,
        String indvElhmChdvc,
        String indvMutu,
        String ogElhm,
        String ogElhmExcp,
        String ogAdp,
        String ogMutu
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarFeeRes")
    public record SearchPlarFeeRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarDeductionRes")
    public record FindPlarDeductionRes(
        String rds,
        String erntx,
        String rsdntx,
        String hirInsr,
        String buDdtn,
        String pnpyam,
        String inddInsr
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
}
