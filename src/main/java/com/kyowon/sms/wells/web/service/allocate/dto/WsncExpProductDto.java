package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncExpProductDto {
    @ApiModel(value = "WsncExpProductDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncExpProductDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdNm,
        String puQty
    ) {}

}
