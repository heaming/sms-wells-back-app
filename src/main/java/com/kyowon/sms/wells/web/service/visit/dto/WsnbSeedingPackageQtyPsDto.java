package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WsnbSeedingPackageQtyPsDto {
    @ApiModel(value = "WsnbSeedingPackageQtyPsDto-SearchReq")
    public record SearchReq(
        @NotNull
        String startDate,
        @NotBlank
        String endDate
    ) {}

    @ApiModel(value = "WsnbSeedingPackageQtyPsDto-SearchRes")
    public record SearchRes(
        String sdingPkgGrpCd,
        String sdingPkgGrpCdNm,
        String vstDt,
        Integer installUpload,
        Integer asUpload,
        Integer bsUpload,
        Integer totalUpload,
        Integer expInstall,
        Integer expAs,
        Integer expBs,
        Integer installCnt,
        Integer asCnt,
        Integer bsCnt,
        Integer useQty
    ) {}
}
