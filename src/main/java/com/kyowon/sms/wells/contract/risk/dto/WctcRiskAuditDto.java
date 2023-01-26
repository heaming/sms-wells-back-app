package com.kyowon.sms.wells.contract.risk.dto;

import io.swagger.annotations.ApiModel;

public class WctcRiskAuditDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchRes")
    public record SearchRes(
        String dangChkId,
        String wellsOjPstnRankNm,
        String dangMngtPntnrOgNm,
        String dangMngtPntnrOgCd,
        String dangMngtPntnrNm,
        String dangMngtPrtnrNo,
        String dangOjPrtnrNm,
        String dangOjPrtnrNo,
        String dangOjPrtnrPstnDvNm,
        String dangOcStrtmm,
        String dangArbitOgNm,
        String dangChkNm,
        String dangArbitCdNm,
        String dangUncvrCt,
        String dangArbitLvyPc,
        String dangArbitLvyPcSum,
        String fstRgstUsrId,
        String fstRgstDt
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchReq")
    public record SearchReq(
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
}
