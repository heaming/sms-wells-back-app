package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 연체 입금/환불 반영 서비스 DVO
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-05-22
 */
@Getter
@Setter
@ToString
public class WdcaDelinquentDepositRefundDvo {
    private String cntrNo;
    private int cntrSn;
    private String kwGrpCoCd;
    private String rveNo;
    private int rveSn;
    private String dpDvCd;
    private String dpMesCd;
    private String dpTpCd;
    private String rveDvCd;
    private String rveCd;
    private String rveDt;
    private String perfDt;
    private int rveAmt;

    private int thmDlqDpSumAmt; /*당월연체입금합계금액*/
    private int thmDlqAddDpSumAmt; /*당월연체가산입금합계금액*/
    private int thmDlqRfndSumAmt; /*당월연체환불합계금액*/
    private int thmDlqAddRfndSumAmt; /*당월연체가산환불합계금액*/
}
