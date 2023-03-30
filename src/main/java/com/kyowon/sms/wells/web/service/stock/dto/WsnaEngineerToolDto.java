package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

public class WsnaEngineerToolDto {
    @ApiModel(value = "WsnaEngineerToolDto-SearchReq")
    public record SearchReq(
        String pymdt,
        String ogCd,
        String egerPrtnrNo,
        String prtnrKnm,
        String toolPdCdStrt,
        String toolPdCdEnd,
        String sapMatCdStrt,
        String sapMatCdEnd
    ) {}

    @ApiModel(value = "WsnaEngineerToolDto-SearchRes")
    public record SearchRes(
        String ogTpCd,
        String egerPrtnrNo,
        String toolPdCd,
        String dsbSn,
        String toolQty,
        String ogCd,
        String ogNm,
        String hgrOgNm,
        String prtnrNo,
        String prtnrKnm,
        String svpdNmAbbr1,
        String svpdSapCd,
        String pymdts,
        int maxToolQty
    ) {}

    @ApiModel(value = "WsnaEngineerToolDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String ogTpCd,
        @NotBlank
        String egerPrtnrNo,
        @NotBlank
        String toolPdCd
    ) {}

    @ApiModel(value = "WsnaEngineerToolDto-SearchPartsRes")
    public record SearchPartsRes(
        String svpdSapCd,
        String svpdPdCd,
        String svpdNmAbbr1
    ) {}

    @ApiModel(value = "WsnaEngineerToolDto-CreateReq")
    public record CreateReq(
        @NotEmpty
        List<String> pdCds,
        @NotEmpty
        List<String> egerPrtnrNos
    ) {}
}
