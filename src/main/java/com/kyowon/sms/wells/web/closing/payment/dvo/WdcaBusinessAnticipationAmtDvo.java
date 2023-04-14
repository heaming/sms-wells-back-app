package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// TODO: 테이블 미정의로 추후 재작업 필요

@Getter
@Setter
@ToString
public class WdcaBusinessAnticipationAmtDvo {
    private String rveNo; /* 수납번호 */
    private String rveSn; /* 수납일련번호 */
    private String dpClDt; /* 입금마감일자 */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */

    private String kwGrpCoCd; /* 회사코드 */
    private String rveCd;
    private String ichrPrtnrNo; /* 담당파트너번호 */
    private String rveAmt; /* 수납금액 */
    private String cstNo;

    private String bnkCd; /* 은행코드 */
    private String cdcoCd; /* 카드사코드 */
    private String etcAtamNo; /* 기타선수금번호 */
    private String etcAtamSn; /* 기타선수금일련번호 */

    private String bznsAtamProcsCd;
    private String bznsAtamProcsAmt;

    private String rveTpCd; /* 수납유형코드 */
    private String dpKndCd; /* 입금종류코드 */
    private String dpTpCd; /* 입금유형코드 */
    private String pdHcsfId; /* 상품대분류ID */
    private String pdMclsfId; /* 상품중분류ID */
    private String pdLclsfId; /* 상품소분류ID */
    private String sellTpCd; /* 판매유형코드 */

    private String bznsAtamBlam;
    private String sapDpTpCd; /* SAP입금유형코드 */
    private String sapPdDvCd; /* SAP상품구분코드 */
    private String sapPdAtcCd; /* SAP상품항목코드 */
    private String orglFnlMdfcDtm; /*Dvo에 동시성처리용 컬럼 정의*/
}
