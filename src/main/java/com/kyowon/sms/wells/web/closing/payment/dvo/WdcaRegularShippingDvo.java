package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaRegularShippingDvo {
    private String cntrNo; /* 계약번호 */
    private int cntrSn; /* 계약일련번호 */
    private int resChramBorAmt; /* 잔여요금위약금액 */
    private int dscBorAmt; /* 할인위약금액 */
}
