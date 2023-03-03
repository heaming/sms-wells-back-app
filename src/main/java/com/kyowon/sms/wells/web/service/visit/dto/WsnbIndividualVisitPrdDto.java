package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnbIndividualVisitPrdDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}

    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchPeriodReq")
    public record SearchPeriodReq(
        @NotBlank
        String svPdCd,
        @NotBlank
        String pdctPdCd
    ) {}

    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchAsnNoReq")
    public record SearchAsnNoReq(
        @NotBlank
        String asnOjYm,
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}

    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchProcessReq")
    public record SearchProcessReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,

        String baseYmd,
        String vstNmnN,
        String periodDeleteYmd,
        String asnOjYmd,
        String carriedForwardYmd,
        String rqstRsn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String rcgvpKnm,
        String basePdCd,
        String pdNm,
        String istDt,
        String memberStartDt,
        String memberEndDt,
        String sellTpCd,
        String chkPrdCd,
        String pdGdCd,
        String frisuBfsvcPtrmN
    ) {}

    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchVstRes")
    public record SearchVstRes(
        String vstNmnN,
        String istNmnN,
        String vstDuedt,
        String svBizDclsfCd,
        String filtChngLvCd,
        String pdNm,
        String wkDt,
        String mtrCanDt
    ) {}

    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchPeriodRes")
    public record SearchPeriodRes(
        String vstNmnN,
        String bfsvcWkDvCd,
        String pdNm,
        String partUseQty,
        String vstDvCd
    ) {}

    @ApiModel(value = "WsnbIndividualVisitPrdDto-SearchAsnNoRes")
    public record SearchAsnNoRes(
        String cstSvAsnNo
    ) {}
}
