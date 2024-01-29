package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

public class WfebMutualAidFeeManagerMgtDto {
    public record SearchAidReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        String strtDt, /* 시작일 */
        String endDt, /* 종료일 */
        String pdCd, /* 상품코드 */
        String sellPrtnrNo, /* 번호 */
        String clasfctnFee /* 수수료구분 */
    ) {}

    public record CreateAidReq(
        @NotBlank
        String baseYm  /* 기준년월 */
    ) {}


    public record AidIndividual(
        String baseYm, /* 발생월 */
        String cntrStat, /* 수수료구분 */
        String ogCd, /* 소속지점cd */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 성명 */
        String rsbDvCd, /* 직책 */
        String brmgrPrtnrNo, /* 지점장번호 */
        String cntrNo, /* 계약번호 */
        String pdNm, /* 상품명 */
        String cntrPdStrtdt /* 설치일 */,
        String lifCntrNo, /* 상조계약번호 */
        String lifPdNm, /* 상조상품명 */
        String rcpdt, /* 접수일 */
        String cntrDt, /* 계약일 */
        String canDt, /* 취소일 */
        Integer sellFee, /* 판매자수수료 */
        String totDsbOjDvCd, /* 총지급대상 */
        String lifCntrOcTn, /* 회차 */
        Integer slOcAcuAmt, /* 누적발생매출 */
        Integer dpAcuAmt, /* 누적임금 */
        String flpymTn, /* 완납회차 */
        Integer preAmtSum, /* 가지급금 */
        Integer curAmt, /* 당월지급 */
        String etCnfmDvNm, /* 라이프 확정 */
        String feeDsbYm, /* 수수료 지급월 */
        String feeRedfYm, /* 수수료 되물림월 */
        String cnfmYn /* 확정여부 */
    ) {}

    public record AidGroup(
        String baseYm, /* 발생월 */
        String cntrStat, /* 수수료구분 */
        String ogCd, /* 소속 */
        String brmgrPrtnrNo, /* 지점장번호 */
        String prtnrKnm, /* 지점장명 */
        String rsbDvCd, /* 직책 */
        Integer brchCt, /* 건수 지점 */
        Integer brchAmt, /* 금액 지점 */
        String feeDsbYm, /* 수수료 지급월 */
        String feeRedfYm, /* 수수료 되물림월 */
        String etCnfmDvNm, /* 라이프 확정 */
        String cnfmYn /* 확정여부 */
    ) {}

    public record SearchAidOrderReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        String cntrStat, // 실적구분
        String alncCd, // 제휴구분
        String rsbDvCd, // 직책유행
        String dgr2LevlOgId, // 조직레벨 1~3
        String dgr3LevlOgId,
        String dgr4LevlOgId,
        String prtnrNo, // 번호
        String prtnrNm
    ) {}
    public record AidOrder(
        String alncNm, /* 제휴구분 */
        String cntrNo, /* 웰스계약번호 */
        String lifCntrNo, /* 상조계약번호 */
        String cstKnm, /* 고객명 */
        String ogCd, /* 소속 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String cdCntn, /* 직책명 */
        String brmgrPrtnrNo, /* 지점장 */
        String rcpdt,  /* 접수일자 */
        String cntrDt, /* 계약일자 */
        String dpDt,   /* 입금일자 */
        String wdwlDt, /* 철회일자 */
        String canDt,  /* 취소일자 */
        String feeDsbYm,  /* 수수료월 */
        String cntrStat /* 실적구분 */
    ) {}

}
