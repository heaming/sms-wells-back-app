package com.kyowon.sms.wells.web.competence.evaluate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpsdWelsMngerSvCmpstIctrCstDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 웰스매니저 서비스종합지표(고객) Search Request Dto
    @Builder
    @ApiModel("WpsdWelsMngerSvCmpstIctrCstDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ogTpCd,
        @NotBlank
        String baseYm,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String prtnrNo
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 웰스매니저 서비스종합지표(고객) Search Result Dto
    @ApiModel("WpsdWelsMngerSvCmpstIctrCstDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String copnDvCd,
        String pdNm,
        String fstPrtnrNo,
        String fstPrtnrKnm,
        String cntcOgNm,
        String cntcPrtnrNo,
        String cntcPrtnrKnm,
        String absncRsonCd,
        String cntcDt,
        String cntcHh,
        String callYn,
        String msgYn,
        String cntcDtBf3,
        String vstDuedt,
        String vstExpHh,
        String vstInTime,
        String scnDtm,
        String bcInMthdCd,
        String vstFshDt,
        String vstFshHh,
        String vstPrgsStatCd,
        String prtnrKnm,
        String ogNm,
        String hpcallYn,
        String hpcallStpcN,
        String npVstDt,
        String npVstMth,
        String svBizDclsfNm,
        String rcgvpKnm,
        String cntrCstNo,
        String rnadr,
        String rdadr,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String cnfmPsicPrtnrNo
    ) {
    }
}
