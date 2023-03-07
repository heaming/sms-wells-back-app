package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiCustomerDvo {
    private String cstDv;
    private String cstNo;
    private String itgCstNo;
    private String cstKnm;
    private String cralLocaraTno;
    @DBDecField
    private String mexno;
    private String cralIdvTno;
    private String locaraTno;
    @DBDecField
    private String exno;
    private String idvTno;
    private String emadr;
    private String bryyMmdd;
    private String sexDvCd;
}

