package com.kyowon.sms.wells.web.service.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsniServiceHistoryInterfaceDvo {
    String custNm;
    String inGb;
    String inGbNm;
    String sellTpCd;
    String sellTpCdNm;
    String cntrNo;
    Integer cntrSn;
    String pdCd;
    String pdCdNm;
    String gdsGr;
    String gubun;
    String svBizHclsfCd;
    String svBizHclsfCdNm;
    String svBizMclsfCd;
    String svBizMclsfCdNm;
    String svBizLclsfCd;
    String svBizLclsfCdNm;
    String svBizDclsfCd;
    String svBizDclsfCdNm;
    String regId;
    String regKnm;
    String regMemo;
    String vstSchDtTm;
    String recdDtTm;
    String accDtTm;
    String arrDtTm;
    String cfrmDtTm;
    String wrkDtTm;
    String prtnrOgTpCd;
    String prtnrNo;
    String prtnrKnm;
    String telNo1;
    @DBDecField
    String telNo2;
    String telNo3;
    String ogCd;
    String ogName;
    String jurisdictionOgNm;
    String chagGb;
    String retYn;
    String bcNo;
    String procTxt;
    String cancNm;
    String cancMemo;
    String partUseYn;
    String filterCd1;
    String filterCd2;
    String filterCd3;
    String filterCd4;
    String filterCd5;
    String filterCd6;
    String filterCd1Nm;
    String filterCd2Nm;
    String filterCd3Nm;
    String filterCd4Nm;
    String filterCd5Nm;
    String filterCd6Nm;
    String partCd1;
    String partCd2;
    String partCd3;
    String chulgoC;
    String chulgoCYn;
    String chulgoU;
    String chulgoUYn;
    Integer reqAmtTot;
    Integer accTot;
    String stopDt;
    String rcpStatCd;
    String rcpStatCdNm;
    String cltnDt;
    Integer asMths;
    Integer bsMths;
    String inDlvComp;
    String inInvoice;
    String outDlvComp;
    String outInvoice;
    String cstSvAsnNo1;
    String cstSvAsnNo2;
    Integer fsVstCnt;
}
