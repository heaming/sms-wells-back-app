package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaAsConsumablesStoreDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // AS소모품입고관리(엑셀업로드) Search Request Dto
    @Builder
    @ApiModel("WsnaAsConsumablesStoreDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String strRgstDt,
        @NotBlank
        String itmKndCd,
        String itmPdCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // AS소모품입고관리(엑셀업로드) Search Result Dto
    @ApiModel("WsnaAsConsumablesStoreDto-SearchRes")
    public record SearchRes(
        String strWareNo,
        int itmStrSn,
        String itmStrNo,
        String wareNm,
        String strRgstDt,
        String sapCd,
        String itmPdCd,
        String itmPdNm,
        String mngtUnitCd,
        String itmGdCd,
        String strQty,
        String rmkCn
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-FindItmStrNoReq")
    public record FindItmStrNoReq(
        String strTpCd, // 입고유형코드
        String strRgstDt // 입고일자
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String strWareNo,
        String wareNm,
        String rowState,
        String itmStrNo,
        int itmStrSn,

        @NotBlank
        String strRgstDt,
        String sapCd,

        @NotBlank
        String itmPdCd,
        String itmPdNm,

        @NotBlank
        String itmGdCd,

        @NotBlank
        String strQty,
        String rmkCn
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String strWareNo,
        String wareNm,
        String rowState,
        String itmStrNo,
        int itmStrSn,

        @NotBlank
        String strRgstDt,
        String sapCd,

        @NotBlank
        String itmPdCd,
        String itmPdNm,

        @NotBlank
        String itmGdCd,

        @NotBlank
        String strQty,
        String rmkCn

    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-SearchItemReq")
    public record SearchItemReq(
        String itmKndCd

    ) {}
    @ApiModel(value = "WsnaAsConsumablesStoreDto-SearchItemRes")
    public record SearchItemRes(
        String codeId,
        String codeName

    ) {}
}
