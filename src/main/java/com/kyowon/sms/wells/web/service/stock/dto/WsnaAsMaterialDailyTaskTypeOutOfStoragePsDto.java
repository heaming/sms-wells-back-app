package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto {

    @Builder
    @ApiModel(value = "WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String baseYear,
        String baseMonth,
        String wareDtlDvCd,
        String wareNoM,
        String wareNoD,
        String svBizHclsfCd,
        String useYn,
        String itmKndCd,
        String itmGrpCd,
        List<String> itmPdCds,
        String itmPdCdFrom,
        String itmPdCdTo,
        String itmPdCd,
        String strtSapCd,
        String endSapCd
    ) {}

    @ApiModel(value = "WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd,
        String pdNm,
        String asLdtm,
        String minGoQty,
        Integer last1yPrev3m,
        Integer last1y,
        Integer last1yNext2m,
        Integer last3m,
        BigDecimal mmAgrg,
        BigDecimal ddAgrg,
        Integer lastPrev1m,
        Integer qtyPajuSum,
        Integer qtyCenterSum,
        Integer qtyEngSum,
        Integer qtyMm,
        Integer qty1,
        Integer qty2,
        Integer qty3,
        Integer qty4,
        Integer qty5,
        Integer qty6,
        Integer qty7,
        Integer qty8,
        Integer qty9,
        Integer qty10,
        Integer qty11,
        Integer qty12,
        Integer qty13,
        Integer qty14,
        Integer qty15,
        Integer qty16,
        Integer qty17,
        Integer qty18,
        Integer qty19,
        Integer qty20,
        Integer qty21,
        Integer qty22,
        Integer qty23,
        Integer qty24,
        Integer qty25,
        Integer qty26,
        Integer qty27,
        Integer qty28,
        Integer qty29,
        Integer qty30,
        Integer qty31,
        String trnovrRtOjYn,
        String cmnPartDvCd,
        String ordnyHvMatYn
    ) {}
}
