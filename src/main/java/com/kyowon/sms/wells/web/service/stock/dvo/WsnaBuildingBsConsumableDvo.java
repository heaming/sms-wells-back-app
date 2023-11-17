package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaBuildingBsConsumableDvo {
    String reqYn;
    String bldCd;
    String bldNm;
    String rsppPrtnrNo;
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
    String ostrAkNo;
    int ostrAkSn;
    String ogTpCd;
    String prtnrNo;
    String fxnPdCd;
    String aplcPdCd;

    String fxnPckngUnit;
    String fxnPdNm;
    String fxnSapMatCd;
    String aplcPckngUnit;
    String aplcPdNm;
    String aplcSapMatCd;
    String qty;
}
