package com.kyowon.sms.wells.web.bond.consultation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncRentalResignConfirmDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 직권해지관리 - 렌탈 해지확정 Search Request Dto
    @Builder
    @ApiModel("WbncRentalResignConfirmDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String fromAuthRsgCnfmdt, /* 확정년월시작 */
        @NotBlank
        String toAuthRsgCnfmdt, /* 확정년월종료 */
        String clctamDvCd, /* 집금구분코드 */
        String clctamPrtnrNo, /* 집금담당자 */
        String cstNo, /* 고객번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn /* 계약일련번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 직권해지관리 - 렌탈 해지확정 Search Result Dto
    @ApiModel("WbncRentalResignConfirmDto-SearchRes")
    public record SearchRes(
        String authRsgCnfmym, /* 확정년월 */
        String cstNo, /* 고객번호 */
        String cntrNoSn, /* 계약상세번호 */
        String sellTpNm, /* 판매유형명 */
        String cstKnm, /* 고객명 */
        Long rtrnDbtFnlAmt, /* 반환채무최종금액 */
        Long nrtrnDbtFnlAmt, /* 미반환채무최종금액 */
        Long rsgMmUcAmt, /* 해지월미수금액 */
        Long rsgMmStpCs, /* 해지월중지금액 */
        Long ccam, /* 위약금 */
        Long csmbCsBorAmt, /* 소모품비용위약금액 */
        Long reqdCsBorAmt, /* 철거비용위약금액 */
        Long lsRntf, /* 분실손료 */
        String exmptYn, /* 면책여부 */
        String pdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String clctamPrtnrNo, /* 집금파트너번호 */
        String prtnrKnm /* 집금파트너명 */
    ) {}
}
