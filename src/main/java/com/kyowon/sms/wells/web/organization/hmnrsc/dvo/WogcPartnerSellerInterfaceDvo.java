package com.kyowon.sms.wells.web.organization.hmnrsc.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WogcPartnerSellerInterfaceDvo {
    private String errCd;
    private String errNm;
    private String prtnrNo;
    private String prtnrKnm;
    private String cralTno;
    private String cralLocaraTno;
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String dgr3LevlOgCd;
    private String dgr3LevlOgNm;

    private String ogCd;
    private String ogNm;
}
