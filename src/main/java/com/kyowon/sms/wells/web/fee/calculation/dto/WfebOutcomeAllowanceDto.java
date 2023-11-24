package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfebOutcomeAllowanceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 성과수당현황 Search Request Dto
    @Builder
    @ApiModel("WfebOutcomeAllowanceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        String rsbDvCd, /* 직책 구분 코드 */
        String ogTpCd, /* 조직 구분 코드 */
        String prtnrNo, /* 사번 */
        Boolean rtmInqr /* 실시간 조회 여부 */
    ) {}

    @ApiModel("WfebOutcomeAllowanceDto-SaveMoReq")
    public record SaveMoReq(
        String baseYm, /* 기준년월 */
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        String rsbDvCd, /* 직책구분코드 */
        Integer dtrcN, /* 지국수 */
        Long trgCt, /* 목표건수 */
        Integer baseDtrcN, /* 기준지국수 */
        Double perfCt, /* 실적건수 */
        Double trgAchvRt, /* 목표달성율 */
        Long nwSellAccCt, /* 신규판매계정건수 */
        Long purSprAccCt, /* 순수이탈계정건수 */
        Long accNincCt, /* 계정순증건수 */
        Double feeAckmtCt, /* 수수료인정건수 */
        Long elhmExcpPerfAmt, /* 가전외실적금액 */
        Long elhmExcpCt, /* 가전외건수 */
        Long redfCt, /* 되물림건수 */
        Long perfAggAmt, /* 실적누계금액 */
        Long mngrDangGd, /* 관리자위험등급 */
        Long trgAchvAwAmt, /* 목표달성수당금액 */
        Long brchNincCt, /* 지점순증건수 */
        Double wmAclActiCt, /* WM실제활동건수 */
        Double bfsvcAclActiCt, /* BS실제활동건수 */
        Double aclActiBrchAvCt, /* 실제활동지점평균건수 */
        Long actvBrchN, /* 활성지점수 */
        Long ogAwAmt, /* 조직수당금액 */
        Long dsbAmt /* 지급금액 */
    ) {}

    @ApiModel("WfebOutcomeAllowanceDto-SavePoReq")
    public record SavePoReq(
        String baseYm, /* 기준년월 */
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        String rsbDvCd, /* 직책구분코드 */
        Long trgCt, /* 목표건수 */
        Double perfCt, /* 실적건수 */
        Double trgAchvRt, /* 목표달성율 */
        Long trgAchvAwAmt, /* 목표달성수당금액 */
        Long thm1OptnTrgCt, /* 1차월가동목표건수 */
        Long thm1OptnAchvCt, /* 1차월가동달성건수 */
        Double thm1OptnAchvRt, /* 1차월가동달성율 */
        Long thm1OptnAwAmt, /* 1차월가동수당금액 */
        Long aclActiTrgCt, /* 실제활동목표건수 */
        Long aclActiAchvCt, /* 실제활동달성건수 */
        Double aclActiAchvRt, /* 실제활동달성율 */
        Long aclActiAwAmt, /* 실제활동수당금액 */
        Long ogAchvAwSumAmt, /* 조직달성수당합계금액 */
        Long ejtAwAmt, /* 배출수당금액 */
        Long outcAwSumAmt /* 성과수당합계금액 */
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS M조직 성과수당현황 Search Result Dto
    @ApiModel("WfebOutcomeAllowanceDto-AwBaseInfoRes")
    public record AwBaseInfoRes(
        Integer baseSn, /* 기준일련번호 */
        String ogTpCd, /* 조직유형코드 */
        String rsbDvCd, /* 직책구분코드 */
        String mngrOutcDvCd, /* 관리자성과구분코드 */
        Double achvStrtRat, /* 달성시작비율 */
        Double achvEndRat, /* 달성종료비율 */
        Integer nincStrtCt, /* 순증시작건수 */
        Integer nincEndCt, /* 순증종료건수 */
        String apyStrtYm, /* 적용시작년월 */
        String apyEndYm, /* 적용종료년월 */
        Long awAmt /* 수당금액 */
    ) {}
    @ApiModel("WfebOutcomeAllowanceDto-SearchManagerRes")
    public record SearchManagerRes(
        String ogCd,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        Integer dtrcN,
        Integer trgCt,
        Integer baseDtrcN,
        Double perfCt,
        Double trgAchvRt,
        Integer nwSellAccCt,
        Integer purSprAccCt,
        Integer accNincCt,
        Double feeAckmtCt,
        Long elhmExcpPerfAmt,
        Integer elhmExcpCt,
        Integer redfCt,
        Long perfAggAmt,
        Integer mngrDangGd,
        Long trgAchvAwAmt,
        Integer brchNincCt,
        Double wmAclActiCt,
        Double bfsvcAclActiCt,
        Double aclActiBrchAvCt,
        Integer actvBrchN,
        Long ogAwAmt,
        Long dsbAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS P조직 성과수당현황 Search Result Dto
    @ApiModel("WfebOutcomeAllowanceDto-SearchPlannerRes")
    public record SearchPlannerRes(
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        Integer trgCt,
        Double perfCt,
        Double trgAchvRt,
        Long trgAchvAwAmt,
        Integer thm1OptnTrgCt,
        Integer thm1OptnAchvCt,
        Double thm1OptnAchvRt,
        Long thm1OptnAwAmt,
        Integer aclActiTrgCt,
        Integer aclActiAchvCt,
        Double aclActiAchvRt,
        Long aclActiAwAmt,
        Long ogAchvAwSumAmt,
        Long ejtAwAmt,
        Long outcAwSumAmt
    ) {}
}
