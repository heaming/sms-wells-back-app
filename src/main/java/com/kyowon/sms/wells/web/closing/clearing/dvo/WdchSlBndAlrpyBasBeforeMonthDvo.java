package com.kyowon.sms.wells.web.closing.clearing.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * EDU 매출채권반제기본(이전월 데이터)
 */
@Getter
@Setter
@ToString
public class WdchSlBndAlrpyBasBeforeMonthDvo {
    private BigDecimal dpBlam; /*입금잔액*/
    private BigDecimal dpAcuAmt; /*입금누적금액*/
    private BigDecimal totSlAmt; /*총매출금액*/
    private BigDecimal ucAmt; /* 미수금액*/
}
