package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaEstimateCcamDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctaEstimateCcamDto-SearchReq")
    public record SearchReq(
        String slClYm,
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaEstimateCcamDto-SearchRes")
    public record SearchRes(
        String prmMcn, /* 선납개월 */
        String prmStrtYymm, /* 선납기간-시작년월 */
        String prmEndYymm, /* 선납기간-종료년월 */
        String rentalAmt, /* 월렌탈료 */
        String rentalDscAmt, /* 할인금액 */
        String dscAmt, /* 할인 총 금액(선납기간기준) */
        String totPrmAmt, /* 선납예상총금액 */
        String nomSlAmt, /* 정상매출 */
        String rentalDc, /* 렌탈일수 */
        String rplmDt, /* 교체일자 */
        String spmtSlAmt, /* 추가매출 */
        String nomDscAmt, /* 정상할인 */
        String spmtDscAmt, /* 추가할인 */
        String slCtrAmt, /* 매출조정 */
        String thmSlSumAmt, /* 매출금액 */
        String slSumVat, /* 매출VAT */
        String slAggAmt, /* 매출누계 */
        String dscAggAmt, /* 할인누계 */
        String ctrAggAmt, /* 조정누계 */
        String thmUcBlam, /* 매출잔액 */
        String btdDlqAddAmt, /* 연체가산금 */
        String thmDlqAddDpSumAmt, /* 입 */
        String thmDlqAddRfndSumAmt, /* 출 */
        String thmCtrDlqAddAmt /* 조정 */
    ) {}
}
