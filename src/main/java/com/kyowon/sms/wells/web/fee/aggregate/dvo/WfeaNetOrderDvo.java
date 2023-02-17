package com.kyowon.sms.wells.web.fee.aggregate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeaNetOrderDvo {
    String Lcnam2; /* 수당내역 */
    String Lcgun0; /* 구분０ */
    String Lcpseq; /* 출력순서 */
    String Lcamt1a; /* 금액  */
    String Lcamt1b; /* 추가지급*/
    String Lcamt1c; /* 수당공제*/
}
