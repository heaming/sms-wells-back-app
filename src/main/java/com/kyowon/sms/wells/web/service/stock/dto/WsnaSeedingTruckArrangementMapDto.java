package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 	K-W-SV-U-0302M01 모종 출하대차 MAP dto
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

public class WsnaSeedingTruckArrangementMapDto {

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchReq")
    public record SearchReq(
        @NotNull
        String baseDt
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchTodayGgLct")
    public record SearchTodayGgLct(
        String dgGgLctCd,
        String dgGgLctNm
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedAgg")
    public record SearchSeedAgg(
        String dgGgLctCd,
        String dgGgLctNm,
        String sdingPkgGrpCd,

        String sdingPkgGrpCdNm,

        Integer sdingQty
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchRes")
    public record SearchRes(
        String dgLctCd,
        String dgLctNm,
        Integer truckNo,
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
