package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncCapsuleSubscriptionCustomerDto {

    @ApiModel(value = "WsncCapsuleSubscriptionCustomerDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYmd
    ) {}

    @ApiModel(value = "WsncCapsuleSubscriptionCustomerDto-SaveRes")
    public record SaveRes(
        @NotBlank
        String result,
        String msg
    ) {}

}
