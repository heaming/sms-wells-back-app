package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundPresentStateDvo {
    private String cstNo;
    private String cntrNo;
    private String cstKnm;
    private String sellTpNm;
    private String pdNm;
    private String tno;
    private String mpno;
    private String cralLocaraTno;
    @DBEncField
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String partAmt;
    private String tcfee;
    private String trcs;
    private String etcCs;
    private String rveIzAmt;
    private String dpAmt;
    private String cardDpAmt;
    private String cshDpAmt;
    private String rfndRqdt;
    private String rfndDsbDt;
    private String rfndAkAmt;
    private String cshRfndFnitCd;
    private String cshRfndFnitNm;
    @DBDecField
    private String cshRfndAcnoEncr;
    private String cardRfndFnitCd;
    private String cardRfndFnitNm;
    @DBDecField
    private String cardRfndCrcdnoEncr;

    public String getMpno(){
        return (cralLocaraTno != null && mexnoEncr != null && cralIdvTno != null) ? (cralLocaraTno +"-"+ mexnoEncr +"-"+ cralIdvTno) : (cralLocaraTno + mexnoEncr + cralIdvTno);
    }

    public String getCardRfndCrcdnoEncr(){
        return (cardRfndCrcdnoEncr != null && cardRfndCrcdnoEncr.length() > 15 ) ? (cardRfndCrcdnoEncr.substring(0,4) +"-"+ cardRfndCrcdnoEncr.substring(4,8) +"-"+ cardRfndCrcdnoEncr.substring(8,12) +"-"+ cardRfndCrcdnoEncr.substring(12,16)) : cardRfndCrcdnoEncr;
    }
}
