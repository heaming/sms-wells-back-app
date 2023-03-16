package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WwdbMutualAidAllianceBulkDepositRegDvo {
    Object sn; /*순번*/
    Object lifSpptYm; /* 지원년월 */
    String test1; /* 고객성명 */
    Object welsCntrNo; /*웰스계약번호*/
    Object welsCntrSn; /*웰스계약일련번호*/
    Object lifAlncPdCd; /*상품*/
    Object lifAlncPdNm; /*상품명*/
    Object test2; /* 행사일자 */
    Object test3; /* 납부 */
    Object test4; /* 누계 */
    Object test5; /*부금금액*/
    Object test6; /*부금누계*/
    Object test7; /*계약일자*/
    Object test8; /*해지일자*/
    Object lifSpptAmt; /*지원금액*/
    Object lifSpptAggAmt; /*라이프지원누계금액*/
    Object lifRepAmt; /*라이프환수금액*/
    Object lifCntrNo; /*상조계약번호*/
    Object lifCntrSn; /*상조계약번호*/
    String lifAlncDvCd; /*제휴코드*/
}
