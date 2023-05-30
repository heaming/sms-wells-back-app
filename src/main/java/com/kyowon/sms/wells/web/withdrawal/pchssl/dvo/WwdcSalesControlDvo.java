package com.kyowon.sms.wells.web.withdrawal.pchssl.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdcSalesControlDvo {
    private String cntrNo;
    private String cntrSn;
    private String cntrDtlNo;
    private String cstKnm;
    private String slCtrStrtYm;
    private String slCtrEndYm;
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
    private String slCtrPrcsdt; /* 등록일자 */
    private String usrNm;
    private String fnlMdfcUsrId;
}
