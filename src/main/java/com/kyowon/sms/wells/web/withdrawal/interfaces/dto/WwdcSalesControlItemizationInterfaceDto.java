package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WwdcSalesControlItemizationInterfaceDto {

    /* Wells 매출조정 내역 조회 Request Dto */
    @ApiModel("WwdcSalesControlItemizationInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @NotBlank
        @JsonProperty("INQR_YM")
        String inqrYm // 조회년월
    ) {}

    /* Wells 매출조정 내역 조회 Result Dto */
    @ApiModel("WwdcSalesControlItemizationInterfaceDto-SearchRes")
    public record SearchRes(

        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("CST_NM")
        String cstNm, // 고객명
        @JsonProperty("SL_CTR_DV")
        String slCtrDv, // 구분(매출조정)
        @JsonProperty("SL_CTR_AMT")
        String slCtrAmt, // 금액(매출조정금액)
        @JsonProperty("FNIT_NM")
        String fnitNm, // 금융기관명
        @JsonProperty("FNT_AC_CARDNO")
        String fntAcCardno, // 이체계좌카드번호
        @JsonProperty("ACHLDR_NM")
        String achldrNm, // 예금주명
        @JsonProperty("AFTN_RS_CD")
        String aftnRsCd, // 자동이체결과코드
        @JsonProperty("AFTN_RS_NM")
        String aftnRsNm // 자동이체결과명

    ) {}
}
