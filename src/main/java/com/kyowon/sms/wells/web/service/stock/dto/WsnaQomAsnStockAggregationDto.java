package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0127M01 물량배정 재고이송량 집계 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-04
 */

public class WsnaQomAsnStockAggregationDto {

    @Builder
    @ApiModel("WsnaQomAsnStockAggregationDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String bsYn,
        @NotBlank
        String asnOjYm,

        String itmKndCd,
        String itmPdCd,

        @NotBlank
        String cntGb,
        String bsItmKndCd,
        @NotBlank
        String qomAsnGb

    ) {}

    @Builder
    @ApiModel("WsnaQomAsnStockAggregationDto-SearchRes")
    public record SearchRes(

        String sapMatCd,
        String itmPdCd,
        String pdNm,
        BigDecimal bsQty,
        BigDecimal indiQty1,
        BigDecimal indiQty2,
        BigDecimal indiQty3,
        BigDecimal indeQty1,
        BigDecimal indeQty2,
        BigDecimal indeQty3,
        BigDecimal qomAsnQty,
        BigDecimal qty100002,
        BigDecimal qty100008,
        BigDecimal lgstQty,
        BigDecimal centerQty,
        BigDecimal centerIndiQty,
        BigDecimal lgstLackQty,
        BigDecimal lackQty100008

    ) {}
}
