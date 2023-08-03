package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbCancelBorControlStatusDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Dto
    @ApiModel("/WdcbCancelBorControlStatusDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String sellTpDtlCd, /* 판매유형상세코드 */

        @NotBlank
        String perfYm /* 실적년월 */
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황(일시불, 금융리스, 정기배송 / 집계) Search Result Dto
    @ApiModel("WdcbCancelBorControlStatusDto-SearchRes")
    public record SearchRes(
        String perfYm, /*실적년월*/
        String cntrDtlSn, /*계약상세번호*/
        String canDtm, /*취소일자*/
        String leaseSlCtrAmt, /*매출조정금액*/
        String leaseSlCanAmt, /*매출취소금액 - 취소조정(취소 잔여 매출액포함)*/
        String ctrTp, /*조정유형*/
        String slCtrRmkCn, /*취소조정사유*/
        String pdNm, /*상품명*/
        String statChRsonNm, /*취소사유*/
        String eotDlqAmt, /*최종연체금*/
        //        String ccamResAmt /*최종위약금*/
        String eotBorAmt /*최종위약금*/
    ) {}
}
