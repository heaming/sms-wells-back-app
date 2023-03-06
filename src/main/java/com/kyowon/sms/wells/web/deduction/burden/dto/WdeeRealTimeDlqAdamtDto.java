package com.kyowon.sms.wells.web.deduction.burden.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdeeRealTimeDlqAdamtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @Builder
    @ApiModel("WdeeRealTimeDlqAdamtDto-SearchReq")
    public record SearchReq(
        String cntrSn,
        @NotBlank
        String cntrNo,
        @NotBlank
        String perfYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WdeeRealTimeDlqAdamtDto-SearchRes")
    public record SearchRes(
        String thmCtrDlqAddAmt,
        String wordAcdp,
        String janAmt
    ) {}
}
