package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRentalMembershipPrepaymentInterfaceDvo {

    private String rcnt; // 차월
    private String totPrpm; // 총선납개월
    private String dscRat; // 선납시 할인율
    private String dscMm; // 할인 대상 선납개월
    private String dscAmt; // 할인 대상 선납금액
    private String noDscMm; // 미할인 대상 선납개월
    private String noDscAmt; // 미할인 대상 선납금액
    private String prmFromYm; // 선납기간from
    private String prmToYm; // 선납기간to
    private String mmAmt; // 월납입금액
    private String mmDscAmt; // 한달 할인금액
    private String atam; // 선수금액
    private String ucAmt; // 미수금액
    private String dpAmt; // 납입금액

}



