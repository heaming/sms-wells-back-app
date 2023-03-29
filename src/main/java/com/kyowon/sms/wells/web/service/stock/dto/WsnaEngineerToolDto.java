package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnaEngineerToolDto {
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
        @NotBlank
        List<String> pdCds,
        @NotBlank
        List<String> egerPrtnrNos
    ) {}
}
