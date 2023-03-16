package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdccSalesBondAtamDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 searchReq
    @ApiModel(value = "WdccDelinquentAdamtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        String perfDt,
        String agrgDv,
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtl,
        String cntr,
        String mlgBtdPrpdAmt
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 searchReq
    @ApiModel(value = "WdccDelinquentAdamtDto-SearchRes")
    public record SearchRes(
        String perfYm,       /*실적년월*/
        String perfDt, /*실적일자*/
        String cntr, /*계약상세번호*/
        /*String cstKnm, 고객명*/
        String pdCd, /*상품명*/
        String rveAmt, /*기초영업선수금*/
        String bznsAtamBlam /*(-)잡이익 */
    ) {
    }
}
