package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_WSVI1007.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WsniCubigVisitStopReqIvo {
    @JsonProperty(value = "CNTR_NO")
    String cntrNo;
    @JsonProperty(value = "CNTR_SN")
    String cntrSn;
    @JsonProperty(value = "APY_DT")
    String apyDt;
    @JsonProperty(value = "SPP_STP_DV_CD")
    String sppStpDvCd;
    @JsonProperty(value = "TN1_STP_YM")
    String tn1StpYm;
    @JsonProperty(value = "TN2_STP_YM")
    String tn2StpYm;
    @JsonProperty(value = "TN3_STP_YM")
    String tn3StpYm;
    @JsonProperty(value = "TN4_STP_YM")
    String tn4StpYm;
    @JsonProperty(value = "SPP_AK_SPF_DT")
    String sppAkSpfDt;
    @JsonProperty(value = "OG_TP_CD")
    String ogTpCd;
    @JsonProperty(value = "PRTNR_NO")
    String prtnrNo;
}
