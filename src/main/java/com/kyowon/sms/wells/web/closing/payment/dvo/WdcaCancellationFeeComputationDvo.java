package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaCancellationFeeComputationDvo {
    private String cntrNo; /* 계약번호 */
    private int cntrSn; /* 계약일련번호 */
    private String rqdt; /* 요청일자 */
    private String duedt; /* 취소일자 */
    private String canTpCd; /* 취소유형코드 */
    private int lsRntf; /* 분실손료 */
    private int pBorAmt; /* 포인트위약금액 */
    private String cnfmYm; /* 확정여부 */
    private String fgptPdCd; /*사은품상품코드*/
    private int rtngdQty; /*반품수량*/
    private String exmptDvCd; /*면책구분코드*/
    private int borAmt; /*위약금액*/
}
