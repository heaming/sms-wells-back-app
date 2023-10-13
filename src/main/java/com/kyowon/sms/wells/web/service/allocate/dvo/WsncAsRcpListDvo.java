package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncAsRcpListDvo {

    String dgr1LevlOgCd;

    String dgr1LevlOgNm;

    String dgr2LevlOgCd;

    String dgr2LevlOgNm;

    String dgr3LevlOgNm;

    String prtnrNo;

    String prtnrKnm;

    String bldNm;

    String istDt;

    String cntrNo;

    String cntrSn;

    String rcgvpKnm;

    String sapMatCd;

    String pdCd;

    String pdNm;

    String cralLocaraTno;

    @DBDecField
    String mexnoEncr;

    String cralIdvTno;

    String locaraTno;

    @DBDecField
    String exnoEncr;

    String idvTno;

    String newAdrZip;

    String rnadr;

    String rdadr;

    String asAskCn;

    String rcpDt;

    String rcpPrtnrKnm;

    String svBizDclsfNm;

    String weccProcsYn;

    String pcsDt;

    String pcpId;

    String wkPrtnrKnm;

    String wkExcnDt;

    String wkPrgsStatNm;

    String wkCanRsonNm;

    String wkCanCn;
}
