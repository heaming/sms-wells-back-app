package com.kyowon.sms.wells.web.closing.payment.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcaCancellationFeeComputationDto {
    // *********************************************************
    // 위약금 산출 Save Dto
    @ApiModel("WdcaCancellationFeeComputationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String rqdt,
        @NotBlank
        String duedt,
        String canTpCd,
        String lsRntf,
        String pBorAmt,
        String cnfmYm
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 위약금 산출 Result Dto
    @ApiModel("WdcaCancellationFeeComputationDto-SearchRes")
    public record SearchRes(
        String resRtlfeBorAmt,
        String rgstCostDscBorAmt,
        String rentalDscBorAmt,
        String rstlBorAmt,
        String csmbCostBorAmt,
        String pBorAmt,
        String reqdCsBorAmt,
        String lsRntf,
        String borAmt
    ) {}
}
