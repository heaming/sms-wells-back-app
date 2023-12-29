package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WsnaMdProductReturningGoodsMgtDvo {

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

    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String locaraTno;

    @DBDecField
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
    int partCnt;
    String partCd1;
    String partNm1;
    String partQty1;
    String partCd2;
    String partNm2;
    String partQty2;
    String partCd3;
    String partNm3;
    String partQty3;
    String partCd4;
    String partNm4;
    String partQty4;
    String partCd5;
    String partNm5;
    String partQty5;
    String partCd6;
    String partNm6;
    String partQty6;
    String partCd7;
    String partNm7;
    String partQty7;
    String partCd8;
    String partNm8;
    String partQty8;
    String partCd9;
    String partNm9;
    String partQty9;
    String partCd10;
    String partNm10;
    String partQty10;
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
    //회수송장번호
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

    // 택배 MD 상품 파트너업체
    String prtnrBzsNm;
    String svcTyp;

}
