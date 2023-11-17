package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 입금내역조회(모바일) DVO
 * </pre>
 *
 * @author jaehaYeon
 * @since 2023-06-07
 */
@Getter
@Setter
public class WwdbDepositItemizationDvo {
    String cstNo; /*고객번호*/
    String startDt; /*조회시작일*/
    String finishDt; /*조회종료일*/
}
