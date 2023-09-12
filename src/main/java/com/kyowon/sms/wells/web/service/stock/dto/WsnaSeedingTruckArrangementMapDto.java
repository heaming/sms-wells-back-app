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
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedTotalRes")
    public record SearchSeedTotalRes(
        Integer pak01,
        Integer pak02,
        Integer pak03,
        Integer pak04,
        Integer pak05,
        Integer pak13,
        Integer pak23,
        Integer pak50,
        Integer pak08,
        Integer pak09,
        Integer pak12,
        Integer pak51,
        Integer pak52,
        Integer pak53,
        Integer pak54,
        Integer pak55,
        Integer pak56,
        Integer pak57,
        Integer pak58,
        Integer pak59,
        Integer pak60,
        Integer pak24,
        Integer pak28,
        Integer pak29,
        Integer pak30,
        Integer pak31
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchRes")
    public record SearchRes(
        List<SearchSeedAgrgRes> seedAgrgRes,
        SearchSeedTotalRes seedTotalRes
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedAgrgRes")
    public record SearchSeedAgrgRes(
        String dgLctCd,
        String dgLctNm,
        Integer truckNo,
        //        List<String> cart1F,
        //        List<String> cart1B,
        //        List<String> cart2F,
        //        List<String> cart2B,
        //        List<String> cart3F,
        //        List<String> cart3B,
        //        List<String> cart4F,
        //        List<String> cart4B,
        //        List<String> cart5F,
        //        List<String> cart5B,
        //        List<String> cart6F,
        //        List<String> cart6B,
        //        List<String> cart7F,
        //        List<String> cart7B,
        //        List<String> cart8F,
        //        List<String> cart8B,
        //        List<String> cart9F,
        //        List<String> cart9B
        String cart1F,
        String cart1B,
        String cart2F,
        String cart2B,
        String cart3F,
        String cart3B,
        String cart4F,
        String cart4B,
        String cart5F,
        String cart5B,
        String cart6F,
        String cart6B,
        String cart7F,
        String cart7B,
        String cart8F,
        String cart8B,
        String cart9F,
        String cart9B

    ) {}

}
