package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsniCubigVisitStopDto {

    @ApiModel(value = "WsniCubigVisitStopDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String apyDt,
        String sppStpDvCd,
        String tn1StpYm,
        String tn2StpYm,
        String tn3StpYm,
        String tn4StpYm,
        String sppAkSpfDt,
        String ogTpCd,
        String prtnrNo
    ) {}

    @ApiModel(value = "WsniCubigVisitStopDto-CreateRes")
    public record CreateRes(
        int result
    ) {}
}
