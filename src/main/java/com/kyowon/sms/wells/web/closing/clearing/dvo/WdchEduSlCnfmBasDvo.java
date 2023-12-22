package com.kyowon.sms.wells.web.closing.clearing.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * EDU 매출확정
 */
@Getter
@Setter
@ToString
public class WdchEduSlCnfmBasDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String sellTpCd; /*판매유형코드*/
    private String sellTpDtlCd; /*판매유형상세코드*/
    private String sellInflwChnlDtlCd; /*판매유입채널상세코드*/
    private String sapPdDvCd; /*SAP상품구분코드*/
    private String dgCstId; /*대표고객ID*/
    private String cstNo; /*고객번호*/
    private String pdCd; /*상품코드*/
    private BigDecimal slAmt; /*매출금액*/
    private BigDecimal slCanAmt; /*매출취소금액*/
    private String slRcogDt; /*매출인식일자*/
    private String sapSlpno; /*SAP전표번호*/
    private BigDecimal slAmtSum; /*매출총액 총합*/
    private BigDecimal thmOcDlqAddAmt; /*당월발생연체가산금액*/
    private BigDecimal ocBorAmt; /*추가미수발생금액*/
    private String baseYm; /*기준년월*/
    private int slBndAlrpySn; /*채권반제일련번호*/
}
