package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbBsPeriodChangeHistoryListDto {

    @ApiModel(value = "WsnbBsPeriodChangeHistoryListDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String mngtYm,
        String cntrDtlNo,
        String inqrDvCd,
        String pdGrpCd,
        String pdCd

    ) {}

    @ApiModel(value = "WsnbBsPeriodChangeHistoryListDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String moCntrDtlNo,
        String moRcgvpKnm,
        String moPdAbbrNm,
        String vstPrdCd,
        String cntrDtlNo,
        String rcgvpKnm,
        String pdAbbrNm,
        String bfchVstPrdCd,
        String afchVstPrdCd,
        String akRsonCn,
        String apyMm,
        String prtnrNm,
        String wkDt

    ) {}
}
