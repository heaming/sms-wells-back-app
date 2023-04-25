package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaNewManagerBsConsumableDvo {
    String reqYn;
    String bldCd;
    String bldNm;
    String prtnrNo;
    String prtnrNmNo;
    String vstCstN;
    String strWareNo;
    String csmbPdCd;
    String sapMatCd;
    String bfsvcCsmbDdlvTpCd;
    String bfsvcCsmbDdlvQty;
    String fxnDdlvUnitQty;
    String aplcDdlvUnitQty;
    List<String> fxnQtys;
    List<String> aplcQtys;
    String mngtYm;
    String bizStrtdt;
    String bizStrtHh;
    String bizEnddt;
    String bizEndHh;
    String bfsvcCsmbDdlvOjCd;
    String bfsvcCsmbDdlvStatCd;
}
