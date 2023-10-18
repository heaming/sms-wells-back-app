package com.kyowon.sms.wells.web.competence.evaluate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpsdWelsMngerSvCmpstIctrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 웰스매니저 서비스종합지표 Search Request Dto
    @Builder
    @ApiModel("WpsdWelsMngerSvCmpstIctrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngtYm,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String awdDv
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 웰스매니저 서비스종합지표 Search Result Dto
    @ApiModel("WpsdWelsMngerSvCmpstIctrDto-SearchRes")
    public record SearchRes(
        String cnrOgCd,
        String cnrOgCd1,
        String cnrOgCd2,
        String svProcsRtVstAccN,
        String cttFshCt,
        String contactRate01,
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
        String hpcallRspRat,
        String hpcallAddPc,
        String totalScore,
        String allRank,
        String overScore,
        String ichrPrtnrNo,
        String ichrPrtnrNm,
        String rsbNm,
        String cttVstAccN,
        String cttCt1,
        String dgr1CttRat,
        String cttCt2,
        String dgr2CttRat,
        String cttPc,
        String hpcallFwCt,
        String hpcallAvPc,
        String hpcallEvlPc,
        String promObyPc,
        String svProcsRtEvlPc,
        String scnPc
    ) {
    }
}
