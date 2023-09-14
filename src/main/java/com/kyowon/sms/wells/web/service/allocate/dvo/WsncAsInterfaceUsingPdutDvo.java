package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncAsInterfaceUsingPdutDvo {
    String cntrNo;
    String cntrCstNo;
    String rcgvpKnm;
    String pdctPdCd;
    String pdctPdNm;
    String basePdCd;
    String basePdNm;
    String bcNo;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String zip;
    String adr;
}
