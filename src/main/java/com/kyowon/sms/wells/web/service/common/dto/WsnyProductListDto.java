package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

public class WsnyProductListDto {

    @ApiModel(value = "WsnyProductListDto-SearchReq")
    public record SearchReq(
        String itmKndCd, // 품목종류
        String pdGrpCd // 상품구분
    ) {}

    @ApiModel(value = "WsnyProductListDto-SearchRes")
    public record SearchRes(
        String codeId,
        String codeName,
        String itmKndCd,
        String pdGrpCd
    ) {}
}
