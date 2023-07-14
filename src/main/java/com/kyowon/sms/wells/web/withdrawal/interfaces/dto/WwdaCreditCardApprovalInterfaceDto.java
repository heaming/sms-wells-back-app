package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdaCreditCardApprovalInterfaceDto {

    /* wells 카드즉시 결제 Request Dto */
    @ApiModel("WwdaCreditCardApprovalInterfaceDto-SaveReq")
    public record SaveReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("FNIT_CD")
        String fnitCd, // 금융기관코드
        @JsonProperty("CARDNO")
        String cardNo, // 카드번호
        @JsonProperty("CDONR_NM")
        String cdonrNm, /*카드주명*/
        @JsonProperty("CRDCD_EXPDT_YM")
        String crdcdExpdtYm, /*카드유효기간년월*/
        @JsonProperty("INDV_ENTRP_DV_CD")
        String indvEntrpDvCd, /*개인사업자구분*/
        @JsonProperty("BRYY_MMDD_BZOP_NO")
        String bryyMmddBzopNo, /*생년월일/사업자번호*/
        @JsonProperty("STLM_AMT")
        String stlmAmt /*결제금액*/
    ) {}

    /* wells 카드즉시 결제 Response Dto */
    @ApiModel("WwdaCreditCardApprovalInterfaceDto-SaveRes")
    @Builder
    public record SaveRes(
        @JsonProperty("RS_CD")
        String rsCd, /*결과코드*/
        @JsonProperty("RS_CD_NM")
        String rsCdNm /*결과코드명*/
    ) {}

    /* wells 카드즉시 결제 알림톡 발송 Request Dto */
    @ApiModel("WwdaCreditCardApprovalInterfaceDto-SaveNotificationReq")
    public record SaveNotificationReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("MPNO")
        String mpNo, // 전화번호
        @JsonProperty("STLM_AMT")
        String stlmAmt /*결제금액*/
    ) {}
}
