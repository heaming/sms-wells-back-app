package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaCancellationFeeComputationDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String rqdt; /* 요청일자 */
    private String duedt; /* 취소일자 */
    private String canTpCd; /* 취소유형코드 */
    private String lsRntf; /* 분실손료 */
    private String pBorAmt; /* 포인트위약금액 */
    private String cnfmYm; /* 확정여부 */
    private String fgptPdCd; /*사은품상품코드*/
}
