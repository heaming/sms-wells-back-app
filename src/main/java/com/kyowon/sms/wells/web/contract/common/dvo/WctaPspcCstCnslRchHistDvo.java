package com.kyowon.sms.wells.web.contract.common.dvo;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstCnslRcmdIzDvo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaPspcCstCnslRchHistDvo extends WctaPspcCstCnslRcmdIzDvo {
    private String histStrtDtm;
    private String histEndDtm;
}
