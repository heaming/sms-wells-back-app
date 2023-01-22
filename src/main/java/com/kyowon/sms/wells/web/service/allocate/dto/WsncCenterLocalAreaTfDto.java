package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncCenterLocalAreaTfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncCenterLocalAreaTfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String wareDvCd,
        String wareAreaCd,
        String zipFrom,
        String zipTo,
        String rsonCd
    ) {}

    @ApiModel(value = "WsncCenterLocalAreaTfDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String newAdrZip,
        String ctpvNm,
        String ctctyNm,
        String emdNm,
        String chSn,
        String mngerRglvlDvCd,
        String mngrDvCd,
        String brchOgId,
        String bfBrchOgId,
        String ichrLocaraCtrRsonCd,
        String fnlMdfcDtm,
        String mdfcBrchOgId,
        String mdfcIchrLocaraCtrRsonCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncCenterLocalAreaTfDto-SearchRes")
    public record SearchRes(
        String newAdrZip,
        String ctpvNm,
        String ctctyNm,
        String emdNm,
        String chSn,
        String mngerRglvlDvCd,
        String mngrDvCd,
        String brchOgId,
        String bfBrchOgId,
        String ichrLocaraCtrRsonCd,
        String fnlMdfcDtm,
        String mdfcBrchOgId,
        String mdfcIchrLocaraCtrRsonCd
    ) {}
}
