package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbSamsungManufactureNoInqrDto {

    @ApiModel(value = "WsnbSamsungManufactureNoInqrDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String startDt,

        @NotBlank
        String endDt,

        String ssPdctBcNo,

        String cntrDtlNo,

        String saleCd,

        String rcgvpKnm

    ) {}
    @ApiModel(value = "WsnbSamsungManufactureNoInqrDto-SearchRes")
    public record SearchRes(
        String cntrNo,

        String cntrSn,

        String rcgvpKnm,

        String sellTpNm,

        String saleCd,

        String sapMatCd,

        String pdCd,

        String pdNm,

        String ssPdctBcNo,

        String fstRgstDt,

        String prtnrKnm,

        String ogNm,

        String wkPsicDvCd
    ) {}
}
