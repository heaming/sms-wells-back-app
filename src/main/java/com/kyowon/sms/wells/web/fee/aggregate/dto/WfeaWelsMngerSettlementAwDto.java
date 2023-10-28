package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 웰스매니저 정착수당
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.06.07
 */
public class WfeaWelsMngerSettlementAwDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 웰스매니저 정착수당 Search Request Dto
    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String tcntDvCd,
        String prtnrNo,
        String prtnrKnm,
        String divCd,
        String rsbDvCd
    ) {}

    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SaveOpngReq")
    public record SaveOpngReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String tcntDvCd,
        String reCrtYn
    ) {}

    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String prtnrNo,
        String tcntDvCd,
        @NotBlank
        String opngCd
    ) {}

    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SaveConfirmReq")
    public record SaveConfirmReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String tcntDvCd,
        @NotBlank
        String cnfmStatYn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 웰스매니저 정착수당 Search Result Dto
    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String ogCd,
        String bldNm,
        String prtnrNo,
        String prtnrKnm,
        String rsbDvCd,
        String qlfDvCd,
        String m1QlfDvCd,
        String cvDt,
        String strtupDt,
        String preStrtupDt,
        String bzStatCd,
        String cntrDt,
        String cltnDt,
        String fstCntrDt,
        String fnlCltnDt,
        String opngCd,
        String baseYm,
        String tcntDvCd,
        String cnfmStatYn
    ) {}

    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SearchEtcRes")
    public record SearchEtcRes(
        String opngCnfmYn,
        String opngCnfmCnt,
        String feeCnfmYn
    ) {}
}
