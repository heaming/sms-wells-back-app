package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaRentalFeeDiscountRstlCcamDvo {
    private int rentalDscBorAmt; /* 렌탈료할인위약금 */
    private int rstlBorAmt; /* 재약정위약금 */
}
