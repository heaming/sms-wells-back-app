package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaFilterOutOfStorageAgrgDto {

    @ApiModel(value = "WsnaFilterOutOfStorageAgrgDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt,

        String itmKndCd,

        String findGb
    ) {}

}
