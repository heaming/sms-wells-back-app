package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdbNotPaidMakeAPaymentRgstDto {

    @ApiModel("WwdbNotPaidMakeAPaymentRgstDto-SaveReq")
    public record SaveReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, //계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, //계약일련번호
        @JsonProperty("DP_DV_CD")
        String dpDvCd, //입금구분코드
        @JsonProperty("ORD_TP_CD")
        String ordTpCd, //주문유형코드
        @JsonProperty("SELL_DV_CD")
        Integer sellDvCd, //판매자구분
        @JsonProperty("PRTNR_NO")
        Integer prtnrNo, //파트너번호
        @JsonProperty("DP_TP_NM")
        String dpTpNm, //입금유형명
        @JsonProperty("DP_AMT")
        Integer dpAmt //입금금액
    ) {}
    @Builder
    @ApiModel("WwdbNotPaidMakeAPaymentRgstDto-SaveRes")
    public record SaveRes(
        @JsonProperty("PROCS_RS")
        String procsRs, //처리결과
        @JsonProperty("ERR_MSG")
        String errMsg //에러메시지
    ) {}
}
