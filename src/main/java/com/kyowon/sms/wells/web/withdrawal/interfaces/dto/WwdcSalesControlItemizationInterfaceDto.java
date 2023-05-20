package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WwdcSalesControlItemizationInterfaceDto {

    /* Wells 매출조정 내역 조회 Request Dto */
    @ApiModel("WwdcSalesControlItemizationInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @NotBlank @JsonProperty("SL_CTR_YM")
        String slCtrYm // 조회년월
    ) {}

    /* Wells 매출조정 내역 조회 Result Dto */
    @ApiModel("WwdcSalesControlItemizationInterfaceDto-SearchRes")
    public record SearchRes(

        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("SL_CTR_DV_NM")
        String slCtrDvNm, // 구분(매출조정)
        @JsonProperty("CST_NM")
        String cstNm, // 고객명
        @JsonProperty("SL_CTR_AMT")
        String slCtrAmt, // 금액(매출조정금액)
        @JsonProperty("ATTN_NM")
        String attnNm // 자동이체정보(카드/은행/기타)

    ) {}
}
