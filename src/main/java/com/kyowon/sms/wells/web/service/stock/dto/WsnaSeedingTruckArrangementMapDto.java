package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 *  K-W-SV-U-0126M01 입고 미승인 현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.06.20
 */

public class WsnaSeedingTruckArrangementMapDto {

    @ApiModel(value = "WsnaStoreNaprvStateDto-SearchReq")
    public record SearchReq(
        String strWareDvCd,
        String strWareNoM,
        String strWareNoD
    ) {}

    @ApiModel(value = "WsnaStoreNaprvStateDto-PkgRes")
    public record PkgRes(
        String ggLctCd,
        String ggLctNm,
        String sdingPkgGrpCd,
        String sdingPkgGrpNm,
        Integer sdingPkgGrpQty
    ) {}

    @ApiModel(value = "WsnaStoreNaprvStateDto-SearchRes")
    public record SearchRes(
        String ggLctCd,
        String ggLctNm,
        Integer truckCnt,
        String placedAt,
        List<String> cart1F,
        List<String> cart1B,
        List<String> cart2F,
        List<String> cart2B,
        List<String> cart3F,
        List<String> cart3B,
        List<String> cart4F,
        List<String> cart4B,
        List<String> cart5F,
        List<String> cart5B,
        List<String> cart6F,
        List<String> cart6B,
        List<String> cart7F,
        List<String> cart7B,
        List<String> cart8F,
        List<String> cart8B,
        List<String> cart9F,
        List<String> cart9B
    ) {}
}
