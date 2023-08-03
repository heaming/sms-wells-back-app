package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccSalesPerformDto {
    /**
     * 매출현황 검색 조건
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param fromSlClYy 검색시작일
     * @param toSlClYy 검색종료일
     * @param slClYm 매출년월
     */
    @ApiModel(value = "WdccSalesPerformDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String fromSlClYy,
        String toSlClYy,
        String slClYm
    ) {}

    /**
     * 매출현황 검색 결과
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param slClYm 매출년월
     * @param slStpYn 매출중지
     * @param rentalTn 렌탈차월
     * @param slCtrDvCd 매출조정구분코드
     * @param prmMcn 선납개월
     * @param thmSlSumAmt 매출합계
     * @param borAmt 위약금액
     * @param lsRntf 분실손료
     * @param sumBorAmt 위약금
     * @param dpAmt 입금금액
     * @param eotAtam 선수금액
     * @param thmUcBlam 미수금액
     * @param thmOcDlqAmt 연체금액
     * @param dlqMcn 연체개월수
     * @param btdDlqAddAmt 가산금기초
     * @param thmOcDlqAddAmt 가산금발생
     * @param thmCtrDlqAddAmt 가산금공제
     * @param thmDlqAddDpSumAmt 가산금입금
     * @param thmDlqAddRfndSumAmt 가산금환불
     * @param eotDlqAddAmt 가산금기말
     * @param slStpAmt 매출중지금액
     */
    @ApiModel(value = "WdccSalesPerformDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String slClYm,
        String slStpYn,
        String rentalTn,
        String slCtrDvCd,
        String prmMcn,
        String thmSlSumAmt,
        String borAmt,
        String lsRntf,
        String sumBorAmt,
        String dpAmt,
        String eotAtam,
        String thmUcBlam,
        String thmOcDlqAmt,
        String dlqMcn,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String slStpAmt
    ) {}
}
