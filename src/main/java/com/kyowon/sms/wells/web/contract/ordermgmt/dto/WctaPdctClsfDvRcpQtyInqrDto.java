package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaPdctClsfDvRcpQtyInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctaPdctClsfDvRcpQtyInqrDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String startDate,
        String endDate
    ) {}
}
