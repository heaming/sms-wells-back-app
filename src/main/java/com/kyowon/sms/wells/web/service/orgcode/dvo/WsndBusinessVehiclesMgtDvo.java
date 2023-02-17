package com.kyowon.sms.wells.web.service.orgcode.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsndBusinessVehiclesMgtDvo {
    String vhcMngtNo;
    String vhcMngtSn;
    String carno;
    String vhcMngtOgTpCd;
    String vhcMngtPrtnrNo;
    String vhcPymdt;
    String dsbEnddt;
    String vhcMngtTpCd;
    String insrAgeCd;
    @DBEncField
    @DBDecField
    String rflngCdnoEncr;
    @DBEncField
    @DBDecField
    String hipsCdnoEncr;
    String vhcDsbRmkCn;
    String orglFnlMdfcDtm;

}
