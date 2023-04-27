package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnaManagerBsConsumableDto {

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchReq")
    public record SearchReq(
        String mngtYm,
        List<String> bldCds
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchRes")
    public record SearchRes(
        String bldCd,
        String bldNm,
        String prtnrKnm,
        String prtnrNo,
        String reqYn,
        String ogCd,
        String vstCstN,
        String bfsvcCsmbDdlvStatCd,
        String wrfr,
        String bdtIndv,
        String bdtCrp,
        String arcleIndv,
        String arcleCrp,
        String wtrSftnr,
        String cffMchn,
        String msgcr,
        String dryr,
        String wash,
        String ardrssr,
        String sscling,
        List<String> fxnQtys,
        List<String> aplcQtys
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchItmRes")
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

    @ApiModel(value = "WsnaManagerBsConsumableDto-FindTmlmRes")
    public record FindTmlmRes(
        String bizStrtdt,
        String bizStrtHh,
        String bizEnddt,
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-FindTmlmRes")
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

    @ApiModel(value = "WsnaManagerBsConsumableDto-CreateReq")
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
