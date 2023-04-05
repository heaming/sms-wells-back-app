package com.kyowon.sms.wells.web.deduction.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdecRdsProductDisbursementHoldingDvo {

    private String rdsDsbDuedt; /*RDS지급예정일자*/
    private String prtnrNO; /*파트너번호*/
    private String ogTpCdVal; /*조직유형코드(값)*/
}
