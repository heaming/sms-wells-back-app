package com.kyowon.sms.wells.web.contract.common.dvo;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstBasDvo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaPspcCstChHistDvo extends WctaPspcCstBasDvo {
    private String histStrtDtm;
    private String histEndDtm;
}
