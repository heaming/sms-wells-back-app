package com.kyowon.sms.wells.web.service.interfaces.dvo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsniAsSidingChangeInterfaceDvo {
    @JsonProperty(value = "TP_CD")
    String tpCd; /*유형코드*/
    @JsonProperty(value = "RCPDT")
    String rcpdt; /*접수일자*/
    @JsonProperty(value = "CNTR_NO")
    String cntrNo; /*계약번호*/
    @JsonProperty(value = "PD_CD")
    String pdCd; /*상품코드*/
    @JsonProperty(value = "PD_NM")
    String pdNm; /*상품명*/
    @JsonProperty(value = "BC_NO")
    String bcNo; /*바코드번호*/
    @JsonProperty(value = "WK_TP")
    String wkTp; /*작업유형*/
    @JsonProperty(value = "AS_RSON")
    String asRson; /*AS사유)*/
    @JsonProperty(value = "VST_RQDT")
    String vstRqdt; /*방문요청일자*/
    @JsonProperty(value = "WK_EXCN_DT")
    String wkExcnDt; /*작업수행일자*/
    @JsonProperty(value = "WK_PRGS_STAT")
    String wkPrgsStat; /*작업진행상태*/
    @JsonProperty(value = "PEXT_SDING")
    String pextSding; /*기존모종*/
    @JsonProperty(value = "CH_SDING")
    String chSding; /*변경모종*/
    @JsonProperty(value = "APY_DT")
    String apyDt; /*적용일자*/
    @JsonProperty(value = "RCP_DT_START")
    String rcpDtStart;
    @JsonProperty(value = "RCP_DT_END")
    String rcpDtEnd;
}
