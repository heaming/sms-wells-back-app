package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WwdbDepositDto {

    // *********************************************************
    // Object Dto
    // *********************************************************

    @ApiModel("WwdbDepositDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String dpDt, /*입금일자*/
        @NotBlank
        String perfDt, /*실적년도*/
        @NotBlank
        String cntrNo, /*계약번호*/
        @NotBlank
        String cntrSn, /*계약일련번호*/
        @NotBlank
        String sellTpCd, /*업무구분*/
        @NotBlank
        String sellTpDtlCd, /*업무유형*/
        @NotBlank
        String rveCd, /*수납코드*/
        @NotBlank
        String dpTpCd, /*입금구분*/
        @NotBlank
        String dpAmt, /*입금금액*/
        @NotBlank
        String cardNo, /*카드번호*/
        String istmMcn, /*할부개월*/
        @NotBlank
        String aprno, /*승인번호*/
        String cdonrNm, /*카드주명*/
        String hdwrRgstYn, /*수기카드*/
        @NotBlank
        String dpMesCd, /*입금구분*/
        @NotBlank
        String prtnrNo, /*파트너번호*/
        @NotBlank
        String ogTpCd /*조직유형코드*/

    ) {}

    @ApiModel("WwdbDepositDto-SaveRes")
    public record SaveRes(
        String rveNo, /*수납번호*/
        String procsRsCd, /*결과코드*/
        String procsRsCntn /*결과내용*/

    ) {}
}
