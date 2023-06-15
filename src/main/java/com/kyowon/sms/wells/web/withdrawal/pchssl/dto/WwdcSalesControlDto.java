package com.kyowon.sms.wells.web.withdrawal.pchssl.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WwdcSalesControlDto {

    // 선납예상금 데이터 
    @ApiModel("WwdcSalesControlDto-SearchSalesControlReq")
    public record SearchSalesControlReq(
        String sellTp, /* 판매유형 */
        String exmpYn, /* 방학면제여부 */
        String perfStrtDtm, /* 실적 To*/
        String perfFshDtm, /* 실적 From*/
        String cntrSn, /* 계약일련번호 */
        String cntrNo, /*계약번호 */
        String ctrTp, /* 조정유형*/
        String mtrDv, /* 자료구분*/
        String ctrDv, /* 조정구분 */
        String dsc, /* 할인구분 */
        String prtnrNo, /* 등록자ID -> 23.06.15 파트너ID */
        String slCtrPrcsStrtDt, /* 등록일자 From */
        String slCtrPrcsFshDt /* 등록일자 To */
    ) {}

    @ApiModel("WwdcSalesControlDto-SearchSalesControlRes")
    public record SearchSalesControlRes(
        //
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String cstKnm, /* 고객명 */
        String slCtrStrtYm,
        String slCtrEndYm,
        String pdCd, /* 제품코드 */
        String pdNm, /* 제품명 */

        String slCtrSellTpCd, /* 판매유형코드*/
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

    // 저장용 데이터
    @ApiModel("WwdcSalesControlDto-SaveSalesControlReq")
    public record SaveSalesControlReq(
        String rowState,
        @NotBlank
        String cntrNo,
        String cntrSn,
        String cntrDtlNo,
        String cstKnm,
        String slCtrStrtYm,
        String slCtrEndYm,
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

    // 저장용 데이터
    @ApiModel("WwdcSalesControlDto-RemoveSalesControlReq")
    public record RemoveSalesControlReq(
        String rowState,
        @NotBlank
        String cntrNo,
        String cntrSn,
        String cntrDtlNo,
        String cstKnm,
        String slCtrStrtYm,
        String slCtrEndYm,
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
