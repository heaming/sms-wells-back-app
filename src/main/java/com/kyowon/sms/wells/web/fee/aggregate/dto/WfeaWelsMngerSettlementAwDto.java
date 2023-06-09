package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

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
        String schDiv,
        String schRsbDvCd
    ) {}

    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SaveOpngReq")
    public record SaveOpngReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String tcntDvCd
    ) {}

    @ApiModel(value = "WfeaWelsMngerSettlementAwDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String prtnrNo,
        @NotBlank
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
        String og1nm,
        String og2nm,
        String ogId,
        String bldNm,
        String prtnrNo,
        String prtnrKnm,
        String rsbDvCd,
        String qlfDvCd,
        String nextQlfDvCd,
        String procsDtm,
        String srtup,
        String freeSrtup,
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
        String opngcnfmYn,
        String opngCnfmCnt,
        String feeCnfmYn
    ) {}
}
