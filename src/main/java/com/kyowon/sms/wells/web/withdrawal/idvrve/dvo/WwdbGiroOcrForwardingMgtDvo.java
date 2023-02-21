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
    String istmMcn;
    String istmAmt;
    String istmDscAmt;
    String pyAmt;
    String stplNmnAmt;
    String exnNmnAmt; //--만료차월
    String ltpayYn; //--후납여부
    String giroRglrDvCd; //--후납여부

}
