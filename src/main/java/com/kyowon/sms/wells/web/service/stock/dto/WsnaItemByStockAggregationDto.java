package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0140M01 품목별 재고 집계 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-08
 */

public class WsnaItemByStockAggregationDto {

    @Builder
    @ApiModel("WsnaItemByStockAggregationDto-SearchWareRes")
    public record SearchWareRes(
        String wareDtlDvCd,
        String wareNo,
        String wareNm
    ) {}

    @Builder
    @ApiModel("WsnaItemByStockAggregationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String baseDt,
        String mgtTypCd,
        String itmKndCd,
        List<String> itmPdCds,
        String itmGdCd,
        String useYn,
        String matUtlzDvCd,
        String itmPdCd,
        String strtSapCd,
        String endSapCd

    ) {}
}
