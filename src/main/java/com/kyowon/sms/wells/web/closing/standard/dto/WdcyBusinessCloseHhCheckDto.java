package com.kyowon.sms.wells.web.closing.standard.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcyBusinessCloseHhCheckDto {
    // *********************************************************
    // Search Dto
    // *********************************************************
    // 일마감통제확인 Search Dto
    @ApiModel("WdcyBusinessCloseHhCheckDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String clDt,
        @NotBlank
        String clPsicNo,
        @NotBlank
        String clBizTpCd
    ) {}
    
 // *********************************************************
    // Result Dto
    // *********************************************************
    // 일마감통제확인 Result Dto
    @ApiModel("WdcyBusinessCloseHhCheckDto-SearchRes")
    public record SearchRes(
        @NotBlank
        String procsPsbYn,
        @NotBlank
        String perfDt
    ) {}
}
