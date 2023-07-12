package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

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
        String ctrSn
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
}
