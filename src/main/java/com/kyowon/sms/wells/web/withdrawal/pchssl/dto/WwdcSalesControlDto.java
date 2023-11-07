package com.kyowon.sms.wells.web.withdrawal.pchssl.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 매출 조정 관리 DTO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-05-25
 */
public class WwdcSalesControlDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 매출조정 금액 조회 Search Request Dto

    // 선납예상금 데이터
    @ApiModel("WwdcSalesControlDto-SearchSalesControlReq")
    public record SearchSalesControlReq(
        String sellTp, /* 판매유형 */
        String sellTpDtlCd, /* 판매유형상세 */
        String exmpYn, /* 방학면제여부 */
        String perfStrtDtm, /* 실적 To*/
        String perfFshDtm, /* 실적 From*/
        String cntrSn, /* 계약일련번호 */
        String cntrNo, /* 계약번호 */
        String ctrTp, /* 조정유형*/
        String mtrDv, /* 자료구분*/
        String ctrDv, /* 조정구분 */
        String dsc, /* 할인구분 */
        String prtnrNo, /* 등록자ID -> 23.06.15 파트너ID */
        String slCtrPrcsStrtDt, /* 등록일자 From */
        String slCtrPrcsFshDt /* 등록일자 To */
    ) {}


    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출조정 금액 조회 Search Result Dto
    @ApiModel("WwdcSalesControlDto-SearchSalesControlRes")
    public record SearchSalesControlRes(
        //
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String cstKnm, /* 고객명 */
        String slCtrStrtYm, /* 매출일자 */
        String slCtrEndYm, /* 종료년월 - 만료일자 */
        String pdCd, /* 제품코드 */
        String pdNm, /* 제품명 */
        String slCtrTpCd, /* 할인 */
        String slCtrSellTpCd, /* 판매유형코드 */
        String sellTpDtlCd, /* 판매유형상세코드 */
        String slCtrMtrDvCd, /* 자료구분코드 */
        String slCtrMtrTpCd, /* 매출조정자료유형코드 */
        String slCtrDvCd, /* 매출조정구분코드 */
        String slCtrDscTpCd, /* 매출조정유형코드 - 할인 */
        String canAfOjYn, /* 취소후적용 - default N */
        String slCtrAmt, /* 매출조정금액 */
        String slCtrWoExmpAmt, /* 매출전액면제금액 */
        String slCtrPtrmExmpAmt, /* 매출조정기간면제금액 */
        String slCtrRmkCn, /* 조정비고내용 - 사유 */
        String slCtrPrcsdt, /* 등록일자 */
        String usrNm,
        String fnlMdfcUsrId
    ) {}


    // *********************************************************
    // Request Dto
    // *********************************************************
    // 매출 조정 관리 저장 Save Request Dto
    @ApiModel("WwdcSalesControlDto-SaveSalesControlReq")
    public record SaveSalesControlReq(
        String rowState,
        @NotBlank
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String cstKnm, /* 고객명 */
        String slCtrStrtYm, /* 매출일자 */
        String slCtrEndYm, /* 종료년월 - 만료일자 */
        String slCtrSellTpCd, /* 매출조정판매유형코드 */
        String slCtrMtrDvCd, /* 매출조정자료유형코드 */
        String pdCd, /* 제품코드 */
        String pdNm, /* 제품명 */
        String slCtrDvCd, /* 매출조정구분코드 */
        String slCtrTpCd, /* 매출조정유형코드 */
        String slCtrDscTpCd, /* 할인 */
        String slCtrMtrTpCd, /* 매출조정할인유형코드 */
        String canAfOjYn, /* 취소후적용 - default N */
        String slCtrAmt, /* 매출조정금액 */
        String slCtrWoExmpAmt, /* 매출전액면제금액 */
        String slCtrPtrmExmpAmt, /* 매출조정기간면제금액 */
        String slCtrRmkCn, /* 조정비고내용 - 사유 */
        String slCtrProcsYn, /* */
        String slCtrPrcsdt, /* 등록일자 */
        String usrNm,
        String fnlMdfcUsrId,

        String canDt, /* 취소일자 */
        String apyY, /* 적용년도 */
        String jan, /* 1월 : Y/N */
        String feb, /* 2월 : Y/N */
        String jul, /* 7월 : Y/N */
        String aug, /* 8월 : Y/N */
        String sellTpDtlCd /* 매출판매유형상세코드 */

    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 매출 조정 관리 삭제 Remove Request Dto

    // 저장용 데이터
    @ApiModel("WwdcSalesControlDto-RemoveSalesControlReq")
    public record RemoveSalesControlReq(
        String rowState,
        @NotBlank
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String cstKnm, /* 고객명 */
        String slCtrStrtYm, /* 매출일자 */
        String slCtrEndYm, /* 종료년월 - 만료일자 */
        String slCtrSellTpCd, /* 매출조정판매유형코드 */
        String slCtrMtrDvCd, /* 매출조정자료유형코드 */
        String pdCd, /* 제품코드 */
        String pdNm, /* 제품명 */
        String slCtrDvCd, /* 매출조정구분코드 */
        String slCtrTpCd, /* 매출조정유형코드 */
        String slCtrDscTpCd, /* 할인 */
        String slCtrMtrTpCd, /* 매출조정할인유형코드 */
        String canAfOjYn, /* 취소후적용 - default N */
        String slCtrAmt, /* 매출조정금액 */
        String slCtrWoExmpAmt, /* 매출전액면제금액 */
        String slCtrPtrmExmpAmt, /* 매출조정기간면제금액 */
        String slCtrRmkCn, /* 조정비고내용 - 사유 */
        String slCtrPrcsdt, /* 등록일자 */
        String usrNm,
        String fnlMdfcUsrId

    ) {}
}
