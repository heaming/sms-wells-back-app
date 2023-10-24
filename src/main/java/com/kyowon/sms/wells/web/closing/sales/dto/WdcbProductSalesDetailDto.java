package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbProductSalesDetailDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseDtmnFrom, /*매출시작일자*/
        @NotBlank
        String baseDtmnTo, /*매출종료일자*/
        String sellTpCd, /*판매유형코드*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellChnlDtl, /*판매채널*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cstNo, /*고객번호*/
        String sapSlpno, /*SAP전표번호*/
        String sapPdDvCd /*SAP상품구분코드*/
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchSingleRes")
    public record SearchSingleRes(
        String sellTpCd, /*판매유형코드*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String sellInflwChnlDtlCd, /*판매유입채널상세코드*/
        String slRcogDt, /*매출인식일자*/
        String cntrDtlNo, /*계약상세번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cstKnm, /*고객한글명*/
        String sapPdDvCd, /*SAP상품구분코드*/
        String sellQty, /*판매수량*/
        String sellAmt, /*판매금액*/
        String sellSplAmt, /*판매공급금액*/
        String sellAmtVat, /*판매금액부가가치세*/
        String pvdaAmt, /*현재가치할인차금금액*/
        String chQty, /*변경수량*/
        String slChAmt, /*매출변경금액*/
        String chSplAmt, /*공급가액-매출변경*/
        String chVat, /*변경후부가세*/
        String chPvdaAmt, /*현할차-매출변경*/
        String canQty, /*판매수량-매출취소*/
        String slCanAmt, /*매출금액-매출취소*/
        String canSplAmt, /*공급가액-매출취소*/
        String canVat, /*부가세-매출취소*/
        String canPvdaAmt, /*현할차-매출취소*/
        String totQty, /*판매수량-매출합계*/
        String totAmt, /*매출금액-매출합계*/
        String totSplAmt, /*공급가액-매출합계*/
        String totVat, /*부가세-매출합계*/
        String totPvdaAmt /*현할차-매출합계*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchRentalRes")
    public record SearchRentalRes(
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String sellInflwChnlDtlCd, /*판매유입채널상세*/
        String slRcogDt, /*매출인식일자*/
        String cntrDtlNo, /*계약번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cstKnm, /*고객명*/
        String sapPdDvCd, /*SAP상품구분코드명*/
        String rentalRgstCost, /*매출금액-등록비*/
        String rentalRgstCostSpl, /*공급가액-등록비*/
        String rentalRgstCostVat, /*부가세-등록비*/
        String nomSlAmt, /*매출금액-렌탈료*/
        String splAmt, /*공급가액-렌탈료*/
        String vat, /*부가세-렌탈료*/
        String totSlAmt, /*매출금액-매출합계*/
        String totSplAmt, /*공급가액-매출합계*/
        String totVat /*부가세-매출합계*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdcbProductSalesDetailDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String sellInflwChnlDtlCd, /*판매유입채널상세*/
        String slRcogDt, /*매출인식일자*/
        String cntrDtlNo, /*계약번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cstKnm, /*고객명*/
        String sapPdDvCd, /*SAP상품구분코드*/
        String sellAmt, /*매출금액-회비*/
        String sellSplAmt, /*공급가액-회비*/
        String sellAmtVat, /*부가세-회비*/
        String filSellAmt, /*매출금액-필터*/
        String filSellSplAmt, /*공급가액-필터*/
        String filSellAmtVat, /*부가세-필터*/
        String totSellAmt, /*매출금액-매출합계*/
        String totSellSplAmt, /*공급가액-매출합계*/
        String totSellAmtVat /*부가세-매출합계*/
    ) {}
}
