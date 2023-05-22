package com.kyowon.sms.wells.web.contract.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiCustomerAgreeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약 컨택 현황 조회 Search Request Dto
    @Builder
    @ApiModel("WctiCustomerAgreeDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CST_NO")
        String cstNo,
        @JsonProperty("CNTR_NO")
        String cntrNo

    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약 컨택 현황 조회 Search Result Dto
    @ApiModel("WctiContractContactDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo,
        @JsonProperty("CNTR_CST_NM")
        String cntrCstNm,
        @JsonProperty("SELL_PRTNR_NO")
        String sellPrtnrNo,
        @JsonProperty("AG_PROCS_DTM")
        String agProcsDtm,
        @JsonProperty("AG_ATC_DV_CD")
        String agAtcDvCd,
        @JsonProperty("AG_ATC_DV_NM")
        String agAtcDvNm,
        @JsonProperty("AG_STAT_CD")
        String agStatCd,
        @JsonProperty("AG_STAT_NM")
        String agStatNm,
        @JsonProperty("PRV_DOC_ID")
        String prvDocId
    ) {}
}
