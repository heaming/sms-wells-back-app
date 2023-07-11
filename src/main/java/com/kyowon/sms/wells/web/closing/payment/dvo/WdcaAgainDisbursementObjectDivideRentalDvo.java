package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 재지급데이터 생성 DVO
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-07-03
 */
@Getter
@Setter
@ToString
public class WdcaAgainDisbursementObjectDivideRentalDvo {
    private int fnlAmt;
    private int ackmtPerfAmt;
    private String istDt;
    private String canDt;
    private int rentalTn;
    private int dpAcuAmt;
    private int ucAmt;
}
