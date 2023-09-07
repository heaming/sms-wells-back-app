package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0275M01 폐필터 회수 현황 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

public class WsnaWasteFilterCollectionPsDto {

    @Builder
    @ApiModel("WsnaWasteFilterCollectionPsDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String wareDvCd,
        String hgrWareNo,
        String wareNo,
        String svBizHclsfCd
    ) {}

    @Builder
    @ApiModel("WsnaWasteFilterCollectionPsDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd,
        String pdNm,
        String gubun,
        String gubunCd,

        BigDecimal d01Qty,
        BigDecimal d02Qty,
        BigDecimal d03Qty,
        BigDecimal d04Qty,
        BigDecimal d05Qty,
        BigDecimal d06Qty,
        BigDecimal d07Qty,
        BigDecimal d08Qty,
        BigDecimal d09Qty,
        BigDecimal d10Qty,
        BigDecimal d11Qty,
        BigDecimal d12Qty,
        BigDecimal d13Qty,
        BigDecimal d14Qty,
        BigDecimal d15Qty,
        BigDecimal d16Qty,
        BigDecimal d17Qty,
        BigDecimal d18Qty,
        BigDecimal d19Qty,
        BigDecimal d20Qty,
        BigDecimal d21Qty,
        BigDecimal d22Qty,
        BigDecimal d23Qty,
        BigDecimal d24Qty,
        BigDecimal d25Qty,
        BigDecimal d26Qty,
        BigDecimal d27Qty,
        BigDecimal d28Qty,
        BigDecimal d29Qty,
        BigDecimal d30Qty,
        BigDecimal d31Qty,

        BigDecimal totQty
    ) {}

}
