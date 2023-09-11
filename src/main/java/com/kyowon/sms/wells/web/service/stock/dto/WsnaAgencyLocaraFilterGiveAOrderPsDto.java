package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaAgencyLocaraFilterGiveAOrderPsDto {

    @ApiModel(value = "WsnaAgencyLocaraFilterGiveAOrderPsDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt
    ) {}
}
