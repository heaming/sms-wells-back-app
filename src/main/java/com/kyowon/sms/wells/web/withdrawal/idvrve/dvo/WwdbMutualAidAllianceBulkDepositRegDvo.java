package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbMutualAidAllianceBulkDepositRegDvo {
    String lifSpptYm; /* 지원년월 */
    String welsCntrNo; /*웰스계약번호*/
    String welsCntrSn; /*웰스계약일련번호*/
    String lifAlncPdCd; /*상품*/
    String lifAlncPdNm; /*상품명*/
    String lifSpptAmt; /*지원금액*/
    String lifCntrNo; /*상조계약번호*/
    String lifCntrSn; /*상조계약번호*/
    String lifAlncDvCd; /*제휴코드*/
}
