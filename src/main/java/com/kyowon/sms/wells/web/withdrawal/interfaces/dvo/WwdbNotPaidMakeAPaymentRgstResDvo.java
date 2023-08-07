package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WwdbNotPaidMakeAPaymentRgstResDvo {

    String errorCode; /*에러인 경우 N, 정상인 경우 Y*/
    String resultCnt; // 에러인 경우 기존 셋팅된 MSG, 정상인 경우 '정상 처리 되었습니다.'
}
