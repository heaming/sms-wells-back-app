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
    @ApiModel("WsnaQomAsnStockAggregationDto-SearchPdRes")
    public record SearchPdRes(
        // 품목코드
        String pdCd,
        // 품목명
        String pdNm,
        // 품목종류
        String itmKndCd,
        // 품목그룹
        String itmGrpCd
    ) {}

    @Builder
    @ApiModel("WsnaQomAsnStockAggregationDto-SearchReq")
    public record SearchReq(
        // 당월BS여부
        @NotBlank
        String bsYn,
        // 배정년월
        @NotBlank
        String asnOjYm,
        // 품목종류
        String itmKndCd,
        // 품목코드
        String itmPdCd,
        // 회차구분
        @NotBlank
        String cntGb,
        // BS품목구분
        String bsItmKndCd,
        // 배정W/M
        @NotBlank
        String qomAsnGb

    ) {}

    @Builder
    @ApiModel("WsnaQomAsnStockAggregationDto-SearchRes")
    public record SearchRes(
        // SAP코드
        String sapMatCd,
        // 품목코드
        String itmPdCd,
        // 품목명
        String pdNm,
        // 당월BS수량
        BigDecimal bsQty,
        // 개인창고 1차 수량
        BigDecimal indiQty1,
        // 개인창고 2차 수량
        BigDecimal indiQty2,
        // 개인창고 3차 수량
        BigDecimal indiQty3,
        // 독립창고 1차 수량
        BigDecimal indeQty1,
        // 독립창고 2차 수량
        BigDecimal indeQty2,
        // 독립창고 3차 수량
        BigDecimal indeQty3,
        // 물량배정 계
        BigDecimal qomAsnQty,
        // 파주 재고
        BigDecimal qty100002,
        // 성수 재고
        BigDecimal qty100008,
        // 물류 재고
        BigDecimal lgstQty,
        // 영업센터 조직재고
        BigDecimal centerQty,
        // 영업센터 개인재고
        BigDecimal centerIndiQty,
        // 파주+성수 부족재고
        BigDecimal lgstLackQty,
        // 성수 부족재고
        BigDecimal lackQty100008

    ) {}
}
