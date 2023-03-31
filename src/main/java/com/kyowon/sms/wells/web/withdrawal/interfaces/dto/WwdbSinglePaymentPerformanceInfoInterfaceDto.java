package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WwdbSinglePaymentPerformanceInfoInterfaceDto {

    @ApiModel("WwdbSinglePaymentPerformanceInfoInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn // 계약일련번호
    ) {}


    @ApiModel("WwdbSinglePaymentPerformanceInfoInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("PERF_DT")
        String perfDt, // 실적일자
	    @JsonProperty("DQL_AMT")
        String dqlAmt, // 연체금액
	    @JsonProperty("RVE_DV_NM")
        String rveDvNm, //구분(할부/매변/손료/위약금/연체가산금)
	    @JsonProperty("DP_TP_NM")
        String dpTpNm, // 유형(지로/간이/은행/은행이체/가상계좌/카드이체/영업부/수당/RDS/전금/수수료/대손/앱할인)
	    @JsonProperty("DP_AMT")
        String dpAmt, // 입금금액(환불인 경우 음수로 표시)
	    @JsonProperty("PERF_YM")
        String perfYm // 실적년월
    ) {}
}
