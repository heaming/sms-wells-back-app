package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WwdbNotPaidMakeAPaymentOgPrtnrDvo {
    String prtnrNo;//파트너번호
    String ogTpCd;//조직유형코드
    String ogId;//조직ID
}
