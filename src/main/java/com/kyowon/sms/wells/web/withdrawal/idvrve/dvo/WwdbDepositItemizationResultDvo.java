package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbDepositItemizationResultDvo {
    String dpMesCd; /*입금수단코드*/
    String dpMesCdNm; /*입금수단코드명*/
    String dpTpCd; /*입금유형코드*/
    String dpTpCdNm; /*입금유형코드명*/
    String rveDvCd; /*수납구분코드*/
    String rveDvCdNm; /*수납구분코드명*/
    String rveCd; /*수납코드*/
    String rveCdNm; /*수납코드명*/
    String dpDtm; /*입금일자*/
    String dpAmt; /*입금금액*/
    String cntrNo; /*계약번호*/
    String cntrSn; /*계약일련번호*/
    String pdCd; /*상품코드*/
    String pdNm; /*상품명*/
}
