package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaEmployeeSellQuantityDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctaEmployeeSellQuantityDto-SearchReq")
    public record SearchReq(
        String rcpMm,
        String contractFromYm,
        String contractToYm,
        String sellPartnerNo,
        String contractCustomerNo,
        String baseProductCd
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaEmployeeSellQuantityDto-SearchRes")
    public record SearchRes(
        String pdQty,
        String dsnCntrQty,
        String istDt,
        String slDt
    ) {}
}
