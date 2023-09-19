package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncAsInterfaceCustInfoDvo {

    @JsonProperty(value = "CNTR_NO")
    String cntrNo;
    @JsonProperty(value = "CST_NO")
    String cstNo;
    @JsonProperty(value = "CST_KNM")
    String cstKnm;
    @JsonProperty(value = "CNTR_CNFM_DTM")
    String cntrCnfmDtm;
    @JsonProperty(value = "PD_CD")
    String pdCd;
    @JsonProperty(value = "PD_NM")
    String pdNm;
    @JsonProperty(value = "CRAL_LOCARA_TNO")
    String cralLocaraTno;
    @JsonProperty(value = "MEXNO_ENCR")
    String mexnoEncr;
    @JsonProperty(value = "CRAL_IDV_TNO")
    String cralIdvTno;
    @JsonProperty(value = "LOCARA_TNO")
    String locaraTno;
    @JsonProperty(value = "EXNO_ENCR")
    String exnoEncr;
    @JsonProperty(value = "IDV_TNO")
    String idvTno;
    @JsonProperty(value = "NEW_ADR_ZIP")
    String newAdrZip;
    @JsonProperty(value = "RNADR")
    String rnadr;
    @JsonProperty(value = "RDADR")
    String rdadr;
    @JsonProperty(value = "ADR_DV_CD")
    String adrDvCd;
    @JsonProperty(value = "LTN_ADR")
    String ltnAdr;
    @JsonProperty(value = "RCGVP_KNM")
    String rcgvpKnm;
    @JsonProperty(value = "BRYY_MMDD")
    String bryyMmdd;
    @JsonProperty(value = "SEX_DV_CD")
    String sexDvCd;
    @JsonProperty(value = "CNTR_CST_NO")
    String cntrCstNo;
    @JsonProperty(value = "ITEM_NM")
    String itemNm;
    @JsonProperty(value = "ITEM_DESC")
    String itemDesc;

}
