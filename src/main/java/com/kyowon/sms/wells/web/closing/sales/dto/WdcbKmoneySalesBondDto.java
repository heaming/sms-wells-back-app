package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbKmoneySalesBondDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권 현황 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchBondRes")
    public record SearchBondRes(
        String baseYm, /*기준년월*/
        String btdAmt, /*기초금액*/
        String sellAmt, /*발생금액*/
        String rveAmt, /*당월입금액*/
        String acuRveAmt, /*누적입금액*/
        String resAmt, /*잔액*/
        String mlgRvAmt, /*통합적립액*/
        String mlgCanAmt /*통합적립취소액*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 입금 상세내역 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchDepositRes")
    public record SearchDepositRes(
        String cntrDtlNo, /*계약상세번호*/
        String cntrCstNm, /*고객명*/
        String rcpYm, /*접수년월*/
        String istYm, /*설치년월*/
        String sellAmt, /*판매금*/
        String rveAmt, /*당월입금액*/
        String acuRveAmt, /*누적입금액*/
        String resAmt, /*잔액*/
        String acuDfaAmt, /*누적대손입금액*/
        String dfaYm /*대손년월*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 적립취소 상세내역 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchCancelRes")
    public record SearchCancelRes(
        String rvCanDt, /*적립취소일자*/
        String cntrDtlNo, /*계약상세번호*/
        String canAmt, /*취소금액*/
        String ledgCanDt /*원장취소일자*/
    ) {}
}
