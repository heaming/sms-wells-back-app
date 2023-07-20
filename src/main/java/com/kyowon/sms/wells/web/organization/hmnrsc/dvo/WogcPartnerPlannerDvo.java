package com.kyowon.sms.wells.web.organization.hmnrsc.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import com.sds.sflex.system.config.datasource.PageInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WogcPartnerPlannerDvo {
    String dgr1LevlOgCd;
    String dgr1LevlOgNm;
    String dgr2LevlOgCd;
    String dgr2LevlOgNm;
    String dgr3LevlOgCd;
    String dgr3LevlOgNm;
    String ogCd;
    String mpiPrtnrKnm;
    String prtnrNo;
    String qlfDvCd;
    String rcmdrPrtnrNo;
    String pbPrtnrKnm;
    String cntrDt;
    String fnlCltnDt;
    String rcntrDt;
    String aplcSn;
    String mngtYm;
    PageInfo pageInfo;
}
