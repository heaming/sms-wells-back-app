package com.kyowon.sms.wells.web.bond.consultation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncSameCustomerContractDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 동일고객 계약내역 조회 Find Result Dto
    @ApiModel(value = "WbncSameCustomerContractDto-FindContractRes")
    public record FindContractRes(
        String mpyBsdt, /* 이체 */
        String bndBizDvCd, /* 채권업무구분코드 */
        String bndBizDvNm, /* 채권업무구분명 */
        String pdClsfNm, /* 제품군 */
        String pdNm, /* 제품명 */
        String pdCd, /* 제품코드 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String cstNo, /* 고객코드 */
        String cstKnm, /* 고객명 */
        String dlqMcn, /* 연체개월 */
        String ojAmt, /* 대상금액 */
        String ojDpAmt, /* 대상입금 */
        String ojBlam, /* 대상잔액 */
        String authRsgCnfmdt /* 직권해지일자 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 동일고객 계약 입금정보 조회 Find Result Dto
    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositRes")
    public record FindDepositRes(
        String perfYm, /* 실적월 */
        String rentalTn, /* 차월 */
        String thmSlSumAmt, /* 매출금액 */
        String dpAmtFnl, /* 입금액 */
        String prmAmt, /* 영업선수금액 */
        String dlqAmt, /* 연체금액 */
        String dlqMcn1, /* 연체개월 */
        String dlqAddAmt1, /* 연체가산금 */
        String dlqAddDpAmt1, /* 연체가산입금 */
        String dlqAddBlam1, /* 연체가산잔액 */
        String dlqMcn2, /*연체개월 - 일시불*/
        String dlqAmtL10, /*연체금액 - 일시불*/
        String dlqDpAmtL10, /*연체입금금액 - 일시불*/
        String thmChramDpAmtL10, /*당월입금금액 - 일시불*/
        String ucAmt, /* 미수금액 */
        String bilUcAmt /*청구미수금액*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 동일고객 계약 입금정보 상세조회 Find Result Dto
    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositInfoRes")
    public record FindDepositInfoRes(
        String ojAmt, /* 대상금액 */
        String rsgBorAmt, /* 위약금 */
        String totDlqAmt, /* 총연체금액 */
        String slAggAmt, /* 매출누계 */
        String ojDpAmt, /* 대상입금 */
        String lsRntf, /* 분실료 */
        String totDlqDpAmt, /* 총연체입금 */
        String dpAggAmt, /* 입금누계 */
        String ojBlam, /* 대상잔액 */
        String dlqMcn, /* 연체개월 */
        String totDlqBlam, /* 총연체잔액 */
        String dscAggAmt, /* 할인누계 */
        String ucAmt, /* 미수금액 */
        String thmChramAmt, /* 월요금액 */
        String dlqAddAmt, /* 연체가산금액 */
        String ctrAggAmt, /* 조정누계 */
        String ucDpAmt, /* 미수입금 */
        String mmChramDpAmt, /* 월요금입금 */
        String dlqAddDpAmt, /* 연체가산입금 */
        String ucBlam, /* 미수잔액 */
        String mmChramBlam, /* 월요금잔액 */
        String dlqAddBlam /* 연체가산잔액 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 동일고객 계약 위약정보 조회 Find Result Dto
    @Builder
    @ApiModel(value = "WbncSameCustomerContractDto-FindBreachOfPromiseRes")
    public record FindBreachOfPromiseRes(
        String borAmt, /* 위약금총액 */
        String dpCcamSumAmt, /* 입금위약금합계금액 */
        String borBlam, /* 위약잔액 */
        String thmSlSumAmt, /* 당월매출합계금액 */
        String acuDpAmt, /* 누적입금금액 */
        String ucAmt, /* 미수금액 */
        String rsgBorAmt, /* 해지위약금액 */
        String rgstCostDscBorAmt, /* 등록비할인위약금액 */
        String rentalDscBorAmt, /* 렌탈할인위약금액 */
        String csmbCostBorAmt, /* 소모품비위약금액 */
        String pBorAmt, /* 포인트위약금액 */
        String reqdCsBorAmt, /* 철거비용위약금액 */
        String lsRntf, /* 분실손료 */
        String rstlBorAmt, /* 재약정위약금액 */
        String cntrDtlStatCd /* 현재계약상세상태코드 */
    ) {}

    @ApiModel(value = "EbncSameCustomerContractDto-FindSalesRes")
    public record FindSalesRes(
        String rentalRgstCost, /*등록비용*/
        String rentalAmt, /*렌탈료1월요금*/
        String dutyUseMcn, /*렌탈료의무기간(일)*/
        String stplPtrm, /*의무기간(개월)*/
        String reqdDtm, /*철거요청일자*/
        String rgstDsc, /*등록비할인*/
        String rentalDscAmt, /*렌탈료1할인*/
        String sellDscDvCd, /*할인구분코드*/
        String sellDscDvNm, /*할인구분명*/
        String canDt, /*취소일자 - 탈퇴일자*/
        String cntrPdEnddt, /*계약상품종료일자*/
        String cntrTam, /*렌탈총액*/
        String sellDscTpCd, /*할인유형*/
        String sellDscTpNm, /*할인유형명*/
        String sellTpCd, /*리스구분*/
        String sellTpNm, /*리스구분명*/
        String rentalAmt2, /*렌탈료2월요금*/
        String svPrd, /*관리주기(개월)*/
        String linkDtlCntrNo, /*1+1연계계약*/
        String rentalDscAmt2, /*렌탈료2할인*/
        String sdingDtlCntrNo, /*모종계약*/
        String alncLinkDtlCntrNo, /*제휴연계계약*/
        String rentalPtrm, /*렌탈료1개월*/
        String rentalPtrm2, /*렌탈료2개월*/
        String slRcogDt, /*매출일자*/
        String sellAmt, /*월회비 - 판매금액*/
        String svPrd2, /*서비스주기 - 관리주기*/
        String dscAmt, /*할인금액*/
        String sppDuedt, /*방문일자*/
        String cntrDt, /*가입일자*/
        String vstNmnN, /*방문차월*/
        String frisuBfsvcPtrmN, /*무상멤버십*/
        String cntrAmt, /*인수금 - 청약금*/
        String istmMcn, /*할부개월*/
        String frisuAsPtrmN, /*무상a/s - 유상멤버십*/
        String fnlAmt, /*계약총액*/
        String istmPcamAmt, /*할부금액*/
        String mmIstmAmt /*월할부금*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 동일고객 계약 입금내역 조회 Find Result Dto
    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositDtlRes")
    public record FindDepositDtlRes(
        String rn, /* 번호 */
        String rveNo, /* 입금번호 */
        String rveDt, /* 수납일자 */
        String perfDt, /* 실적일자 */
        String rveCd, /* 수납코드 */
        String rveNm, /* 수납명 */
        String dpDvCd, /* 입금구분코드 */
        String dpDvNm, /* 입금구분명 */
        String sellTpCd, /* 입금종류코드 */
        String sellTpNm, /* 입금종류명 */
        String dpTpCd, /* 입금유형코드 */
        String dpTpNm, /* 입금유형명 */
        String rveAmt, /* 수납금액 */
        String dpMesCd, /* 입금수단코드 */
        String dpMesNm, /* 카드구분 */
        String rveDvCd, /* 수납구분코드 */
        String rveDvNm /* 업무구분 */
    ) {}
}
