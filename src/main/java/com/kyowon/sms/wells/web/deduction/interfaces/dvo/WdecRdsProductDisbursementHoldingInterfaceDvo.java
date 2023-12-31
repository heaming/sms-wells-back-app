package com.kyowon.sms.wells.web.deduction.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdecRdsProductDisbursementHoldingInterfaceDvo {

    private String rdsDsbDuedt; /*RDS지급예정일자*/
    private String prtnrNO; /*파트너번호*/
    private String ogTpCdAryVal; /*조직유형코드(값)*/
    private String wkPrtcDtmVal; /*작업일시*/
}
