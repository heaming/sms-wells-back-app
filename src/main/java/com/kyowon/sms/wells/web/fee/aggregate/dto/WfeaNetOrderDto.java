package com.kyowon.sms.wells.web.fee.aggregate.dto;

import io.swagger.annotations.ApiModel;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WfeaNetOrderDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Request Dto

    @Builder
    @ApiModel(value = "WfeaNetOrderDto-SearchDetailReq")
    public record SearchDetailReq(
        @NotBlank
        String dvCd, /* 구분 */
        @NotBlank
        String rcpDtFrom, /* 접수일자 시작 */
        @NotBlank
        String rcpDtTo, /* 접수일자 종료 */
        String cancDtFrom, /* 취소일자 시작 */
        String cancDtTo, /* 취소일자 종료 */
        String pdCdFrom, /* 상품코드 시작 */
        String pdCdTo, /* 상품코드 종료 */
        String pkgCdFrom, /* 패키지코드 시작 */
        String pkgCdTo, /* 패키지코드 종료 */
        String ogTpCd, /* 조직유형코드 */
        String sellTpCd, /* 판매유형코드 */
        String prtnrNo, /* 파트너번호 */
        String feePdctTpCd, /* 수수료제품유형코드 */
        String ogLevl1, /* 조직레벨1 */
        String ogLevl2, /* 조직레벨2 */
        String ogLevl3, /* 조직레벨3 */
        String ogLevl4, /* 조직레벨4 */
        String ogLevl5 /* 조직레벨5 */
    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SearchAggregateReq")
    public record SearchAggregateReq(
        @NotBlank
        String dvCd, /* 구분 */
        @NotBlank
        String feeTcntDvCd, /* 수수료차수구분코드 */
        @NotBlank
        String perfYm, /* 실적년월 */
        String ogTpCd /* 조직유형코드 */
    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SearchStatusReq")
    public record SearchStatusReq(
        @NotBlank
        String feeTcntDvCd, /* 수수료차수구분코드 */
        @NotBlank
        String perfYm, /* 실적년월 */
        String ogTpCd /* 조직유형코드 */
    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SearchProductReq")
    public record SearchProductReq(
        @NotBlank
        String feeTcntDvCd, /* 수수료차수구분코드 */
        @NotBlank
        String perfYm /* 실적년월 */
    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SaveReq")
    public record SaveReq(
        String feeTcntDvCd,
        String perfYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Result Dto
    @ApiModel(value = "WfeaNetOrderDto-SearchDetailRes")
    public record SearchDetailRes(
        String ogTpCd, /* 조직유형코드 */
        String ogCd, /* 조직코드 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String sellTpCd, /* 판매유형코드 */
        String feePdctTpCd, /* 수수료제품유형코드 */
        String cntrNo, /* 계약번호 */
        String copnDvCd, /* 법인격구분코드 */
        String pdNm, /* 상품명 */
        String basePdCd, /* 기준상품코드 */
        String pkgCd, /* 패키지코드 */
        String pkgSn, /* 패키지일련번호 */
        String cntrPtrm, /* 계약기간 */
        String stplPtrm, /* 약정기간 */
        String cntrCnfmDtm, /* 계약확정일시 */
        String slDt, /* 매출일자 */
        String canDt, /* 취소일자 */
        String reqdDt, /* 철거일자 */
        String sellAmt /* 판매금액 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Result Dto
    @ApiModel(value = "WfeaNetOrderDto-SearchAggregateRes")
    public record SearchAggregateRes(
        String ogTpCd, /* 조직유형코드 */
        String ogCd, /* 조직코드 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String sellTpCd, /* 판매유형코드 */
        String feePdctTpCd, /* 수수료제품유형코드 */
        String cntrNo, /* 계약번호 */
        String copnDvCd, /* 법인격구분코드 */
        String pdNm, /* 상품명 */
        String basePdCd, /* 기준상품코드 */
        String pkgCd, /* 패키지코드 */
        String cntrPtrm, /* 계약기간 */
        String stplPtrm, /* 약정기간 */
        String cntrCnfmDtm, /* 계약확정일시 */
        String slDt, /* 매출일자 */
        String canDt, /* 취소일자 */
        String reqdDt, /* 철거일자 */
        String sellAmt /* 판매금액 */
    ) {}
    @ApiModel(value = "WfeaNetOrderDto-SearchStatusRes")
    public record SearchStatusRes(
        String ogTpCd, /* 조직유형코드 */
        String totCt, /* 총 건수 */
        String spayCnt, /* 일시불 건수 */
        String rentCnt, /* 렌탈/리스 건수 */
        String mshCnt, /* 멤버십 건수 */
        String rglrCnt, /* 정기배송 건수 */
        String rstlCnt, /* 재약정 건수 */
        String envrCnt, /* 환경 건수 */
        String welsfCnt, /* 웰스팜 건수 */
        String bhCnt, /* bh 건수 */
        String capslCnt, /* 캡슐 건수 */
        String homeCareCnt, /* 홈케어 건수 */
        String csmbCnt, /* 소모품 건수 */
        String acsrCnt, /* 부속품 건수 */
        String nopdCnt /* 미등록유형 건수 */
    ) {}

    @ApiModel(value = "WfeaNetOrderDto-SearchProductRes")
    public record SearchProductRes(
        String ogTpCd, /* 조직유형코드 */
        String pdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String pdCnt /* 상품건수 */
    ) {}

}
