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
        String sapPdDvCd,
        String sapPdDvNm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 searchReq
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String slClYm,
        String agrgDv,
        String sapPdDvCd,
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtl,
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금(포인트/집계) ThirdGrid
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchPointAggregateRes")
    public record SearchPointAggregateRes(
        String slClYm,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdAtcNm,
        String mlgBtdPrpdAmt,
        String mlgDpAmt,
        String mlgRfndAmt,
        String mlgTotAmt,
        String mlgSlAmt,
        String mlgEotPrpdAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금(포인트/주문별) FourthGrid
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchPointOrderRes")
    public record SearchPointOrderRes(
        String slClYm,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdAtcNm,
        String cntrCstNo,
        String cstKnm,
        String cntrNo,
        String slRcogDt,
        String mlgBtdPrpdAmt,
        String dpTpCd,
        String chargedDpAmt,
        String freeDpAmt,
        String etcDpAmt,
        String chargedSlAmt,
        String freeSlAmt,
        String etcSlAmt,
        String mlgEotPrpdAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 선수금 일자별 MainGridRes
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchAggregateDateRes")
    public record SearchAggregateDateRes(
        String slClYm,
        String slClDt,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdAtcNm,
        String btdAtam,
        String atamDpAmt,
        String thmAtamRfndAmt,
        String dpTotAmt,
        String slBndAlrpyAmt,
        String borAmt,
        String adjAmt,
        String etcProfit,
        String eotAtam
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 SearchFourthGridRes
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchOrderRes")
    public record SearchOrderRes(
        String slClYm,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String cntrNo,
        String sapPdDvCd,
        String sapPdAtcNm,
        String cstNo,
        String cstKnm,
        String pdCd,
        String pdNm,
        String btdAtam,
        String atamDpAmt,
        String thmAtamRfndAmt,
        String dpTotAmt,
        String slBndAlrpyAmt,
        String borAmt,
        String adjAmt,
        String etcProfit,
        String eotAtam
    ) {}
}
