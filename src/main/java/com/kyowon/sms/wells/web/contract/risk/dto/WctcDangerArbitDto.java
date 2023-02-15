package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import com.sds.sflex.system.config.validation.validator.ValidMonth;

import io.swagger.annotations.ApiModel;

public class WctcDangerArbitDto {
    //*********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String srchGbn,
        @ValidDate
        String dangOcStrtdt,
        @ValidDate
        String dangOcEnddt,
        @ValidMonth
        String dangOcStrtMonth,
        @ValidMonth
        String dangOcEndMonth,
        String gnrdv,
        String rgrp,
        String brch,
        String dangOjPrtnrNo
    ) {}
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchRes")
    public record SearchRes(
        String dangOjPrtnrNo,
        String dangOcStrtmm,
        String dangOjOgId,
        String dangOjPntnrNm,
        String dangOjPrtnrPstnDvNm,
        String dgr1LevlDgPrtnrNo,
        String dgr2LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNo,
        String dgr4LevlDgPrtnrNo,
        String dangChkNm,
        String dangArbitCdNm,
        String dangUncvrCt,
        String dangArbitLvyPc,
        String dangArbitOgNm,
        String fstRgstUsrId,
        String fstRgstDt
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 비정도 영업 조치 사항 등록 Save Request Dto
    @ApiModel(value = "WctcDangerArbitDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState, /* 로우상태(created, updated) */
        @NotBlank
        String dangOjPrtnrNo, /* 사번(행위자) */
        @NotBlank
        String dangOcStrtmm, /* 발생년월(행위자) */
        String dangOjPrtnrOgNm, /* 소속(행위자) */
        String dangOjPrtnrNm, /* 성명(행위자) */
        String dangOjPrtnrPstnDvNm, /* 직급(행위자) */
        String dgr1HgrDgPrtnrNo, /* 총괄단(소속) */
        String dgr2HgrDgPrtnrNo, /* 지역단(소속) */
        String bznsSpptPrtnrNo, /* 영업지원파트너(BM)(소속) */
        String dgr3HgrDgPrtnrNo, /* 지점(소속) */
        @NotBlank
        String dangChkNm, /* 부과내역(벌점) */
        String dangArbitCd, /* 조치항목(벌점) */
        @NotBlank
        String dangUncvrCt, /* 부과대상건수(벌점) */
        @NotBlank
        String dangArbitLvyPc, /* 조치결과부과점수(벌점) */
        @NotBlank
        String dangArbitOgId, /* 조치부서(벌점) */
        String fstRgstUsrId, /* 등록자 */
        String fstRgstDtm /* 등록일자 */
    ) {}
}
