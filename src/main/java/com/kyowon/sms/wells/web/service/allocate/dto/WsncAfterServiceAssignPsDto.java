package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncAfterServiceAssignPsDto {

    @ApiModel(value = "WsncAfterServiceAssignPsDto-SearchReq")
    public record SearchReq(
        String wkExcnDt,
        String mngrDvCd,
        String pdPrpVal20,
        String pdCd
    ) {}

    @ApiModel(value = "WsncAfterServiceAssignPsDto-SearchRes")
    public record SearchRes(
        String wkExcnDt,
        String svBizHclsfCd,
        int acol1,
        int acol2,
        int acol3,
        int acol4,
        int acol5,
        int acol6,
        int acol7,
        int acol8,
        int acol9,
        int acol10,
        int acol11,
        int acol12,
        int totalCount
    ) {}

}
