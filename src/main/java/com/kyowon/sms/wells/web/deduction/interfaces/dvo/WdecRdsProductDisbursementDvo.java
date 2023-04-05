package com.kyowon.sms.wells.web.deduction.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdecRdsProductDisbursementDvo {

    private String rdsRvUseId; /*RDS적립사용ID*/
    private String ogTpCd; /*조직유형코드*/
    private String coCd; /*회사코드*/
    private String rdsDsbRefId; /*RDS지급참조ID*/
    private String akOgTpCd; /*요청조직유형코드*/
    private String prtnrNo; /*파트너번호*/
    private String blngYm; /*귀속년월*/
    private String rdsRvUseDt; /*RDS적립사용일자*/
    private Number rdsAmt; /*RDS금액*/
    private String rdsEarnrDvCd; /*RDS소득자구분코드*/
    private String rdsRvUseTpCd; /*RDS적립사용유형코드*/
    private String rdsSmryCd; /*RDS적요코드*/
    private String rdsSmryCn; /*RDS적요내용*/
}
