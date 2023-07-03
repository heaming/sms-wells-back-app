package com.kyowon.sms.wells.web.closing.sales.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 매출확정생성 서비스 대손금액 / 대손처리일자 DVO
 * </pre>
 *
 * @author WOO SEUNG MIN
 * @since 2023-06-22
 */
@Getter
@Setter
@ToString
public class WdcbSalesConfirmDfaAmtDvo {
    private int dfaAmt;
    private String dfaPrcsdt;
}
