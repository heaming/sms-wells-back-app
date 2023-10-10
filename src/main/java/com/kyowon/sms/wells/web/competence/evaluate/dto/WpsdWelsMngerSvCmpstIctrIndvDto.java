package com.kyowon.sms.wells.web.competence.evaluate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpsdWelsMngerSvCmpstIctrIndvDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 웰스매니저 서비스종합지표(개인) Search Request Dto
    @Builder
    @ApiModel("WpsdWelsMngerSvCmpstIctrIndvDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ogTpCd,
        @NotBlank
        String mngtYm,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String prtnrNo
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 웰스매니저 서비스종합지표(개인) Search Result Dto
    @ApiModel("WpsdWelsMngerSvCmpstIctrIndvDto-SearchRes")
    public record SearchRes(
        String cnrOgCd,
        String ichrPrtnrNo,
        String ichrPrtnrNm,
        String rsbNm,
        String svProcsRtVstAccN,
        String cttFshCt,
        String contactRate,
        String contactRate3Bf,
        String contactPlus,
        String disCntct,
        String promObyCt,
        String promObyRat,
        String scnCt,
        String scnRat,
        String svProcsRtFshAccN,
        String svProcsRtVstRat,
        String hpcallRspCt,
        String hpcallAvPc,
        String totalScore,
        String allRank
    ) {
    }
}
