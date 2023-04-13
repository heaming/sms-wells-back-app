package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbServiceRefundDvo {
    private String rfndDdtnAmt; // 공제금액
    private String rfndAkAmt; // 실지급액
    private String rfndDsbDvCd; // 지급구분
    private String cardRfndFnitCd; // 카드구분
    private String cardRfndCrcdonrNm; // 카드주명
    private String cardRfndFee; // 수수료액
    private String cardRfndCrcdnoEncr; // 카드번호
    private String cardRfndCrdcdAprno; // 승인번호
    //    private String tmp3; // 유효기간(년월)(RRRD.환불카드유효기간필드추가필요)
    private String cardRfndCrdcdIstmMcn; // 할부(개월)
    private String cshRfndFnitCd; // 지급은행
    private String cshRfndAcnoEncr; // 계좌번호
    private String rfndRsonCn; // 환불사유
    private String cntrNoSn; // 계약상세번호 

}
