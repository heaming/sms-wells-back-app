package com.kyowon.sms.wells.web.deduction.redf.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdeaSoleDistributorMgtDvo {
    String redfAdsbOcYm; /* 되물림재지급발생년월 */
    String ogTpCd; /* 조직유형코드 */
    String prtnrNo; /* 파트너번호 */
    String feeRedfAdsbId; /* 수수료되물림재지급id */
    String redfDdtnBlamCrdovrAmt;/* 되물림공제잔액이월금액 */
    String redfOcAmt; /* 되물림발생금액 */
    String redfDdctam; /* 되물림공제금액 */
    String redfDdtnBlam; /* 되물림공제잔액 */
    String dtaDlYn; /* 데이터삭제여부 */
}
