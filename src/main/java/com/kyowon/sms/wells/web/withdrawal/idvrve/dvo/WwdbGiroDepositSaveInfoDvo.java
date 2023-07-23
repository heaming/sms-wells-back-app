package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbGiroDepositSaveInfoDvo {
    String itgDpNo;
    String giroNo;
    String giroDpMtrDvCd;
    String dpSn; //-- 일련번호
    String fntDt;// --이체일자
    String rveDt; //--수납일자
    String bnkCd; //--은행코드
    String giroInqno; //--지로조회번호
    String cntrNo; //--계약번호
    String cntrSn; //--계약일련번호
    String pyTn; //--회차
    String pyYm; //--회차
    String pyAmt;// --납입금액
    String giroFeeDvCd; //--지로수수료
    String giroRveDvCd; //--수납구분
    String dtaDlYn;
    String itgDpCanYn;
    String incmdcYn;
    String procsErrTpCd; //오류처리

    String ogTpCd; /*조직유형코드*/
    String prtnrNo; /*파트너번호*/
    String ogId;
    String dpAmt; /*입금금액*/
    String dpCprcnfAmt; /*입금대사금액*/
}
