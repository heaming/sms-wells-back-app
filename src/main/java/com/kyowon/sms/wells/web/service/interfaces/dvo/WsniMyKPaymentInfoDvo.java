package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsniMyKPaymentInfoDvo {

    private String cntrNo = "";
    private int cntrSn;
    private String rsltCd = ""; /* 결과코드        */
    private String rsltMsg = ""; /* 결과메세지      */
    private String askAmt = ""; /*실 청구금액 - 이체일이전=전월연체금액-당월입금액+당월환불액 / 이체일 이후=당월연체금액*/
    private String dpTpCd = ""; /* 입금유형코드(납부방법) 0102 0203 */
    private String dpTpNm = ""; /* 입금유형명(납부방법) */
    private String bankCardCoNm = ""; /*은행_카드사명*/
    private String crcdnoEncr = "";
    private String acnoEncr = "";
    private String aftnOwrKnm = ""; /* 계좌정보-예금／카드주명 (ACCNM) */
    private String mpyBsdt = "";
}
