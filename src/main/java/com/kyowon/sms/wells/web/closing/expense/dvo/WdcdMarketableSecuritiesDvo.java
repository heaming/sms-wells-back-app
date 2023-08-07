package com.kyowon.sms.wells.web.closing.expense.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdcdMarketableSecuritiesDvo {

    private String opcsAdjNo; // 운영비정산번호
    private String dstOjOgTpCd;
    private String ogTpCd;
    private String ogId;         /*정산조직ID*/
    private String dstOjPrtnrNo;
    private String dstOjpsNm;
    private String dstOjpsPerfAmt;
    private String dstAmt;
    private String dstWhtx;
    private String erntx;
    private String rsdntx;
    private String pdstOpt; /*배분옵션 선택 값*/
    private String cardUseAmt; // 카드사용금액
    private String pstnDvCd;
    private String adjOgId;
    private String baseYm;
    private String adjPrtnrNo;
    private String opcsCardUseIzId;
    private String deleted;
}
