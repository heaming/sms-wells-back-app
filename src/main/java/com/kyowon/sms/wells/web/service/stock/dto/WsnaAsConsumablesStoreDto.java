package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

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
        // 입고등록일자
        @NotBlank
        String strRgstDt,
        // 품목구분코드
        @NotBlank
        String itmKndCd,
        // 품목상품코드
        String itmPdCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // AS소모품입고관리(엑셀업로드) Search Result Dto
    @ApiModel("WsnaAsConsumablesStoreDto-SearchRes")
    public record SearchRes(
        // 입고창고번호
        String strWareNo,
        // 품목입고순번
        int itmStrSn,
        // 품목입고번호
        String itmStrNo,
        // 창고명
        String wareNm,
        // 입고등록일자
        String strRgstDt,
        // SAP코드
        String sapCd,
        // 품목상품코드
        String itmPdCd,
        // 품목상품명
        String itmPdNm,
        // 관리단위코드
        String mngtUnitCd,
        // 품목등급코드
        String itmGdCd,
        // 입고수량
        String strQty,
        // 비고
        String rmkCn,
        // 등록일
        String fstRgstDt,
        // 등록자
        String rgstUsrNm,
        // 최종수정일
        String fnlMdfcDt,
        // 최종수정자
        String mdfcUsrNm
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-FindItmStrNoReq")
    public record FindItmStrNoReq(
        String strTpCd, // 입고유형코드
        String strRgstDt // 입고일자
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-SaveReq")
    public record SaveReq(
        // 입고창고번호
        @NotBlank
        String strWareNo,
        // 창고명
        String wareNm,
        // 로우상태
        String rowState,
        // 품목입고번호
        String itmStrNo,
        // 품목입고순번
        int itmStrSn,

        // 입고등록일자
        @NotBlank
        String strRgstDt,
        // SAP코드
        String sapCd,

        // 품목상품코드
        @NotBlank
        String itmPdCd,

        // 품목상품명
        String itmPdNm,

        // 품목등급코드
        @NotBlank
        String itmGdCd,

        // 입고수량
        @NotBlank
        String strQty,
        // 비고
        String rmkCn
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-RemoveReq")
    public record RemoveReq(
        // 입고창고번호
        @NotBlank
        String strWareNo,
        // 창고명
        String wareNm,
        // 로우상태값
        String rowState,
        //품목입고번호
        String itmStrNo,
        // 품목입고순번
        int itmStrSn,

        // 입고등록일자
        @NotBlank
        String strRgstDt,
        // SAP코드
        String sapCd,

        // 품목상품코드
        @NotBlank
        String itmPdCd,
        // 품목상품명
        String itmPdNm,

        // 품목등급코드
        @NotBlank
        String itmGdCd,

        // 입고수량
        @NotBlank
        String strQty,
        // 비고
        String rmkCn

    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-SearchItemReq")
    public record SearchItemReq(
        // 품목구분코드
        String itmKndCd

    ) {}
    @ApiModel(value = "WsnaAsConsumablesStoreDto-SearchItemRes")
    public record SearchItemRes(
        // CODEID
        String codeId,
        // CODENAME
        String codeName

    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-FindWareNmRes")
    public record FindWareNmRes(
        // 입고창고명
        String strWareNm
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-FindItmPdCdNmRes")
    public record FindItmPdCdNmRes(
        // 품목상품코드
        String itmPdCd,
        // 품목상품명
        String itmPdNm
    ) {}

    @ApiModel(value = "WsnaAsConsumablesStoreDto-FindSapCdNmRes")
    public record FindSapCdNmRes(
        // 품목상품코드
        String sapCd,
        // 품목상품명
        String itmPdNm
    ) {}
}
