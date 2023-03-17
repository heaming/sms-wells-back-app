package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccDelinquentAdditionalChargesDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금 searchReq
    @ApiModel(value = "WdccDelinquentAdditionalChargesDto-SearchReq")
    public record SearchReq(
        String perfYm,
        String sellTpCd
        /*String sellChnlDtlCd 판매채널*/
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 연체가산금 searchRes
    @ApiModel(value = "WdccDelinquentAdditionalChargesDto-SearchRes")
    public record SearchRes(
        String perfYm,
        String cntrNo,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt
    ) {
    }
}
