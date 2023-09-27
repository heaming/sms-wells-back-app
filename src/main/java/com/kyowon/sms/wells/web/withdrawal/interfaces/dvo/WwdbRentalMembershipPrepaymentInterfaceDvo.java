package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WwdbRentalMembershipPrepaymentInterfaceDvo {

    private String nmn; // 차월
    private BigDecimal totPrmMcnt; // 총선납개월
    private BigDecimal dscObjPrmMcnt; // 할인 대상 선납개월
    private BigDecimal prmDscr; // 선납시 할인율
    private BigDecimal dscObjPrmAmt; // 할인 대상 선납금액
    private BigDecimal nonDscObjPrmMcnt; // 미할인 대상 선납개월
    private BigDecimal nonDscObjPrmAmt; // 미할인 대상 선납금액
    private String prmPtrmStrtdt; // 선납기간from
    private String prmPtrmEnddt; // 선납기간to
    private BigDecimal fmnAmt; // 월납입금액
    private BigDecimal fmnDscAmt; // 한달 할인금액
    private BigDecimal prpdAmt; // 선수금액
    private BigDecimal ucAmt; // 미수금액
    private BigDecimal pyAmt; // 납입금액

}
