package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbGiroDepositErrorSaveDvo {
    String itgDpNo; //--통합입금번호
    String cntrNo; //--계약번호
    String cntrSn;
    String dpErrProcsCn;
    String cstKnm; //--  a. 고객명 
    String pyAmt; //--납입금액  
    String rveDt; //--입금일
    String fntDt; //--실적일
    String procsErrTpCd; //오류처리
    String cntr;
}
