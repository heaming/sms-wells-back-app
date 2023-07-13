package com.kyowon.sms.wells.web.service.interfaces.dvo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsniBarcodeProductInterfaceDvo {

    @JsonProperty(value = "BC_NO")
    String bcNo;
    @JsonProperty(value = "CODE")
    String code;
    @JsonProperty(value = "MESSAGE")
    String message;
    @JsonProperty(value = "BASE_PD_CD")
    String basePdCd;
    @JsonProperty(value = "USWY_DV_CD")
    String uswyDvCd;
    @JsonProperty(value = "PDCT_PD_CD")
    String pdctPdCd;
    @JsonProperty(value = "FARM_YN")
    String farmYn;
    @JsonProperty(value = "ITEM_NM")
    String itemNm;
    @JsonProperty(value = "CNTR_NO")
    String cntrNo;
    @JsonProperty(value = "CNTR_SN")
    String cntrSn;
    @JsonProperty(value = "CUST_NM")
    String custNm;
    @JsonProperty(value = "HNO_NO")
    String hnoNo;
    @JsonProperty(value = "ADDR")
    String addr;
    @JsonProperty(value = "ZIPNO")
    String zipno;
    @JsonProperty(value = "EMP_ID")
    String empId;
    @JsonProperty(value = "EMP_NM")
    String empNm;
    @JsonProperty(value = "DEPT_NM")
    String deptNm;
    @JsonProperty(value = "MNG_HP_NO")
    String mngHpNo;
    @JsonProperty(value = "VST_DT")
    String vstDt;
    @JsonProperty(value = "MNG_TYP")
    String mngTyp;
    @JsonProperty(value = "MNG_CYC")
    String mngCyc;
    @JsonProperty(value = "DBLD_NM")
    String dbldNm;
    @JsonProperty(value = "FILTER_YN")
    String filterYn;
}
