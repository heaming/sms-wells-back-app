package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaStockStatusControlDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 재고상태조정 관리 Search Request Dto
    @Builder
    @ApiModel("WsnaStockStatusControlDto-SearchReq")
    public record SearchReq(
        String stFromYmd, /* 입고희망일자 FROM*/
        String edToYmd, /* 입고희망일자 TO*/
        String wareNo, /* 창고번호 */
        String itmKnd, /* 품목구분 */
        String itmGdCtrTpCd, /* 상태조정유형 */
        String itmGdCtrRsonCd /* 상태조정사유 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 재고상태조정 관리 Search Result Dto
    @ApiModel("WsnaStockStatusControlDto-SearchRes")
    public record SearchRes(
        String wareNo, /* 창고번호 */
        String wareNm, /* 창고명 */
        String wareMngtPrtnrNo, /* 창고관리파트너번호 */
        String wareDvCd, /* 창고구분코드 */
        String ogNm, /* 조직명 */
        String itemKnd, /* 품목구분 */
        String ctrWkDt, /* 조정작업일자 */
        String statCtrApyDt, /* 상태조정적용일자 */
        String itmPdCd, /* 품목상품코드 */
        String itmGdCtrTpNm, /* 상태조정유형명 */
        String itmGdCtrTpCd, /* 상태조정유형코드 */
        String itmPdNm, /* 품목상품명 */
        String mgtUnit, /* 관리단위 */
        int bfctNomStocAGdQty, /* 조정전정상재고A등급수량 */
        int bfctNomStocEGdQty, /* 조정전정상재고E등급수량 */
        int bfctNomStocRGdQty, /* 조정전정상재고R등급수량 */
        String bfctItmGdCd, /* 조정전품목등급코드 */
        String afctItmGdCd, /* 조정후품목등급코드 */
        int ctrQty, /* 조정수량 */
        String itmGdCtrRsonCd, /* 품목등급조정사유코드 */
        String itmGdCtrRsonNm, /* 품목등급조정사유명 */
        String ctrSn, /* 조정일련번호 */
        String rmkCn, /* 비고 */
        String sapCd /* SAP코드*/
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseReq")
    public record SearchWarehouseReq(
        String wareDvCd /* 창고구분코드 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseRes")
    public record SearchWarehouseRes(
        String codeId, /* CODEID */
        String codeName, /* CODENAME */
        String wareDvCd /* 창고구분코드 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchItmPdCdReq")
    public record SearchItmPdCdReq(
        String itmKnd /* 품목구분 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-FindOgNmRes")
    public record FindOgNmRes(
        String ogCd, /* 조직코드 */
        String ogNm /* 조직명 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchItmPdCdRes")
    public record SearchItmPdCdRes(
        String codeId, /* CODEID */
        String codeName, /* CODENAME */
        String itmKnd /* 품목구분 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseItmPdCdReq")
    public record SearchWarehouseItmPdCdReq(
        String wareNo, /* 창고명 */
        String itmKnd /* 품목구분 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseItmPdCdRes")
    public record SearchWarehouseItmPdCdRes(
        String codeId, /* CODEID */
        String codeName, /* CODENAME */
        String itmKnd, /* 품목구분 */
        String sapCd, /* SAP코드 */
        String wareNo /* 창고번호 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchStatusProductReq")
    public record SearchStatusProductReq(
        String itmKnd, /* 품목구분 */
        String stFromYmd, /* 입고희망일자 */
        String wareDvCd /* 품목구분코드 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchPdCdQtyReq")
    public record SearchPdCdQtyReq(
        String itmKnd, /* 품목구분 */
        String stFromYmd, /* 입고희망일자 */
        String wareNo, /* 창고번호 */
        String itmPdCd /* 품목상품코드 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchPdCdQtyRes")
    public record SearchPdCdQtyRes(
        String pitmStocAGdQty, /* 시점재고 A등급수량 */
        String pitmStocEGdQty, /* 시점재고 E등급수량 */
        String pitmStocRGdQty, /* 시점재고 R등급수량 */
        String itmPdCd, /* 품목상품코드 */
        String itmKnd, /* 품목구분 */
        String mgtUnit /* 관리단위 */

    ) {}

    @ApiModel("WsnaStockStatusControlDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState, /* 상태 */
        String wareNo, /* 창고번호 */
        String wareNm, /* 창고명 */
        String wareMngtPrtnrNo, /* 창고관리파트너번호 */
        String wareDvCd, /* 창고구분코드 */
        String ogNm, /* 조직명 */
        String itemKnd, /* 품목구분 */
        String ctrWkDt, /* 조정작업일자 */
        String statCtrApyDt, /* 상태조정적용일자 */
        String itmPdCd, /* 품목상품코드 */
        String itmGdCtrTpNm, /* 조정전품목등급유형명 */
        String itmPdNm, /* 품목상품명 */
        String mgtUnit, /* 관리단위 */
        int bfctNomStocAGdQty, /* 조정전정상재고A등급수량 */
        int bfctNomStocEGdQty, /* 조정전정상재고E등급수량 */
        int bfctNomStocRGdQty, /* 조정전정상재고R등급수량 */
        String bfctItmGdCd, /* 조정전품목등급코드 */
        String afctItmGdCd, /* 조정후품목등급코드 */
        @Positive
        int ctrQty, /* 조정수량 */
        String itmGdCtrRsonNm, /* 품목등급조정사유코드명 */
        String ctrSn, /* 조정일련번호 */
        String rmkCn /* 비고 */
    ) {}

    @ApiModel("WsnaStockStatusControlDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String rowState, /* 상태 */
        String wareNo, /* 창고번호 */
        String wareNm, /* 창고명 */
        String wareMngtPrtnrNo, /* 창고관리파트너번호 */
        String wareDvCd, /* 창고구분코드 */
        String ogNm, /* 조직명 */
        String itemKnd, /* 품목구분 */
        String ctrWkDt, /* 조정작업일자 */
        String statCtrApyDt, /* 상태조정적용일자 */
        String itmPdCd, /* 품목상품코드 */
        String itmGdCtrTpCd, /* 조정전품목등급유형코드 */
        String itmGdCtrTpNm, /* 조정전품목등급유형명 */
        String itmPdNm, /* 품목상품명 */
        String mgtUnit, /* 관리단위 */
        int bfctNomStocAGdQty, /* 조정전정상재고A등급수량 */
        int bfctNomStocEGdQty, /* 조정전정상재고E등급수량 */
        int bfctNomStocRGdQty, /* 조정전정상재고R등급수량 */
        String bfctItmGdCd, /* 조정전품목등급코드 */
        String afctItmGdCd, /* 조정후품목등급코드 */
        @Positive
        int ctrQty, /* 조정수량 */
        String itmGdCtrRsonCd, /* 품목등급조정사유코드 */
        String itmGdCtrRsonNm, /* 품목등급조정사유코드명 */
        String ctrSn, /* 조정일련번호 */
        String rmkCn /* 비고 */
    ) {}

}
