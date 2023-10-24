package com.kyowon.sms.wells.web.service.orgcode.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsndBusinessVehiclesMgtDvo {
    String vhcMngtNo;
    int vhcMngtSn;
    String carno;
    String vhcMngtOgTpCd;
    String vhcMngtPrtnrNo;
    String vhcPymdt;
    String dsbEnddt;
    String vhcMngtTpCd;
    String insrAgeCd;
    @DBDecField
    String rflngCdnoEncr;
    @DBDecField
    String hipsCdnoEncr;
    String vhcDsbRmkCn;
    String orglFnlMdfcDtm;

    String ogNm;
    String ogTpCd;
    String prtnrNo;
    String prtnrKnm;
    String rolDvCd;
    String cntrDt;
    String ogId;
    String carnm;
    String vhcMngtTpNm;
}
