package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.27
 */
@Setter
@Getter
public class WsnbServiceProcessingDvo {

    String cntrNo;
    String cntrSn;
    String cntrNoSn;
    String cstSvAsnNo;
    String cstKnm;
    String copnDvNm;
    String cttIchr;
    String hsp;
    String alncBzsNm;
    String frisuYn;
    String sellTpNm;
    String wtholVlvOptYn;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String newAdrZip;
    String radr;
    String cntrCnfmDtm;
    String pdNm;
    String pdGrpCd;
    String pdGrpNm;
    String pdCd;
    String modelNm;
    String scnYn;
    String bfMnftCoId;
    String bcNo;
    String prdtYm;
    String channelNm;
    String gnrdv;
    String rgrp;
    String ogNm;
    String prtnrKnm;
    String cnslMoCn;
    String istDt;
    String useMcn;
    String svBizHclsfNm;
    String svBizDclsfNm;
    String cntrCstNo;
    String asRefriDvNm;
    String bfsvcRefriDvNm;
    String refriDvNm;
    String egerCnrNm;
    String rpbLocaraCd;
    String ichrCnrSdingDidy;
    String prtnr;
    String prtnrPstnDv;
    String siteAwDvNm;
    String siteAwAmt;
    String rglvlGdCd;
    String awAmt;
    String contactCralLocaraTno;
    @DBDecField
    String contactMexnoEncr;
    String contactCralIdvTno;
    String fnlRcpdt;
    String fnlRcpHh;
    String vstDuedt;
    String vstExpHh;
    String vstChYn;
    String vstCnfmdt;
    String vstCnfmHh;
    String dtmChCausNm;
    String dtmChRsonNm;
    String dtmChRsonDtlCn;
    String smsFwYn;
    String promHh;
    String arvDtm;
    String vstFshDtm;
    String istDelay;
    String wkLdtm;
    String wkPrgsStatNm;
    String wkCanRsonNm;
    String wkCanMoCn;
    String fgptDsbYn;
    String badDvNm;
    String asLctNm;
    String asPhnNm;
    String asLctCdNm;
    String svProcsFomNm;
    String imptaRsonNm;
    String asCdEyn;
    String sftAcdnYn;
    String plsSvYn;
    String peslArtcDfrnYn;
    String cwtrWprsVal;
    String wwtWprsVal;
    String per1mOlqVal;
    String istPlcTpCd;
    String rcvryOpt;
    String asCausNm;
    String svProcsCn;
    String pu1Part;
    String pu2Part;
    String pu3Part;
    String pu4Part;
    String pu5Part;
    String pu6Part;
    String pu7Part;
    String pu8Part;
    String pu9Part;
    String pu10Part;
    String partCs;
    String tcfee;
    String bstrCs;
    String etcCs;
    String rveCsTot;
    String cashStlm;
    String cardStlm;
    String elcStlm;
    String adpBilAmt;
    String cstSignHsYn;
    String istEnvrPhoPhDocId; // 설치환경사진 DOC_ID
    String istKitPhoPhDocId; // 설치키트사진 DOC_ID
    String istCelngPhoPhDocId; // 설치천장사진 DOC_ID
    String istEnvrPhoPhFileUid; // 설치환경사진 FILE_UID
    String istKitPhoPhFileUid; // 설치키트사진 FILE_UID
    String istCelngPhoPhFileUid; // 설치천장사진 FILE_UID
    String acpnPrtnrKnm;
    String acpnPrtnrGdNm;

}
