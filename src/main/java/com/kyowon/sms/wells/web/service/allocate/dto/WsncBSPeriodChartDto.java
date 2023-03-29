package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncBSPeriodChartDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncBSPeriodChartDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncBSPeriodChartDto-SearchBaseInfoRes")
    public record SearchBaseInfoRes(
        String cntrNo,
        String cntrSn,
        String bfsvcSppStpRsonCd,
        String sellTpCd,
        String svPdCd,
        String pdctPdCd,
        String istDt,
        int chekInstMths,
        String cntrPdStrtdt
    ) {}

    @ApiModel(value = "WsncBSPeriodChartDto-Search06Res")
    public record Search06Res(
        int vstNmnN,
        int totStplMcn
    ) {}

    @ApiModel(value = "WsncBSPeriodChartDto-Search07Res")
    public record Search07Res(
        String vstMths,
        String wrkTypDtl,
        String chngStg,
        String itemKnd,
        String partCd,
        int qty,
        String vstGb
    ) {}
}
