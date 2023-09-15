package com.kyowon.sms.wells.web.bond.consultation.ivo.EAI_WSVO1013.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResIvo {
    @JsonProperty("ROWNUM")
    private String rownum;

    @JsonProperty("CNSL_NO")
    private String cnslNo;

    @JsonProperty("CNSL_DT")
    private String cnslDt;

    @JsonProperty("CNSL_ST_DT")
    private String cnslStDt;

    @JsonProperty("CNSL_ST_TM")
    private String cnslStTm;

    @JsonProperty("CST_NO")
    private String cstNo;

    @JsonProperty("JOB_CD")
    private String jobCd;

    @JsonProperty("JOB_NM")
    private String jobNm;

    @JsonProperty("CENTER_CD")
    private String centerCd;

    @JsonProperty("CENTER_NM")
    private String centerNm;

    @JsonProperty("SELL_TP_CD")
    private String sellTpCd;

    @JsonProperty("CNTR_NO")
    private String cntrNo;

    @JsonProperty("CNTR_SN")
    private String cntrSn;

    @JsonProperty("CNSL_CN")
    private String cnslCn;

    @JsonProperty("CST_NM")
    private String cstNm;

    @JsonProperty("CST_TNOA")
    private String cstTnoa;

    @JsonProperty("CST_TNOB")
    private String cstTnob;

    @JsonProperty("CST_TNOC")
    private String cstTnoc;

    @JsonProperty("CNSL_ED_DT")
    private String cnslEdDt;

    @JsonProperty("CNSL_ED_TM")
    private String cnslEdTm;

    @JsonProperty("CALL_TP_CD")
    private String callTpCd;

    @JsonProperty("REG_DT")
    private String regDt;

    @JsonProperty("REG_TM")
    private String regTm;

    @JsonProperty("REG_USER_ID")
    private String regUserId;

    @JsonProperty("REG_USER_NM")
    private String regUserNm;

    @JsonProperty("MOD_DT")
    private String modDt;

    @JsonProperty("MOD_TM")
    private String modTm;

    @JsonProperty("MOD_USER_ID")
    private String modUserId;

    @JsonProperty("MOD_USER_NM")
    private String modUserNm;
}
