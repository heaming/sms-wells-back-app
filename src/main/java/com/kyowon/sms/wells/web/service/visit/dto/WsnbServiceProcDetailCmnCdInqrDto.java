package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbServiceProcDetailCmnCdInqrDto {

    @ApiModel(value = "WsnbServiceProcDetailCmnCdInqrDto-SearchReq")
    public record SearchReq(
    String pdPrpVal20,

    String svBizDclsfCd

    ){}

    @ApiModel(value = "WsnbServiceProcDetailCmnCdInqrDto-CmnCdInqrRes")
    public record CmnCdInqrRes(
        String codeId,
        String codeName

    ) {}


}
