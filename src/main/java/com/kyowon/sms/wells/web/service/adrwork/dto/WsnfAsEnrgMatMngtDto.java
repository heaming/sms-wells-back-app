package com.kyowon.sms.wells.web.service.adrwork.dto;

import io.swagger.annotations.ApiModel;

public class WsnfAsEnrgMatMngtDto {

    @ApiModel(value = "WsnfAsEnrgMatMngtDto-SearchReq")
    public record SearchReq(
        String pdGrpCd,
        String pdCd,
        String classA,
        String classB,
        String classC
    ) {}
}
