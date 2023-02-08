package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctcUserSellLimitMngtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctcRiskAuditDto-SearchReq")
    public record SearchReq(
        String startDate,
        String endDate,
        String sell,
        String channel,
        String deptCd,
        String user,
        String productName,
        String sellType,
        String sellLimit
    ) {}
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctcRiskAuditDto-SearchRes")
    public record SearchRes(
        String sellBaseId,
        String sellBaseTpCd,
        String sellBaseChnl,
        String deptCd,
        String sellBaseUsr,
        String copnDvCd,
        String zip,
        String pdCd,
        String pdMclsfId,
        String pdMclsfNm,
        String pdLclsfId,
        String pdLclsfNm,
        String pdNm,
        String sellBasePrd,
        String sellBaseSellTp,
        String sellPrmitDvCd,
        String vlStrtDtm,
        String vlEndDtm,
        String sellBaseApyCn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId
    ) {}

    // *********************************************************
    // Request Cd Check Dto
    // *********************************************************
    @ApiModel(value = "WctcRiskAuditDto-SearchReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String sellBaseId,
        @NotBlank
        String sellBaseTpCd,
        String sellBaseChnl,
        String deptCd,
        String sellBaseUsr,
        String copnDvCd,
        String zip,
        @NotBlank
        String pdCd,
        String pdMclsfId,
        String pdLclsfId,
        String pdNm,
        String sellBasePrd,
        @NotBlank
        String sellBaseSellTp,
        String sellPrmitDvCd,
        @NotBlank
        String vlStrtDtm,
        String vlEndDtm,
        String sellBaseApyCn
    ) {}
}