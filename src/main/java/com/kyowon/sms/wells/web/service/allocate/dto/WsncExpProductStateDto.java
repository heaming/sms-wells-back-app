package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncExpProductStateDto {
    @ApiModel(value = "WsncExpProductStateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String ogId,
        String pdGrpCd,
        String pdCd
    ) {}

    @ApiModel(value = "WsncExpProductStateDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdNm,
        String puQty,
        String ogGdQty,
        String indvGdQty
    ) {}

}
