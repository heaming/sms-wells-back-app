package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctcSiteAuditSellDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 현장감사 판매리스트 Search Request Dto
    @Builder
    @ApiModel("WctcSiteAuditSellDto-SearchReq")
    public record SearchReq(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String sellPrtnrNo,
        @NotBlank
        @Size(min = 1, max = 1)
        String ptrmDv,
        @NotBlank
        @ValidDate
        String dtStrt,
        @NotBlank
        @ValidDate
        String dtEnd,
        String cntrStatCd,
        String sellTpCd,
        String sellTpDtlCd,
        String pdMclsfId
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 현장감사 판매리스트 Search Result Dto
    @ApiModel("WctcSiteAuditSellDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        Integer perfCnt
    ) {}
}
