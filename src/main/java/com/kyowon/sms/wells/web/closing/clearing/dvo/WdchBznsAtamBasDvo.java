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
public class WdchBznsAtamBasDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String sellTpCd; /*판매유형코드*/
    private String sellTpDtlCd; /*판매유형상세코드*/
    private String sapPdDvCd; /*SAP상품구분코드*/
    private String dgCstId; /*대표고객ID*/
    private String cstNo; /*고객번호*/
    private String pdCd; /*상품코드*/
    private String rveNo; /*수납번호*/
    private int rveSn; /*수납일련번호*/
    private String dpClDt; /*입금마감일자*/
    private BigDecimal rveAmt; /*수납금액*/
    private BigDecimal bznsAtamBlam; /*영업선수금잔액*/
    private String rveDvCd; /*수납구분코드*/
    private String sapSlpno; /*SAP전표번호*/
    private String baseYm; /*기준년월*/
    private int slBndAlrpySn; /*채권반제일련번호*/
}
