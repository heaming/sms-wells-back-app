package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbBillDepositMgtSubDvo {
    String itgDpNo; /* 통합입금번호 */
    String cntrNo;
    String cntrSn;
    String billDpAmt; /* 금액 */
}
