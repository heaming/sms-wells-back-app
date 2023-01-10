package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnyCalendarDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnyCalendarDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String serviceCenterCd
    ) {}

    @ApiModel(value = "WsnyCalendarDto-FineRegReq")
    public record FineRegReq(
        @NotBlank
        String svCnrOgId,
        @NotBlank
        String baseY,
        @NotBlank
        String baseMm,
        @NotBlank
        String baseD
    ) {}

    @ApiModel(value = "WsnyCalendarDto-SaveRegReq")
    public record SaveRegReq(
        @NotBlank
        String svCnrOgId,
        @NotBlank
        String baseY,
        @NotBlank
        String baseMm,
        @NotBlank
        String baseD,
        String dfYn,
        String rmkCn,
        String bndtWrkPsicNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnyCalendarDto-SearchRes")
    public record SearchRes(
        String baseY,
        String baseMm,
        String baseD,
        String dowDvCd,
        String dfYn,
        String phldYn,
        String rmkCn,
        String wrkStrtHh,
        String wrkEndHh,
        String restHh,
        String wrkHh,
        String bndtWrkPsicNo,
        String svCnrOgId
    ) {}

    @ApiModel(value = "WsnyCalendarDto-FindRegRes")
    public record FindRegRes(
        String baseY,
        String baseMm,
        String baseD,
        String dfYn,
        String rmkCn,
        String svCnrOgId,
        String bndtWrkPsicNo
    ) {}
}
