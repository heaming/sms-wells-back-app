package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

public class WsnaEngineerToolDsbDto {
    @ApiModel(value = "WsnaEngineerToolDsbDto-SearchReq")
    public record SearchReq(

    ) {}

    @ApiModel(value = "WsnaEngineerToolDsbDto-SearchRes")
    public record SearchRes(

    ) {}

    @ApiModel(value = "WsnaEngineerToolDsbDto-SearchPartsRes")
    public record SearchPartsRes(
        String svpdSapCd,
        String svpdPdCd,
        String svpdNmAbbr1
    ) {}

    @ApiModel(value = "WsnaEngineerToolDsbDto-CreateReq")
    public record CreateReq(
        List<String> pdCd,
        List<String> egerPrtnrNo
    ) {}
}
