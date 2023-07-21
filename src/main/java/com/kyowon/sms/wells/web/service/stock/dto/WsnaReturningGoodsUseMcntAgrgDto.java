package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaReturningGoodsUseMcntAgrgDto {
    @ApiModel(value = "WsnaReturningGoodsUseMcntAgrgDto-SearchReq")
    public record SearchReq(
        String itmGdCd,
        String startDt,
        String endDt,
        String serviceCenter,
        String svBizDclsfCd

    ) {}

    @ApiModel(value = "WsnaReturningGoodsUseMcntAgrgDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd,
        String pdNm,
        String useMcnT,
        String useMcn1,
        String useMcn2,
        String useMcn36,
        String useMcn712,
        String useMcn1324,
        String useMcn2536,
        String useMcn3748,
        String useMcn4960,
        String useMcn6172,
        String useMcn7384,
        String useMcn85

    ) {}

}
