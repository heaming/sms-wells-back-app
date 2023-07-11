package com.kyowon.sms.wells.web.contract.common.dvo;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstCnslBasDvo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaPspcCstCnslChHistDvo extends WctaPspcCstCnslBasDvo {
    private String histStrtDtm;
    private String histEndDtm;
}
