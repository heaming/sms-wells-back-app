package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbVisitStopDto {
    @ApiModel(value = "WsnbVisitStopInquiryDto-SearchCodeReq")
    public record SearchCodeReq(
        String cntrNo,
        String cntrSn
    ) {}

    @ApiModel(value = "WsnbVisitStopInquiryDto-SearchCodeRes")
    public record SearchCodeRes(
        String gbnCd,
        String code,
        String codeName,
        String takbeYn,
        String checkYn
    ) {}

    @ApiModel(value = "WsnbVisitStopInquiryDto-SearchHistoryRes")
    public record SearchHistoryRes(
        String akDt,
        String cntrNo,
        String sppStpDvCd,
        String prtnrKnm,
        String prtnrNo,
        String ogNm,
        String stat,
        String sppAkSpfDt,
        String apyDt,
        String tn1StpYm,
        String tn2StpYm,
        String tn3StpYm,
        String tn4StpYm,
        String pdNm
    ) {}

    @ApiModel(value = "WsnbVisitStopInquiryDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String apyDt,
        String sppStpDvCd,
        String tn1StpYm,
        String tn2StpYm,
        String tn3StpYm,
        String tn4StpYm,
        String sppAkSpfDt,
        String ogTpCd,
        String prtnrNo
    ) {}
}
