package com.kyowon.sms.wells.web.fee.aggregate.dto;

import io.swagger.annotations.ApiModel;

import lombok.Builder;

public class WfeaNetOrderDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Request Dto

    @Builder
    @ApiModel(value = "WfeaNetOrderDto-SearchReq")
    public record SearchReq(
        //조회구분
        String schDvCd,
        //차수
        String tcntDvCd,
        //조직구분
        String ogDvCd,
        //구분
        String schDv,
        //제품유형
        String pdctTp,
        //판매유형
        String selTp,
        //시작일자
        String schDtStrt,
        //종료일자
        String schDtEnd,
        //취소시작일자
        String schCancDtStrt,
        //취소종료일자
        String schCancDtEnd,
        //상품시작코드
        String schPdCdStrt,
        //상품종료코드
        String schPdCdEnd,
        //패키지시작코드
        String schPkgCdStrt,
        //패키지종료코드
        String schPkgdEnd,
        //총괄단
        String ogLevl1,
        //지역단
        String ogLevl2,
        //지점
        String ogLevl3,
        //번호
        String prtnrNo,
        //수수료년월
        String perfYm

    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SaveReq")
    public record SaveReq(
        String tcntDvCd,
        String perfYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Result Dto
    @ApiModel(value = "WfeaNetOrderDto-SearchRes")
    public record SearchRes(
        String sellOg,
        String blg,
        String prtnrNo,
        String prtnrKnm,
        String selType,
        String pdctTp,
        String cntrDtlNo,
        String cstDv,
        String prdtNm,
        String prdtCd,
        String pkgCd,
        String istm,
        String stplMcnt,
        String cntrDate,
        String slDt,
        String canDt,
        String demDt,
        String rtlfe
    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SearchFeeRes")
    public record SearchFeeRes(
        String sellOg,
        String totCt,
        String rental,
        String snglPmnt,
        String regDlvr,
        String rstl,
        String hcrMsh,
        String envr,
        String welsf,
        String bh,
        String capsl,
        String homeCare,
        String csmb,
        String acsr
    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SearchConfirmRes")
    public record SearchConfirmRes(
        String cnfmChk
    ) {}
}
