package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbFeverbikeTalkSendDto {

    @ApiModel(value = "WsnbFeverbikeTalkSendDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String pifThpOfrAgYn,
        String cntrCstNm,
        String mpno
    ) {}
}
