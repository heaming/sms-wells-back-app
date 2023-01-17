package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbFeverbikeBiztalkDto {

    @ApiModel(value = "WsnbFeverbikeTalkSendDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        int cntrSn,
        String pifThpOfrAgYn,
        String cntrCstNm,
        String mpno
    ) {}

        @ApiModel(value = "WsnbFeverbikeTalkSendDto-SaveReq")
    public record SaveReq(
        String cntrNo
    ) {}
}
