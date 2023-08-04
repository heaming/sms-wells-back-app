package com.kyowon.sms.wells.web.closing.performance.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdccSalesPerformDvo {
    private String cntrNo; //계약번호
    private String cntrSn; //계약일련번호
    private String slClYm; //매출년월
    private String slStpYn; //매출중지
    private String rentalTn; //렌탈차월
    private String slCtrDvCd; //매출조정구분코드
    private String prmMcn; //선납개월
    private String thmSlSumAmt; //매출합계
    private String borAmt; //위약금액
    private String lsRntf; //분실손료
    private String sumBorAmt; //위약금
    private String dpAmt; //입금금액
    private String eotAtam; //선수금액
    private String thmUcBlam; //미수금액
    private String thmOcDlqAmt; //연체금액
    private String dlqMcn; //연체개월수
    private String btdDlqAddAmt; //가산금기초
    private String thmOcDlqAddAmt; //가산금발생
    private String thmCtrDlqAddAmt; //가산금공제
    private String thmDlqAddDpSumAmt; //가산금입금
    private String thmDlqAddRfndSumAmt; //가산금환불
    private String eotDlqAddAmt; //가산금기말
    private String slStpAmt; //매출중지금액
}
