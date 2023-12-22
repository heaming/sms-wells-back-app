package com.kyowon.sms.wells.web.closing.clearing.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * EDU 매출채권반제기본
 */
@Getter
@Setter
@ToString
public class WdchSlBndAlrpyBasDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String baseYm; /*기준년월*/
    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String sellTpCd; /*판매유형코드*/
    private String sellTpDtlCd; /*판매유형상세코드*/
    private String sellInflwChnlDtlCd; /*판매유입채널상세코드*/
    private String sapPdDvCd; /*SAP상품구분코드*/
    private String dgCstId; /*대표고객ID*/
    private String cstNo; /*고객번호*/
    private String pdCd; /*상품코드*/
    private String slBndAlrpyDt; /*반제일자*/
    private BigDecimal slBndAlrpyAmt; /*(매출채권)반제금액*/
    private BigDecimal bndAlrpyAggAmt; /*채권반제누계금액*/
    private String rveNo; /*수납번호*/
    private int rveSn; /*수납일련번호*/
    private String dpClDt; /*입금마감일자*/
    private BigDecimal rveAmt; /*수납금액*/
    private String rveDvCd; /*수납구분코드*/
    private String sapDpSlpno; /*SAP입금번호*/
    private BigDecimal dpAcuAmt; /*입금누적금액*/
    private BigDecimal btdAtam; /*기초선수금액*/
    private BigDecimal dpBlam; /*입금잔액*/
    private BigDecimal atamRplcProcsAmt; /*선수금대체처리금액*/
    private BigDecimal atamCvAmt; /*영업선수금액*/
    private String slRcogDt; /*매출인식일자*/
    private BigDecimal slBndOcAmt; /*매출채권발생금액*/
    private BigDecimal slCanAmt; /*매출취소금액*/
    private BigDecimal spmtUcOcAmt; /*추가미수발생금액*/
    private String sapSlSlpno; /*SAP매출전표번호*/
    private BigDecimal totSlAmt; /*총매출금액*/
    private BigDecimal btdUcAmt; /*기초미수금액*/
    private BigDecimal ucAmt; /*미수금액*/
}
