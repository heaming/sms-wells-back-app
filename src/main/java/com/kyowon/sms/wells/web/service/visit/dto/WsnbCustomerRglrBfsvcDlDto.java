package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WsnbCustomerRglrBfsvcDlDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnbCustomerRglrBfsvcDlDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cstSvAsnNo,
        @NotBlank
        String asnOjYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnbCustomerRglrBfsvcDlDto-SaveRes")
    public record SaveRes(
        String cstSvAsnNo,
        String asnOjYm,
        String cntrNo,
        String cntrSn,
        String svBizMclsfCd,
        String svBizDclsfCd,
        String wkSn,
        String wkExcnDt

    ) {}

}
