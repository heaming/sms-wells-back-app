package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaComputationObjectSalesDvo {
    private String cntrNo;
    private String cntrSn;
    private String slClYm;
    private int rentalTn;
}
