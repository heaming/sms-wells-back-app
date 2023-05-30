package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WctaSeedingDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 모종 Search Request Dto
    @Builder
    @ApiModel("WctaSeedingDto-SearchReq")
    public record SearchMachineReq(
        @NotBlank
        String cntrCstNo,
        @NotBlank
        String rglrSppMchnTpCd /* enumerable???????*/
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 모종 Search Result Dto
    @ApiModel("WctaSeedingDto-SearchRes")
    public record SearchMachineRes(
        String cntrNo,
        String cntrSn,
        String pdCd,
        String pdNm,
        String istDt,
        String posQty
    ) {
    }
}
