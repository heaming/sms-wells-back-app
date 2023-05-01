package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaAffiliateAlncDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WctaAffiliateAlncDto-SaveReq")
    public record SaveReq(
        String alncmpCd,
        String cntrNo,
        int cntrSn,
        String klyear,
        String klcode,
        String alncCanDt,
        String alncCanRsonCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctaAffiliateAlncDto-SearchRes")
    public record SearchRes() {}
}
