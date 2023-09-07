package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaSellTypeDvo {
    private String cntrNo; /* 계약번호 */
    private int cntrSn; /* 계약일련번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String sellTpDtlCd; /* 판매유형상세코드 */
    private String cntrDtlStatCd; /* 계약상세상태코드 */
}
