package com.kyowon.sms.wells.web.closing.expense.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
        @JsonProperty("BSDT")
        String bsdt,
        @JsonProperty("GUBUN_CODE")
        String gubunCode
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불총액/매출중지 내역 조회
    @Builder
    @ApiModel(value = "WdcdRefundTotalAmountSalesStopDto-FindSalesControlRes")
    public record FindRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("GUBUN_CODE")
        String gubunCode,
        @JsonProperty("CST_NO")
        String cstNo,
        @JsonProperty("CST_KNM")
        String cstKnm,
        @JsonProperty("SUM_AMT")
        String sumAmt
    ) {}
}
