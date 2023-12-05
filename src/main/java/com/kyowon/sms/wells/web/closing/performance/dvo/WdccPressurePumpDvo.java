package com.kyowon.sms.wells.web.closing.performance.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdccPressurePumpDvo {
    private String cntrNo;
    private String cntrSn;
    private String itmPdCd;
    private String lctamt;
    private String adnSvStrtYm;
    private String rcpdt;
    private String istDuedt;
    private String istDt;
    private String fnlVstFshDt;
    private String gubn;
    private String adnSvSn;
    private String pdctPdCd; /* 제품상품코드*/
}
