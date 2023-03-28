package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbKmoneySalesBondDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권 현황 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchBondRes")
    public record SearchBondRes(
        String cwinst,
        String lcbast,
        String lctamt,
        String cciamt,
        String cwiamt,
        String lcblnc,
        String lcacmt,
        String lccact
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 입금 상세내역 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchDepositRes")
    public record SearchDepositRes(
        String lcordr,
        String cntrNo,
        String cntrSn,
        String lccnam,
        String lccrtt,
        String lcslet,
        String sellAmt,
        String cwiamt,
        String cwbamt,
        String lcblnc
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 적립취소 상세내역 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchCancelRes")
    public record SearchCancelRes(
        String dpcndt,
        String dpordr,
        String canAmt,
        String lccant
    ) {
    }
}
