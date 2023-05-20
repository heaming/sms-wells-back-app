package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnaBuildingBsConsumableDto {
    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchReq")
    public record SearchReq(
        String mngtYm,
        List<String> bldCds
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchRes")
    public record SearchRes(
        String bldCd,
        String bldNm,
        String rsppPrtnrNo,
        String vstCstN,
        String reqYn,
        String bfsvcCsmbDdlvStatCd,
        List<String> fxnQtys,
        List<String> aplcQtys
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchItmRes")
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

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-FindTmlmRes")
    public record FindTmlmRes(
        String bizStrtdt,
        String bizStrtHh,
        String bizEnddt,
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-FindTmlmRes")
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

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchBldRes")
    public record SearchBldRes(
        String bldCd,
        String bldNm
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-CreateReq")
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
