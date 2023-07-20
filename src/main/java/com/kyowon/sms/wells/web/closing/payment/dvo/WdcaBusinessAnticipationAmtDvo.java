package com.kyowon.sms.wells.web.closing.payment.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaBusinessAnticipationAmtDvo {
    private String inputGubun; /* 입력구분 */

    /* List */
    private String rveNo;
    private int rveSn;
    private String dpClDt;
    private String cntrNo;
    private int cntrSn;
    private String kwGrpCoCd;
    private String rveCd;
    private String ogTpCd;
    private String ichrPrtnrNo;
    private int rveAmt;
    private String cstNo;
    private String bnkCd;
    private String dpCprcnfNo;
    private String pdCd;
    private String pdHclsfId;
    private String pdMclsfId;
    private String pdLclsfId;
    private String rveDt;
    private String perfDt;
    private int bilTn;
    private String procsDvCd;
    private String dpMesCd;
    private String dpTpCd;
    private String rveDvCd;
    private String rvePhCd;
    private String rveplcDvCd;
    private String cdcoCd;
    private String dpDvCd;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String cardAprno;
    private String crcdnoEncr;
    private String dprNm;

    @DBDecField
    private String acnoEncr;
    private String crpAcno;
    private String vncoDvCd;
    private String etcAtamNo;

    private int bznsAtamBlam;
    private String sapDpTpCd;
    private String sapPdDvCd;
    private String sapPdAtcCd;
    private String dgCstId;
}
