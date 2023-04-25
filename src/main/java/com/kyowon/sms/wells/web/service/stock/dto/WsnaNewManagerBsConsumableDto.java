package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnaNewManagerBsConsumableDto {

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-SearchReq")
    public record SearchReq(
        String mngtYm,
        List<String> bldCds
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-SearchRes")
    public record SearchRes(
        String bldCd,
        String bldNm,
        String prtnrNmNo,
        String prtnrNo,
        String reqYn,
        List<String> fxnQtys,
        List<String> aplcQtys
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-SearchItmRes")
    public record SearchItmRes(
        String bfsvcCsmbDdlvTpCd,
        String fxnPdCd,
        String fxnPckngUnit,
        String fxnPdNm,
        String fxnSapMatCd,
        String aplcPdCd,
        String aplcPckngUnit,
        String aplcPdNm,
        String aplcSapMatCd,
        String qty
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-FindTmlmRes")
    public record FindTmlmRes(
        String bizStrtdt,
        String bizStrtHh,
        String bizEnddt,
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-FindTmlmRes")
    public record CreateTmlmReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String bizStrtdt,
        @NotBlank
        String bizStrtHh,
        @NotBlank
        String bizEnddt,
        @NotBlank
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String bfsvcCsmbDdlvOjCd,
        @NotBlank
        String strWareNo,
        @NotBlank
        String csmbPdCd,
        @NotBlank
        String sapMatCd,
        @NotBlank
        String bfsvcCsmbDdlvQty,
        @NotBlank
        String bfsvcCsmbDdlvStatCd
    ) {}
}
