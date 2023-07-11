package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 영업선수금기본 Table DVO
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-07-03
 */
@Getter
@Setter
@ToString
public class WdcaBznsAtamBasDvo {
    private String cntrNo;
    private String cntrSn;
    private int bznsAtamBlam; /*영업선수금잔액*/
    private int bznsAtamProcsAmt; /*영업선수금처리금액*/
    private String bznsAtamProcsCd; /*영업선수금처리코드*/
}
