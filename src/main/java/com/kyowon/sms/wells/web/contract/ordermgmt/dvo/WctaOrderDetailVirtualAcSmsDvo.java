package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctaOrderDetailVirtualAcSmsDvo {
    private String templateId;
    private String cstNm;
    private String cntrNo;
    private String cstNo;
    private int cntrSn;
    private String vacBnkNm;
    private String vacNo;
    private String template;
    private String msgTit;
    private String cralLocaraTno;
    private String mexnoEncr;
    private String cralIdvTno;
}
