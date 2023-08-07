package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WsnaStockStatusControlDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 재고상태조정 관리 Search Request Dto
    @Builder
    @ApiModel("WsnaStockStatusControlDto-SearchReq")
    public record SearchReq(
        String stFromYmd,
        String edToYmd,
        String wareNo,
        String itmKnd,
        String itmGdCtrTpCd,
        String itmGdCtrRsonCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 재고상태조정 관리 Search Result Dto
    @ApiModel("WsnaStockStatusControlDto-SearchRes")
    public record SearchRes(
        String wareNo,
        String wareNm,
        String wareMngtPrtnrNo,
        String wareDvCd,
        String ogNm,
        String itemKnd,
        String ctrWkDt,
        String statCtrApyDt,
        String itmPdCd,
        String itmGdCtrTpNm,
        String itmPdNm,
        String mgtUnit,
        int bfctNomStocAGdQty,
        int bfctNomStocEGdQty,
        int bfctNomStocRGdQty,
        String bfctItmGdCd,
        String afctItmGdCd,
        int ctrQty,
        String itmGdCtrRsonNm,
        String ctrSn,
        String rmkCn
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseReq")
    public record SearchWarehouseReq(
        String wareDvCd
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseRes")
    public record SearchWarehouseRes(
        String codeId,
        String codeName,
        String wareDvCd
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchItmPdCdReq")
    public record SearchItmPdCdReq(
        String itmKnd
    ) {}

    @ApiModel("WsnaStockStatusControlDto-FindOgNmRes")
    public record FindOgNmRes(
        String ogCd,
        String ogNm
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchItmPdCdRes")
    public record SearchItmPdCdRes(
        String codeId,
        String codeName,
        String itmKnd
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseItmPdCdReq")
    public record SearchWarehouseItmPdCdReq(
        String wareNo,
        String itmKnd
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchWarehouseItmPdCdRes")
    public record SearchWarehouseItmPdCdRes(
        String codeId,
        String codeName,
        String itmKnd,
        String wareNo
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchStatusProductReq")
    public record SearchStatusProductReq(
        String itmKnd,
        String stFromYmd,
        String wareDvCd
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchPdCdQtyReq")
    public record SearchPdCdQtyReq(
        String itmKnd,
        String stFromYmd,
        String wareNo,
        String itmPdCd
    ) {}

    @ApiModel("WsnaStockStatusControlDto-SearchPdCdQtyRes")
    public record SearchPdCdQtyRes(
        String pitmStocAGdQty,
        String pitmStocEGdQty,
        String pitmStocRGdQty,
        String itmPdCd,
        String itmKnd,
        String mgtUnit

    ) {}

    @ApiModel("WsnaStockStatusControlDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String wareNo,
        String wareNm,
        String wareMngtPrtnrNo,
        String wareDvCd,
        String ogNm,
        String itemKnd,
        String ctrWkDt,
        String statCtrApyDt,
        String itmPdCd,
        String itmGdCtrTpNm,
        String itmPdNm,
        String mgtUnit,
        int bfctNomStocAGdQty,
        int bfctNomStocEGdQty,
        int bfctNomStocRGdQty,
        String bfctItmGdCd,
        String afctItmGdCd,
        @NotBlank
        int ctrQty,
        String itmGdCtrRsonNm,
        String ctrSn,
        String rmkCn
    ) {}

    @ApiModel("WsnaStockStatusControlDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String rowState,
        String wareNo,
        String wareNm,
        String wareMngtPrtnrNo,
        String wareDvCd,
        String ogNm,
        String itemKnd,
        String ctrWkDt,
        String statCtrApyDt,
        String itmPdCd,
        String itmGdCtrTpNm,
        String itmPdNm,
        String mgtUnit,
        int bfctNomStocAGdQty,
        int bfctNomStocEGdQty,
        int bfctNomStocRGdQty,
        String bfctItmGdCd,
        String afctItmGdCd,
        @NotBlank
        int ctrQty,
        String itmGdCtrRsonNm,
        String ctrSn,
        String rmkCn
    ) {}

}
