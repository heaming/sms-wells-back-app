package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WwdbNotPaidMakeAPaymentContractDvo {

    String cntrNo;/*계약번호*/
    String cntrSn;/*계약일련번호*/
    String cntrCstNo; /*고객번호*/
    String sellTpCd; /*판매유형*/
    String cntrCnfmDtm; /*확정일시*/
    String basePdCd; /* 상품코드 */

}
