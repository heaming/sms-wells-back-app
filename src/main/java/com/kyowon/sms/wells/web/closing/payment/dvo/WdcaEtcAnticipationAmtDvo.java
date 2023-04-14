package com.kyowon.sms.wells.web.closing.payment.dvo;

// TODO: 테이블 미정의로 추후 재작업 필요

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaEtcAnticipationAmtDvo {
    private String etcAtamNo;
    private String etcAtamOcDt;
    private String itgDpNo;
    private String etcAtamAmt;
    private String etcAtamTpCd;
    private String etcAtamBlam;
    private String cntrNo;
    private String cntrSn;
    private String kwGrpCoCd;
    private String rveCd;
    private String ichrPrtnrNo;
    private String cstNo;

    private String pdCd;
    private String pdHclsfId;
    private String pdMclsfId;
    private String pdLclsfId;
    private String pdDclsfId;

    private String sapDpTpCd; /* SAP입금유형코드 */
    private String sapPdDvCd; /* SAP상품구분코드 */
    private String sapPdAtcCd; /* SAP상품항목코드 */
    private String orglFnlMdfcDtm; /*Dvo에 동시성처리용 컬럼 정의*/

    private int etcAtamSn;
    private String etcAtamProcsCd;
    private String etcAtamPrcsdt;
    private String etcAtamProcsAmt;

    private String rveNo; /* 수납번호 */
    private String rveSn; /* 수납일련번호 */

    private String rfndRcpNo;
    private String rveDt;
    private String bilTn;

    private String procsDvCd;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String dpMesCd;
    private String dpTpCd; /* 입금유형코드 */

    private String cdcoCd; /* 카드사코드 */
    private String cardAprno;
    private String crcdnoEncr;
    private String bnkCd;
    private String dprNm;
    private String vacNo;
    private String vncoDvCd;
}
