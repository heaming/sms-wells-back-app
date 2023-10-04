package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbServiceProcDetailListDto {

    @ApiModel(value = "WsnbServiceProcDetailListDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String cstSvAsnNo

    ) {}
}
