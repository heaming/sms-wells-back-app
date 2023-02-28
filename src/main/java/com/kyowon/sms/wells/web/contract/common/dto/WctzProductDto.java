package com.kyowon.sms.wells.web.contract.common.dto;

import io.swagger.annotations.ApiModel;

public class WctzProductDto {
    @ApiModel("WctzProductDto-SearchMiddleClassesRes")
    public record SearchMiddleClassesRes(
        String pdClsfId,
        String pdClsfNm,
        String hgrPdClsfId,
        String pdClsfCd
    ) {}
}
