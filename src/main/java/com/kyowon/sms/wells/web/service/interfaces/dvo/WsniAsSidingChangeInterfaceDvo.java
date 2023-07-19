package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsniAsSidingChangeInterfaceDvo {
    String tpCd; /*유형코드*/
    String rcpdt; /*접수일자*/
    String cntrNo; /*계약번호*/
    String pdCd; /*상품코드*/
    String pdNm; /*상품명*/
    String bcNo; /*바코드번호*/
    String wkTp; /*작업유형*/
    String asRson; /*AS사유)*/
    String vstRqdt; /*방문요청일자*/
    String wkExcnDt; /*작업수행일자*/
    String wkPrgsStat; /*작업진행상태*/
    String pextSding; /*기존모종*/
    String chSding; /*변경모종*/
    String apyDt; /*적용일자*/
}
