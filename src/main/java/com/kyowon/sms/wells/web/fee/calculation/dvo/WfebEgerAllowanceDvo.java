package com.kyowon.sms.wells.web.fee.calculation.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfebEgerAllowanceDvo {

    String perfYm; /*실적년월*/
    String ogId; /*조직ID*/
    String ogCd; /*조직코드*/
    String rsbDvCd; /*직책구분코드*/
    String prtnrNo; /*파트너번호*/
    String feeCd; /*수수료코드*/
    int feeAmt; /*수수료금액*/
    String baseYm; /*기준년월*/
    String type; /*유형*/
    String confirm; /*확정*/
    String hdof; /*본사*/

}
