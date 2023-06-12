package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbDepositComparisonComfirmationDvo {
    private String itgDpNo; /*통합입금번호*/
    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String rveCoCd; /*수납회사코드*/
    private String rveCd; /*수납코드*/
    private String ogTpCd; /*조직유형코드*/
    private String prtnrNo; /*파트너번호*/
    private String dpDvCd; /*입금구분코드*/
    private String dpMesCd; /*입금수단코드*/
    private String dpTpCd; /*입금유형코드*/
    private String rveDvCd; /*수납구분코드*/
    private String pdClsfId; /*상품분류ID*/
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String pdCd; /*상품코드*/
    private String incmdcYn; /*소득공제여부*/
    private String sellTpCd; /*판매유형코드*/
    private String sellTpDtlCd; /*판매유형상세코드*/
    private String dpAmt; /*입금금액*/
    private String dpCprcnfNo; /*입금대사번호*/
    private String rveAkNo; /*수납요청번호*/
    private String rveOjDrmNo1;
    private String rveOjDrmNo2;

}
