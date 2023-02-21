package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctaMembershipContractSnDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctaMembershipContractSnDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String sellTpCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaMembershipContractSnDto-SearchRes")
    public record SearchRes(
        String cntrSn
    ) {}
}
