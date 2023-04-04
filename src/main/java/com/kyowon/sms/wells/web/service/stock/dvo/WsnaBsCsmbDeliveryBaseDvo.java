package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaBsCsmbDeliveryBaseDvo {
    String mngtYm;
    String csmbPdCd;
    String itmKnm;
    String mngtUnitCd;
    String goDvCd;
    int goUprc;
    int boxUnitQty;
    String rmkCn;
    String bfsvcCsmbDdlvOjCd;
    String bfsvcCsmbDdlvOrtYn;
    String bfsvcCsmbDdlvTpCd;
    String bfsvcCsmbDdlvCmptBaseCd;
    String bfsvcCsmbDdlvOjPdGrpCd;
    String bfsvcCsmbDdlvOjAccTpCd;
    int bfsvcCsmbDdlvUnitAccN;
    int bfsvcCsmbDdlvUnitQty;
    int bfsvcCsmbAplcLmQty;
    int sortOdr;
}
