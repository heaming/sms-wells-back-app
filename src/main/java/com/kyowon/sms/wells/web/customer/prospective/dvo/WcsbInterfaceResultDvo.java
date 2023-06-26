package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WcsbInterfaceResultDvo {
    private String check; /* 결과상태 */
    private String rsCd; /* 결과코드 */
    private String rsMsg; /* 결과메시지 */
    private String orsv; /* 결과메시지 */
}
