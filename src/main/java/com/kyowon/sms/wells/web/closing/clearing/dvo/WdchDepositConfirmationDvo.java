package com.kyowon.sms.wells.web.closing.clearing.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * EDU 영업선수금기본
 */
@Getter
@Setter
@ToString
public class WdchDepositConfirmationDvo {
    private String sellTpCd; /*판매유형코드*/
    private String sellTpDtlCd; /*판매유형상세코드*/
    private String rveDvCd; /*수납구분코드*/
    private BigDecimal amt; /*금액(인정실적금액)*/
}
