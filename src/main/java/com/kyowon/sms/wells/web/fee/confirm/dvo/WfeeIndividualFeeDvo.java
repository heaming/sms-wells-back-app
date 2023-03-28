package com.kyowon.sms.wells.web.fee.confirm.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeeIndividualFeeDvo {

    String perfYm; /*실적년월*/
    String ogTp; /*조직유형*/
    String rsbTp; /*직책유형*/
    String ogLevl1; /*조직레벨1*/
    String ogLevl2; /*조직레벨2*/
    String ogLevl3; /*조직레벨3*/
    String prtnrNo; /*파트너번호*/
    String feeDsbYn; /*수수료지급여부*/
    String rsbDvCd; /*직책구분코드*/
    String hirFomCd; /*고용형태코드*/

}
