package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 조회 결과 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleSearchDvo {

    private String dpYn;
    private String ostrYn;
    private String refriDiv;
    private String shipDiv;
    private String receiptDiv;
    private String cntrNo;
    private String cstNm;
    private String sppOrdNo;
    private String mchnModel;
    private String mchnCstNo;
    private String mchnCstNm;
    private String ctrlPkg;
    private String shipPkg;
    private String sding1;
    private BigDecimal qty1;
    private String sding2;
    private BigDecimal qty2;
    private String sding3;
    private BigDecimal qty3;
    private String sding4;
    private BigDecimal qty4;
    private String sding5;
    private BigDecimal qty5;
    private String mchnDemDt;
    private String receiptDt;
    private String vstDt;
    private String ostrScheDt;
    private String bsFshDt;
    private String dpDt;
    private String ostrCnfmDt;
    private String sdingRcgWareNm;
    private String vstCeter;
    private String vstIchr;

    private String ichrCralLocaraTno;
    @DBDecField
    private String ichrMexnoEncr;
    private String ichrCralIdvTno;

    private String cstCralLocaraTno;
    @DBDecField
    private String cstMexnoEncr;
    private String cstCralIdvTno;
    private String cstZip;
    private String cstAdr;

    private String refriDvCd;
    private int cntrSn;
    private String svBizHclSfCd;
    private String svBizDclsfCd;
    private int sppPlanSn;

    private String sdingPdCd1;

    private String sowDt1;
    private String sdingPdCd2;
    private String sowDt2;
    private String sdingPdCd3;
    private String sowDt3;
    private String sdingPdCd4;
    private String sowDt4;
    private String sdingPdCd5;
    private String sowDt5;

    private String sdingPkgPdCd;
    private String mngrDvCd;
    private String dpEpttNm;
    private String ogTpCd;
    private String prtnrNo;
    private String cntrAdrpcId;
    private BigDecimal recapCsAmt;
    private String sdingMcnrPdCd;
    private String sppDvCd;
    private String cstSvAsnNo;
}
