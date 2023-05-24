package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdcdRefundTotalAmountSalesStopDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불총액/매출중지 내역 조회
    @Builder
    @ApiModel(value = "WdcdRefundTotalAmountSalesStopDto-FindReq")
    public record FindReq(
        String bsdt
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불총액/매출중지 내역 조회
    @Builder
    @ApiModel(value = "WdcdRefundTotalAmountSalesStopDto-FindSalesControlRes")
    public record FindSalesControlRes(
        String cntrNo,
        String cntrSn,
        String gubunCode,
        String cstNo,
        String cstKnm,
        String sumAmt
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불총액/매출중지 내역 조회
    @Builder
    @ApiModel(value = "WdcdRefundTotalAmountSalesStopDto-FindRefundRes")
    public record FindRefundRes(
        String cntrNo,
        String cntrSn,
        String gubunCode,
        String cstNo,
        String cstKnm,
        String sumAmt
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불총액/매출중지 내역 조회
    @Builder
    @ApiModel(value = "WdcdRefundTotalAmountSalesStopDto-FindSalesStopRes")
    public record FindSalesStopRes(
        String cntrNo,
        String cntrSn,
        String gubunCode,
        String cstNo,
        String cstKnm,
        String sumAmt
    ) {
    }
}
