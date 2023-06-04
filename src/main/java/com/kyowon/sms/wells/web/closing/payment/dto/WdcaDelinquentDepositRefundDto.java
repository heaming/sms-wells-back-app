package com.kyowon.sms.wells.web.closing.payment.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcaDelinquentDepositRefundDto {
    // *********************************************************
    // save Dto
    // *********************************************************
    // 연체 입금/환불 반영 SAVE Dto
    @ApiModel("WdcaDelinquentDepositRefundDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String kwGrpCoCd,
        @NotBlank
        String rveNo, /*수납번호*/
        @NotBlank
        String rveSn, /*수납일련번호*/
        @NotBlank
        String dpDvCd, /*입금구분코드*/
        @NotBlank
        String dpMesCd, /*입금수단코드*/
        @NotBlank
        String dpTpCd, /*입금유형코드*/
        @NotBlank
        String rveDvCd, /*수납구분코드*/
        @NotBlank
        String rveCd, /*수납코드*/
        @NotBlank
        String rveDt, /*수납일자*/
        @NotBlank
        String perfDt, /*실적일자*/
        @NotBlank
        String rveAmt /*수납금액*/
    ) {}
}
