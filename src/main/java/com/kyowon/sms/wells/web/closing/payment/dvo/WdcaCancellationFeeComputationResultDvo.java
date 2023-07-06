package com.kyowon.sms.wells.web.closing.payment.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaCancellationFeeComputationResultDvo {
    private int resRtlfeBorAmt; /* 잔여렌탈료위약금액 */
    private int rgstCostDscBorAmt; /* 등록비할인위약금액 */
    private int rentalDscBorAmt; /* 렌탈할인위약금액 */
    private int rstlBorAmt; /* 재약정위약금액 */
    private int csmbCostBorAmt; /* 소모품비위약금액 */
    private int pBorAmt; /* 포인트위약금액 */
    private int reqdCsBorAmt; /* 철거비용위약금액 */
    private int lsRntf; /* 분실손료 */
    private int borAmt; /* 위약금액 */
}
