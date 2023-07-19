package com.kyowon.sms.wells.web.bond.consultation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncUnpaidGuideUrgentDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 미납요금 안내/촉구 대상 Search Request Dto
    @Builder
    @ApiModel("WbncUnpaidGuideUrgentDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ucAmtFwTpCd, /* 미수금액발송유형코드 */
        @NotBlank
        String stateDvCd, /* 진행상태 */
        @NotBlank
        String copnDvCd, /* 법인격구분코드 */
        @NotBlank
        String bndBizDvCd, /* 채권업무구분코드 */
        @NotBlank
        String fromDlqMcn, /* 시작연체개월 */
        @NotBlank
        String toDlqMcn, /* 종료연체개월 */
        @NotBlank
        String clctamDvCd, /* 집금구분 */
        @NotBlank
        String fromTotNpdAmt, /* 시작총미납액 */
        @NotBlank
        String toTotNpdAmt, /* 종료총미납액 */
        String ojWkDt, /* 자료생성 작업일자 */
        String ojPyTmlmDt, /* 자료생성 납입기한 */
        String wkDt, /* 작업일자 */
        String pyTmlmDt/* 납입기한 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 미납요금 안내/촉구 대상 Search Result Dto
    @ApiModel("WbncUnpaidGuideUrgentDto-SearchRes")
    public record SearchRes(
        String cstNo, /* 고객번호 */
        String sfkVal, /* 세이프키 */
        String cntrNo, /* 계약번호 */
        int cntrSn, /* 계약일련번호 */
        String cntrNoSn, /* 계약상세일련번호 */
        String cntrTpCd, /* 계약유형 */
        String cntrTpNm, /* 계약유형명 */
        String cstKnm, /* 계약자성명 */
        String cstY, /* 고객년도 */
        String cstCd, /* 고객코드 */
        int agrgCt, /* 건수 */
        int agrgMinusCt, /* 나머지건수 */
        String cntrBasAdr, /* 주소1 */
        String cntrDtlAdr, /* 주소2 */
        String cntrRefAdr, /* 주소3 */
        String zip, /* 우편번호 */
        String istPlcNm, /* 수취인 */
        String bndBizDvCd, /* 상품구분 */
        String bndBizDvNm, /* 상품구분명 */
        String rcpDate, /* 계약일자 */
        String istDate, /* 설치일자 */
        String rentalAmt1, /* 렌탈료1 */
        String rentalAmt2, /* 렌탈료2 */
        Long npdAmt, /* 미납금 */
        Long dlqAmt, /* 연체금 */
        Long thmChramAmt, /* 당월요금 */
        Long dlqAddAmt, /* 연체가산금 */
        Long spmtSlAmt, /* 추가매출액 */
        int dlqMcn, /* 연체개월 */
        Long totNpdAmt, /* 총미납금액 */
        String pdgrpNm, /* 제품군 */
        String pdNm, /* 제품명 */
        String fnlPyDate, /* 최근납입일 */
        String bnkCd, /* 가상계좌은행 */
        String bnkNm, /* 가상계좌은행명 */
        String vacNo, /* 가상계좌번호 */
        String achldrNm, /* 예금주 */
        String pyTmlmDt, /* 납입기한 */
        String wkDt, /* 작업/확정일자 */
        String clctamPrtnrNm, /* 집금담당자 */
        String ofrmTnoVal, /* 집금담당연락처 */
        String cnfmYn /* 확정여부 */
    ) {}
    @Builder
    @ApiModel("WbncUnpaidGuideUrgentDto-CheckReq")
    public record CheckReq(
        @NotBlank
        String ucAmtFwTpCd, /* 미수금액발송유형코드 */
        @NotBlank
        String ojWkDt, /* 자료생성 작업일자 */
        String wkDt /* 작업일자 */
    ) {}
    @Builder
    @ApiModel("WbncUnpaidGuideUrgentDto-CheckRes")
    public record CheckRes(
        String cnfmYn, /*확정여부*/
        int totalCount /*데이터 수*/
    ) {}
    @Builder
    @ApiModel("WbncUnpaidGuideUrgentDto-SaveObjectReq")
    public record SaveObjectReq(
        @NotBlank
        String ucAmtFwTpCd, /* 미수금액발송유형코드 */
        @NotBlank
        String ojWkDt /* 자료생성 작업일자 */
    ) {}
    @Builder
    @ApiModel("WbncUnpaidGuideUrgentDto-CreateObjectReq")
    public record CreateObjectReq(
        @NotBlank
        String ucAmtFwTpCd, /* 미수금액발송유형코드 */
        @NotBlank
        String copnDvCd, /* 법인격구분코드 */
        @NotBlank
        String bndBizDvCd, /* 채권업무구분코드 */
        @NotBlank
        String fromDlqMcn, /* 시작연체개월 */
        @NotBlank
        String toDlqMcn, /* 종료연체개월 */
        @NotBlank
        String clctamDvCd, /* 집금구분 */
        @NotBlank
        String ojWkDt, /* 자료생성 작업일자 */
        @NotBlank
        String ojPyTmlmDt /* 자료생성 납입기한 */
    ) {}
    @Builder
    @ApiModel("WbncUnpaidGuideUrgentDto-CreateCustomerReq")
    public record CreateCustomerReq(
        @NotBlank
        String ucAmtFwTpCd, /* 미수금액발송유형코드 */
        @NotBlank
        String wkDt, /* 작업일자 */
        @NotBlank
        String ojWkDt, /* 자료생성 작업일자 */
        @NotBlank
        String pyTmlmDt/* 납입기한 */
    ) {}
}
