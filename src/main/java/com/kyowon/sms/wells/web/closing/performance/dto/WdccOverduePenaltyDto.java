package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdccOverduePenaltyDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 CodeReq
    @ApiModel(value = "WdccOverdueQenaltyDto-FindCodeRes")
    public record FindCodeRes(
        String sapPdDvCd, /*SAP상품구분코드*/
        String sapPdDvNm /*SAP상품구분명*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 searchReq
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String slClYm, /*매출마감년월*/
        String agrgDv, /*집계구분*/
        String sapPdDvCd, /*SAP상품구분코드*/
        String sellTpCd, /*판매유형코드*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellChnlDtl, /*판매채널*/
        String cntrNo, /*계약번호*/
        String cntrSn /*계약일련번호*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금(포인트/집계) ThirdGrid
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchPointAggregateRes")
    public record SearchPointAggregateRes(
        String slClYm, /*매출마감년월*/
        String sellTpCd, /*판매유형코드*/
        String sellTpCdNm, /*판매유형명*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellTpDtlCdNm, /*판매유형상세명*/
        String sapPdDvCd, /*SAP상품구분코드*/
        String sapPdAtcNm, /*SAP상품코드명*/
        String mlgBtdPrpdAmt, /*기초마일리지금액*/
        String mlgDpAmt, /*마일리지입금금액*/
        String mlgRfndAmt, /*마일리지환불금액*/
        String mlgTotAmt, /*합계*/
        String mlgSlAmt, /*마일리지매출금액*/
        String mlgEotPrpdAmt /*마일리지기말선수금액*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금(포인트/주문별) FourthGrid
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchPointOrderRes")
    public record SearchPointOrderRes(
        String slClYm, /*실적년월*/
        String sellTpCd, /*판매유형코드*/
        String sellTpCdNm, /*판매유형코드명*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellTpDtlCdNm, /*판매유형상세코드명*/
        String sapPdDvCd, /*SAP상품구분코드*/
        String sapPdAtcNm, /*SAP상품코드명*/
        String cntrCstNo, /*고객번호*/
        String cstKnm, /*고객명*/
        String cntrNo, /*계약번호*/
        String slRcogDt, /*매출일자*/
        String mlgBtdPrpdAmt, /*기초마일리지금액*/
        String dpTpCd, /*입금유형코드*/
        String chargedDpAmt, /*유상입금*/
        String freeDpAmt, /*무상입금*/
        String etcDpAmt, /*기타입금*/
        String chargedSlAmt, /*유상매출*/
        String freeSlAmt, /*무상매출*/
        String etcSlAmt, /*기타매출*/
        String mlgEotPrpdAmt /*마일리지기말선수금액*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 선수금 일자별 MainGridRes
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchAggregateDateRes")
    public record SearchAggregateDateRes(
        String slClYm, /*실적년월*/
        String slClDt, /*매출마감일자*/
        String sellTpCd, /*판매유형코드*/
        String sellTpCdNm, /*판매유형코드명*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellTpDtlCdNm, /*판매유형상세코드명*/
        String sapPdDvCd, /*SAP상품구분코드*/
        String sapPdAtcNm, /*SAP상품코드명*/
        String btdAtam, /*전기이월*/
        String atamDpAmt, /*선수입금*/
        String thmAtamRfndAmt, /*선수환불*/
        String dpTotAmt, /*입금합계*/
        String slBndAlrpyAmt, /*매출대사*/
        String borAmt, /*위약금*/
        String adjAmt, /*조정금액*/
        String etcProfit, /*잡이익*/
        String eotAtam /*기말 영업선수금*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 SearchFourthGridRes
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchOrderRes")
    public record SearchOrderRes(
        String slClYm, /*실적년월*/
        String sellTpCd, /*판매유형코드*/
        String sellTpCdNm, /*판매유형코드명*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellTpDtlCdNm, /*판매유형상세코드명*/
        String cntrNo, /*계약번호*/
        String sapPdDvCd, /*SAP상품구분코드*/
        String sapPdAtcNm, /*SAP상품코드명*/
        String cstNo, /*고객번호*/
        String cstKnm, /*고객명*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String btdAtam, /*전기이월*/
        String atamDpAmt, /*선수입금*/
        String thmAtamRfndAmt, /*선수환불액*/
        String dpTotAmt, /*입금합계*/
        String slBndAlrpyAmt, /*매출합계*/
        String borAmt, /*위약금*/
        String adjAmt, /*조정금액*/
        String etcProfit, /*잡이익*/
        String eotAtam /*기말 영업선수금*/
    ) {}
}
