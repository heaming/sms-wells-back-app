package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdccDelinquentAdditionalChargesDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금 searchReq
    @ApiModel(value = "WdccDelinquentAdditionalChargesDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String slClYm,
        @NotBlank
        String agrgDv,
        @NotBlank
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtlCd, /*판매채널*/
        String sapPdDvCd /*SAP상품구분코드*/
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금 (집계) SearchAggregateRes
    @ApiModel(value = "WdccDelinquentAdditionalChargesDto-SearchAggregateRes")
    public record SearchAggregateRes(
        String perfYm,
        String sellTpCd,
        String sellTpDtlCd,
        String sapPdDvCd,
        String w1Am01,
        String w1Am02,
        String w1Am03,
        String w1Am04,
        String w1Am05,
        String w1Am06
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금 (주문별) SearchOrderUnitRes
    @ApiModel(value = "WdccDelinquentAdditionalChargesDto-SearchOrderUnitRes")
    public record SearchOrderUnitRes(
        String perfYm,
        String sellTpCd,
        String sellTpDtlCd,
        String sapPdDvCd,
        String cntrSn,
        String cstKnm,
        String w1Am01,
        String w1Am02,
        String w1Am03,
        String w1Am04,
        String w1Am05,
        String w1Am06
    ) {
    }
}
