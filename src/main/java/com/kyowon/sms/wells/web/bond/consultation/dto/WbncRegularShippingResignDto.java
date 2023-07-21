package com.kyowon.sms.wells.web.bond.consultation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncRegularShippingResignDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 직권해지관리 - 정기배송 해지 Search Request Dto
    @Builder
    @ApiModel("WbncRegularShippingResignDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String authRsgDt, /* 직권해지일 */
        String clctamDvCd, /* 집금구분코드 */
        String clctamPrtnrNo /* 집금담당자 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 직권해지관리 - 정기배송 해지 Search Result Dto
    @ApiModel("WbncRegularShippingResignDto-SearchRes")
    public record SearchRes(
        String baseYm, /* 기준년월 */
        String cntrNo, /* 계약번호 */
        Integer cntrSn, /* 계약일련번호 */
        String authRsgSts, /* 해지상태 */
        String authRsgCnfmYn, /* 직권해지확정여부 */
        String cntrNoSn, /* 계약상세번호 */
        String cstKnm, /* 고객명 */
        String basePdCd, /* 패키지번호 */
        String pdNm, /* 패키지명 */
        String prtnrKnm, /* 플래너(판매자)명 */
        String sellPrtnrNo, /* 플래너(판매자)번호 */
        String ogCd, /* 조직코드 */
        String slDt, /* 매출일자 */
        Long sellAmt, /* 판매금액 */
        Integer dlqMcn, /* 연체개월수 */
        Long ucAmt, /* 미수금액 */
        Long dlqAmt, /* 연체금액 */
        Long totRveAmt, /* 입금누계-연체시작일자이후부터 입금된 총금액 */
        String clctamPrtnrNm, /* 집금담당자명 */
        String clctamPrtnrNo, /* 집금담당자번호 */
        String errCn /* 에러내용 */
    ) {}

    @ApiModel("WbncRegularShippingResignDto-SaveConfirmReq")
    public record SaveConfirmReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        String cntrSn /* 계약일련번호 */
    ) {}
}
