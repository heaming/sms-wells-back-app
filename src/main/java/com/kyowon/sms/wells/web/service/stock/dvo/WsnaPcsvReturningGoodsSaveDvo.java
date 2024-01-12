package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WsnaPcsvReturningGoodsSaveDvo {

    String findGb;
    String svBizDclsfCd;
    String svBizDclsfNm;
    String wkPrgsStatCd;
    String wkPrgsStatNm;
    String reWkPrgsStatNm;
    String bcNo;
    String cntrNo;
    String cntrSn;
    String cntrDtlNo;
    String rcgvpKnm;
    String cralLocaraTno;

    String mexnoEncr;
    String cralIdvTno;
    String locaraTno;

    String exnoEncr;
    String idvTno;
    String basePdCd;
    String basePdNm;
    String pdctPdCd;
    String cntrPdStrtdt;
    String fwSppIvcNo;
    String pcsvRcgvDt;
    String rcpdt;
    String pdUseDc;
    String cmptGd;
    String fnlGb;
    String rcpOgTpCd;
    String rcpIchrPrtnrNo;
    String prtnrKnm;
    String wkWareNo;
    String wareNm;
    String wareMngtPrtnrNo;
    String wareMngtPrtnrOgTpCd;
    String asLctCd;
    String asPhnCd;
    String asCausCd;
    String rpbLocaraCd;
    String siteAwSvTpCd;
    String siteAwAtcCd;
    String pdUswyCd;
    String asRefriDvCd;
    String bfsvcRefriDvCd;
    String urgtDvCd;
    String cntrCstNo;
    String sellTpCd;
    String sellTpNm;
    String sellTpDtlCd;
    String sellTpDtlNm;
    String cntrDtlStatCd;
    String cntrDtlStatNm;
    String cntrRcpFshDtm;
    String adrId;
    String newAdrZip;
    String rnadr;
    String rdadr;
    String rsgAplcDt;
    String rsgFshDt;
    String arvDt;
    String dtmChRsonCd;
    String pscocd;
    String dtmChRsonDtlCn;
    String reqdDt;
    String mngrDvCd;
    String dgr1LevlOgId;
    String dgr3LevlOgId;
    String editYn;
    String svBizHclsfCd;
    String pdQty;
    String istDt;
    String cstSvAsnNo;
    String ogId;
    String ogTpCd;
    String prtnrNo;
    String clnSppIvcNo;

    // 물류 수불처리
    String ostrTpCd;
    String ostrDt;
    String itmOstrNo;
    String ostrSn;
    String rmkCn;
    String logisticsPdCd;
    String logisticsPdNm;
    String logisticsPdQty;
    String fnlItmGdCd;
    String asnDt;

    List<WsnaPcsvReturningGoodsSaveProductDvo> products;

}
