package com.kyowon.sms.wells.web.withdrawal.pchssl.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 매출 조정 관리 DVO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-05-25
 */
@Getter
@Setter
public class WwdcSalesControlDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cntrDtlNo; /* 계약상세번호 */
    private String cstKnm; /* 고객명 */
    private String slCtrStrtYm; /* 매출일자 */
    private String slCtrEndYm; /* 종료년월 - 만료일자 */
    private String slCtrSellTpCd; /* 매출조정판매유형코드 */
    private String slCtrMtrDvCd; /* 매출조정자료유형코드 */
    private String pdCd; /* 제품코드 */
    private String pdNm; /* 제품명 */
    private String slCtrDvCd; /* 매출조정구분코드 */
    private String slCtrTpCd; /* 매출조정유형코드 */
    private String slCtrMtrTpCd; /* 매출조정할인유형코드 */
    private String slCtrDscTpCd; /* 할인 */
    private String canAfOjYn; /* 취소후적용 - default N */
    private String slCtrAmt; /* 매출조정금액 */
    private String slCtrWoExmpAmt; /* 매출전액면제금액 */
    private String slCtrPtrmExmpAmt; /* 매출조정기간면제금액 */
    private String slCtrRmkCn; /* 조정비고내용 - 사유 */
    private String slCtrProcsYn; /* 매출조정처리여부 */
    private String slCtrPrcsdt; /* 등록일자 */
    private String usrNm;
    private String fnlMdfcUsrId;

    private String canDt; /* 취소일자 */
    private String apyY; /* 적용년도 */
    private String jan; /* 1월 : Y/N */
    private String feb; /* 2월 : Y/N */
    private String jul; /* 7월 : Y/N */
    private String aug; /* 8월 : Y/N */
    private String sellTpDtlCd; /* 매출판매유형상세코드 */

    /*excel 업로드용*/

    private String controlYear; /*조정년도*/
    private String controlMonth; /*조정월*/
    private String orderType; /*주문유형*/
    private String controlAmount; /*조정금액*/
    private String controlDivide; /*조정구분*/
    private String controlType;  /*조정유형*/
    private String controlReason; /*조정사유*/
}
