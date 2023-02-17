package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbGiroOcrForwardingMgtDvo {
    String rowState;
    String wkDt;
    String wkSn; // --작업일련번호
    String cntrNo;
    String cntrSn;
    String cstFnm; //--고객명
    String slDt; // --매출일자
    String strtGiroTn; //--시작
    String endGiroTn; //--종료
    String thm0Amt; //--0차월
    String istmRentalAmt1; //--1청구 렌탈료
    String stplNmnAmt; //--약정차월
    String istmRentalAmt2; //--2청구 렌탈료
    String exnNmnAmt; //--만료차월
    String ltpayYn; //--후납여부
}
