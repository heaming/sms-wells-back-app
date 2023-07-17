package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WwdbNotPaidMakeAPaymentRgstReqDvo {
    String cntrNo;//계약번호
    String cntrSn;//계약일련번호
    String dpDvCd;//입금구분코드
    String sellTpCd;//판매유형코드
    String prtnrNo;//파트너번호
    String dpTpCd;//입금유형코드
    long dpAmt;//입금금액
}
