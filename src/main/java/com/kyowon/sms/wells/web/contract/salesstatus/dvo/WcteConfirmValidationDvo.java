package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WcteConfirmValidationDvo {
    private String cntrNo;
    private int cntrSn;
    private String canDt;
    private String cntrCnfmYn;
    private String istDt;
    private String sppDuedt;
    private String cpsDt;

    private String sellTpCd;
    private String secPdYn;
    private String ostrRgstYn;
}
