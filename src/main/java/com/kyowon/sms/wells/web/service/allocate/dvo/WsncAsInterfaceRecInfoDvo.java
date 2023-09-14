package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncAsInterfaceRecInfoDvo {
    String cntrNo;
    String inChnlDvCd;
    String svBizHclsfCd;
    String rcpdt;
    String asIstOjNo;
    String svBizDclsfCd;
    String svBizDclsfNm;
    String cnslMoCn;
    String cltnYn;
    String vstCnfmDt;
    String vstCnfmM;
    String vstExpDt;
    String vstExpM;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String locaraTno;
    @DBDecField
    String exnoEncr;
    String idvTno;
    String newAdrZip;
    String rnadr;
    String rdadr;
    String adrDvCd;
}
