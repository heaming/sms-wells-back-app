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
 * @since 2023-05-24
 */
@Getter
@Setter
@ToString
public class WdcaDepositRefundProcessingAmountDvo {
    private String cntrNo;
    private int cntrSn;
    private int thmDlqDpSumAmt; /*당월연체입금합계금액*/
    private int thmDlqAddDpSumAmt; /*당월연체가산입금합계금액*/
    private int rsgBorDpAmt; /*해지위약입금금액*/
    private int thmDlqRfndSumAmt; /*당월연체환불합계금액*/
    private int thmDlqAddRfndSumAmt; /*당월연체가산환불합계금액*/
    private int eotDlqAmt; /*기말연체금액*/
    private int eotDlqAddAmt; /*기말연체가산금액*/
}
