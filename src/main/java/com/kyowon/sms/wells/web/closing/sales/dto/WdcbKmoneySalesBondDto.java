package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbKmoneySalesBondDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권 현황 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchBondRes")
    public record SearchBondRes(
        String baseYm,
        String btdAmt,
        String sellAmt,
        String rveAmt,
        String acuRveAmt,
        String resAmt,
        String mlgRvAmt,
        String mlgCanAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 입금 상세내역 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchDepositRes")
    public record SearchDepositRes(
        String cntrDtlNo,
        String cntrCstNm,
        String rcpYm,
        String istYm,
        String sellAmt,
        String rveAmt,
        String acuRveAmt,
        String resAmt,
        String acuDfaAmt,
        String dfaYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 적립취소 상세내역 Search Result Dto
    @ApiModel("WdcbKmoneySalesBondDto-SearchCancelRes")
    public record SearchCancelRes(
        String rvCanDt,
        String cntrDtlNo,
        String canAmt,
        String ledgCanDt
    ) {}
}
