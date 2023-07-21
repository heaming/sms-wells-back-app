package com.kyowon.sms.wells.web.bond.consultation.dto;

import javax.validation.constraints.NotBlank;

import org.eclipse.jetty.util.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncRentalResignExpectedMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 직권해지관리 - 렌탈 해지예정 Search Request Dto
    @Builder
    @ApiModel("WbncRentalResignExpectedMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseDt, /* 직권해지일 */
        String sellTpCd, /* 상품유형코드 */
        String clctamDvCd, /* 집금구분코드 */
        String clctamPrtnrNo, /* 집금담당자 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cstNo, /* 고객번호 */
        @NotBlank
        String authRsgCd/* 확정구분 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 직권해지관리 - 렌탈 해지예정 Search Result Dto
    @ApiModel("WbncRentalResignExpectedMgtDto-SearchRes")
    public record SearchRes(
        String baseYm, /* 기준년월 */
        String cntrNo, /* 계약번호 */
        Integer cntrSn, /* 계약일련번호 */
        String cntrNoSn, /* 계약일련상세번호 */
        String sellTpCd, /* 판매유형코드 */
        String sellTpNm, /* 판매유형명 */
        String cstNo, /* 고객번호 */
        String cstKnm, /* 고객명 */
        String authRsgExcdRqrPrtnrNo, /* 직권해지제외요청자파트너번호 g-당월제외사번 */
        String excdYn, /* 제외여부 */
        String authRsgExcdRsonCd, /* 제외사유코드 */
        String authRsgExcdRsonNm, /* 제외사유명 */
        String authRsgExcdRsonCn, /* 제외사유내용 */
        Long totNpdAmt, /* 총미납금액 */
        Long totMchnNpdAmt, /* 총미납금액(결합기기) */
        Long lstmmOcptCs, /* 전월점유비 */
        Long thmOcptCs, /* 당월점유비 */
        Long thmAdnSvUcAmt, /* 당월가압펌프 */
        Long rtrnDbtTotAmt, /* 반환시총체납액 */
        Long nrtrnDbtTotAmt, /* 미반환시총체납액 */
        Long rentalAmt, /* 당월렌탈료 */
        Long rentalResBorAmt, /* 위약렌탈잔여 */
        Long rentalRgstCostBorAmt, /* 위약등록비 */
        Long dscCsBorAmt, /* 위약할인금액 */
        Long rstlBorAmt, /* 위약재약정 */
        Long pBorAmt, /* 위약포인트 */
        Long csmbCsBorAmt, /* 소모품비 */
        Long reqdCsBorAmt, /* 철거비 */
        Long lsRntf, /* 분실손료 */
        Integer rentalNmnN, /* 렌탈차월수 */
        String clctamPrtnrNo, /* 사번 */
        String prtnrKnm, /* 집금담당자명 */
        String clctamYn, /* 집금자여부 */
        Long rveAmt, /* 입금액 */
        String rqrBaseYm, /* 최초제외월 */
        Long acuRveAmt, /* 누적입금액 */
        String bryyMmdd, /* 생년월일 */
        String cntrTpCd, /* 계약구분코드 */
        String cntrTpNm, /* 계약구분명 */
        String pdCd, /* 제품코드 */
        String pdNm, /* 제품명 */
        String istDt, /* 설치일자 */
        String cralLocaraTno, /* 계약자휴대지역전화번호 */
        String mexnoEncr, /* 계약자휴대전화국번호암호화 */
        String cralIdvTno, /* 계약자휴대개별전화번호 */
        String cralTno, /* 계약자휴대전화번호 */
        String ogCd, /* 판매자조직코드 */
        String sellPrtnrNo, /* 판매자번호 */
        String plarKnm, /* 플래너 */
        String canRedfYn, /* 취소되물림여부 */
        Integer dlqMcn, /* 연체개월 */
        String slStpYn, /* 매출중지 */
        Long totNpdExpAmt, /* 총미납금(예정) */
        Long rtrnDbtExpAmt, /* 반환시총채무액(예정) */
        Long nrtrnDbtExpAmt, /* 미반환시총채무액(예정) */
        Long totNpdFnlAmt, /* 총미납금(최종) */
        Long rtrnDbtFnlAmt, /* 반환시총채무액(최종) */
        Long nrtrnDbtFnlAmt, /* 미반환시총채무액(최종) */
        String authRsgExpYn, /* 직권해지예정여부 */
        String authRsgDuedt, /* 직권해지예정일자 */
        String authRsgExpRgstPrtnrNo, /* 직권해지예정등록파트너번호 */
        String authRsgCnfmYn, /* 직권해지확정여부 */
        String authRsgCnfmdt, /* 직권해지확정일자 */
        String authRsgCnfmRgstPrtnrNo/* 직권해지확정등록파트너번호 */
    ) {
        public SearchRes {
            if (StringUtil.isNotBlank(cralLocaraTno) && StringUtil.isNotBlank(mexnoEncr)
                && StringUtil.isNotBlank(cralIdvTno)) {
                cralTno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;
            }
        }
    }

    @Builder
    @ApiModel("WbncRentalResignExpectedMgtDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String baseDt /* 직권해지일 */
    ) {}
    @Builder
    @ApiModel("WbncRentalResignExpectedMgtDto-CheckReq")
    public record CheckReq(
        @NotBlank
        String baseDt /* 직권해지일 */
    ) {}

    @Builder
    @ApiModel("WbncRentalResignExpectedMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        @NotBlank
        String excdYn, /* 제외여부 */
        @NotBlank
        String authRsgExcdRsonCd, /* 제외사유 */
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        int cntrSn, /* 계약일련번호 */
        @NotBlank
        String rowState /* 상태코드 */
    ) {}

    @Builder
    @ApiModel("WbncRentalResignExpectedMgtDto-SaveConfirmReq")
    public record SaveConfirmReq(
        @NotBlank
        String baseDt, /* 직권해지일 */
        @NotBlank
        String confirmDvCd /* 예정확정 : '01' , 최종확정 : '02' */
    ) {}
    @Builder
    @ApiModel("WbncRentalResignExpectedMgtDto-SaveCancelReq")
    public record SaveCancelReq() {}
}
