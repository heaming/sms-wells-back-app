package com.kyowon.sms.wells.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctcDangerArbitDto {
    //*********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String searchGubun,
        String startDate,
        String endDate,
        String startMonth,
        String endMonth,
        String generalDivision,
        String regionalGroup,
        String branchOffice,
        String employeeNo
    ) {}
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchRes")
    public record SearchRes(
        String dangChkId,
        String dangOjPrtnrNo,
        String dangOcStrtdt,
        String dangOjOgId,
        String dangOjPrtnrNm,
        String dangOjPrtnrPstnDvNm,
        String dgr1HgrDgPrtnrNm,
        String dgr2HgrDgPrtnrNm,
        String dgr3HgrDgPrtnrNm,
        String dgr4HgrDgPrtnrNm,
        String dangChkNm,
        String dangArbitCdNm,
        String dangUncvrCt,
        String dangArbitLvyPc,
        String dangArbitOgNm,
        String fstRgstUsrId,
        String fstRgstDt
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 비정도 영업 조치 사항 등록 Save Request Dto
    @ApiModel(value = "WctcDangerArbitDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String dangChkId,
        String dangArbitCdNm,
        String dangArbitLvyPc,
        String dangArbitOgNm,
        String dangChkNm,
        @NotBlank
        String dangOcStrtdt,
        @NotBlank
        String dangOjOgId,
        String dangOjPrtnrNo,
        String dangUncvrCt
    ) {}
}
