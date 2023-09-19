package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncAsInterfaceServContDvo {
    @JsonProperty(value = "CNTR_NO")
    String cntrNo;
    @JsonProperty(value = "CNTR_SN")
    String cntrSn;
    @JsonProperty(value = "CST_SV_ASN_NO")
    String cstSvAsnNo;
    @JsonProperty(value = "AS_IST_OJ_NO")
    String asIstOjNo;
    @JsonProperty(value = "WK_EXCN_DT")
    String wkExcnDt;
    @JsonProperty(value = "WK_PRTNR_KNM")
    String wkPrtnrKnm;
    @JsonProperty(value = "RGST_DT")
    String rgstDt;
    @JsonProperty(value = "RCPDT")
    String rcpdt;
    @JsonProperty(value = "RCP_HH")
    String rcpHh;
    @JsonProperty(value = "PD_CD")
    String pdCd;
    @JsonProperty(value = "PD_NM")
    String pdNm;
    @JsonProperty(value = "SV_PROCS_CN")
    String svProcsCn;
    @JsonProperty(value = "CNSL_MO_CN")
    String cnslMoCn;
    @JsonProperty(value = "SV_BIZ_HCLSF_CD")
    String svBizHclsfCd;
    @JsonProperty(value = "SV_BIZ_HCLSF_NM")
    String svBizHclsfNm;
    @JsonProperty(value = "SV_BIZ_DCLSF_CD")
    String svBizDclsfCd;
    @JsonProperty(value = "SV_BIZ_DCLSF_NM")
    String svBizDclsfNm;
    @JsonProperty(value = "ITEM_NM")
    String itemNm;
    @JsonProperty(value = "ITEM_DESC")
    String itemDesc;
}
