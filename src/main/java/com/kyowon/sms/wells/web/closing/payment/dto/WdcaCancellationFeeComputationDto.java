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
        String RES_RTLFE_BOR_AMT,
        String RGST_COST_DSC_BOR_AMT,
        String RENTAL_DSC_BOR_AMT,
        String RSTL_BOR_AMT,
        String CSMB_COST_BOR_AMT,
        String P_BOR_AMT,
        String REQD_CS_BOR_AMT,
        String LS_RNTF,
        String BOR_AMT
    ) {}
}
