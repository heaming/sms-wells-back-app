package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WcsbNewReceiptDvo {
    private String bsdt; /* 기준일자 */
    private String ogTpCd; /* 조직유형코드 */
    private Long sellBaseQty; /* 판매기준수량 */
    private Long rngBaseQty; /* 범위기준수량 */
    private String aplcBaseMoCn; /* 신청기준메모내용 */

    private String days;
    private String dupliYn; /* 중복체크용 생성변수 */
    private String orglFnlMdfcDtm;
}
