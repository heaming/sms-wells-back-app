package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncCapsuleSubscriptionCustomerDto {

    @ApiModel(value = "WsncCapsuleSubscriptionCustomerDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYmd
    ) {}

}
