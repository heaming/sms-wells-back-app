package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsniChkOverdueCustDvo {
    private String cntrCstNo = "";
    private int dlqMcn;
    private String rsltCd = ""; /* 결과코드        */
    private String rsltMsg = ""; /* 결과메세지      */
    private String eotDlqAmt = ""; /*총연체금액*/
    private String overdueAmouOrd = ""; /* 주문번호별연체금액  */

}
