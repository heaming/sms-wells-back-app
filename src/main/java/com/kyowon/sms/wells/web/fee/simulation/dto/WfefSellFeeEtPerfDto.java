package com.kyowon.sms.wells.web.fee.simulation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfefSellFeeEtPerfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 수수료 예상실적 조회 (판매) Search Request Dto
    @Builder
    @ApiModel("WfefSellFeeEtPerfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String inqrStrtDt,
        @NotBlank
        String inqrEndDt,
        @NotBlank
        String perfBaseDv, /* 실적기준구분 1: 접수, 2: 매출 */
        @NotBlank
        String inqrDv, /* 조회구분 1: 건수&실적, 2: 계정순증, 3: 판매상세, 4: 순증상세 */
        String nincDv, /* 순증구분 01: 전월취소, 02: 신규판매 */
        String pstnDvCd, /* 직급구분코드 */
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String prtnrNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 수수료 예상실적 조회 (판매) Search Result Dto
    @ApiModel("WfefSellFeeEtPerfDto-SearchRes")
    public record SearchRes(
        String ogCd,
        String prtnrNm,
        String prtnrNo,
        String pstnDvCd,
        int brchCt,
        int rentalElhmCt,
        int spayElhmCt,
        int rentalElhmBaseAmt,
        int spayElhmBaseAmt,
        int notElhmAmt,
        int spayNincNwCt,
        int rentalNincNwCt,
        int rentalCanCt,
        int rentalExnCt,
        int mshSprCt,
        int spayRsgCt,
        int vstAsnCt,
        int vstFshCt,
        double vstProcsRt
    ) {}

    // 수수료 예상실적 조회 (판매) 판매상세 Search Result Dto
    @ApiModel("WfefSellFeeEtPerfDto-SearchDetailRes")
    public record SearchDetailRes(
        String ogCd,
        String prtnrNm,
        String prtnrNo,
        String pstnDvCd,
        String cntrNo,
        String cntrCstNm,
        String cntrCnfmDt,
        String cntrPdStrtdt,
        String cntrCanDt,
        String pdClsfNm,
        String pdCd,
        String pdNm,
        String mchnChTpCd,
        String elhmPerfCt,
        String elhmBaseAmt,
        String notElhmAmt
    ) {}

    // 수수료 예상실적 조회 (판매) 순증상세 Search Result Dto
    @ApiModel("WfefSellFeeEtPerfDto-SearchCnclNincRes")
    public record SearchCanNincRes(
        String ogCd,
        String prtnrNm,
        String prtnrNo,
        String pstnDvCd,
        String canTpNm,
        String cntrNo,
        String cntrCstNm,
        String cntrCnfmDt,
        String cntrPdStrtdt,
        String cntrCanDt,
        String cntrStatChRsonCd,
        String pdClsfNm,
        String pdCd,
        String pdNm,
        String origOgCd,
        String origPrtnrNm,
        String origSellPrtnrNo
    ) {}
}
