package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WsnbFitForLifeFilterDto {
    @ApiModel(value = "WsnbfitForLifeFilterDto-SearchInfoReq")
    public record SearchInfoReq(
        @NotBlank
        String cntrNo,
        @NotNull
        Integer cntrSn
    ) {}

    @ApiModel(value = "WsnbfitForLifeFilterDto-SearchInfoRes")
    public record SearchInfoRes(
        String cntrNo,
        Integer cntrSn,
        String cntr,
        String rcgvpKnm,
        String vstDuedt,
        String altDeadline,
        String workNm,
        String partPdCd,
        String svpdNmKor
    ) {}

    @ApiModel(value = "WsnbfitForLifeFilterDto-SearchFiltersReq")
    public record SearchFiltersReq(
        @NotBlank
        String cntrNo,
        @NotNull
        Integer cntrSn,
        @NotBlank
        @ValidDate
        String vstDuedt
    ) {}

    @ApiModel(value = "WsnbfitForLifeFilterDto-SearchFiltersRes")
    public record SearchFiltersRes(
        String cntrNo,
        Integer cntrSn,
        String pdctPdCd,
        String svPdCd,
        String filtCmuCdv,
        String filtCmuNm,
        String filtCmuEpl,
        String chPdctPdCd,
        String svpdNmKor
    ) {}

    @ApiModel(value = "WsnbfitForLifeFilterDto-SearchHistoriesReq")
    public record SearchHistoriesReq(
        @NotBlank
        String cntrNo,
        @NotNull
        Integer cntrSn
    ) {}

    @ApiModel(value = "WsnbfitForLifeFilterDto-SearchHistoriesRes")
    public record SearchHistoriesRes(
        String cntrNo,
        Integer cntrSn,
        String fstRgstDt,
        String cntr,
        String vstPromDt,
        String request,
        String svpdNmKor,
        String usrNm,
        String channel,
        String status
    ) {}

    @ApiModel(value = "WsnbfitForLifeFilterDto-SaveFilterReq")
    public record SaveFilterReq(
        @NotBlank
        String cntrNo,
        @NotNull
        Integer cntrSn,
        @NotBlank
        String vstPromDt,
        @NotBlank
        String partPdCdBefore,
        @NotBlank
        String partPdCdAfter
    ) {}
}
