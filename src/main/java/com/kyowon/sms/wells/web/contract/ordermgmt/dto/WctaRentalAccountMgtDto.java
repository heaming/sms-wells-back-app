package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctaRentalAccountMgtDto {
    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchBpdRentalAccountRes")
    public record SearchBpdRentalAccountRes(
        String pdgrpNm,
        String pdNm,
        String basePdCd,
        String istDt,
        String rstlYn,
        String jCnt,
        String mchnChCnt,
        String reRentalCnt,
        String mshCnt,
        String keepRentalCnt,
        String sprExnCnt,
        String sprReqdCnt,
        String sprRat
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchBpdRentalAccountReq")
    public record SearchBpdRentalAccountReq(
        @NotBlank
        String srchGbn,
        @ValidDate
        String istStartDt,
        @ValidDate
        String istEndDt,
        String pdMclsfId,
        String basePdCd,
        String copnDvCd
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchByoRentalAccountRes")
    public record SearchByoRentalAccountRes(
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String istDt,
        String rstlYn,
        String jCnt,
        String mchnChCnt,
        String reRentalCnt,
        String mshCnt,
        String keepRentalCnt,
        String sprExnCnt,
        String sprReqdCnt,
        String sprRat
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchByoRentalAccountReq")
    public record SearchByoRentalAccountReq(
        @NotBlank
        String srchGbn,
        @ValidDate
        String istStartDt,
        @ValidDate
        String istEndDt,
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String copnDvCd
    ) {}
}
