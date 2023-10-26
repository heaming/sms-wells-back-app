package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaAsMaterialOutOfStoragePsDto {

    @ApiModel(value = "WsnaAsMaterialOutOfStoragePsDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,

        String serviceType,

        String prtnrNo,

        String refriType,

        String pdGrpCd,

        String ogCd,

        String installBase,

        String itmKndCd,

        String svBizDclsfCd
    ) {}

    @ApiModel(value = "WsnaAsMaterialOutOfStoragePsDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd
    ) {}
}
