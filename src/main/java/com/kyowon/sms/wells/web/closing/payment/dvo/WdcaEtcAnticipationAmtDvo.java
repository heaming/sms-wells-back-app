package com.kyowon.sms.wells.web.closing.payment.dvo;

// TODO: 테이블 미정의로 추후 재작업 필요

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaEtcAnticipationAmtDvo {
    private String etcAtamNo;
    private String etcAtamOcDt;
    private String itgDpNo;
    private String etcAtamTpCd;
    private String etcAtamProcsCd;
    private String etcAtamPrcsdt;
    private int etcAtamProcsAmt;
    private String rveNo;
    private int rveSn;
    private String rfndRcpNo;
    private String rveDt;
    private int bilTn;
    private String procsDvCd;
    private String dpDvCd;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String pdCd;
    private String pdHclsfId;
    private String pdMclsfId;
    private String pdLclsfId;
    private String dpMesCd;
    private String dpTpCd;
    private String cdcoCd;
    private String cardAprno;
    private String crcdnoEncr;
    private String bnkCd;
    private String dprNm;

    @DBDecField
    private String acnoEncr;
    private String vncoDvCd;
    private String cntrNo;
    private int cntrSn;
    private String kwGrpCoCd;
    private String rveCd;
    private String rveDvCd;
    private String rvePhCd;
    private String rveplcDvCd;
    private String ogTpCd;
    private String ichrPrtnrNo;
    private String cstNo;
    private int etcAtamSn;
    private String sapDpTpCd;
    private String sapPdDvCd;
    private String sapPdAtcCd;
    private String dgCstId;
    private String crpAcno;
    private String dpSlipTrsNo;
    private String sapSlpno;
    private String sapSlipMsg;
}
