package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbBsContactVisitPsDto {

    @ApiModel(value = "WsnbBsContactVisitPsDto-SearchReq")
    public record SearchReq(

        String asnOjYm,

        String dgr1LevlOgId,

        String dgr2LevlOgId,

        String dgr3LevlOgId,

        String prtnrNo

    ) {}
    @ApiModel(value = "WsnbBsContactVisitPsDto-SearchRes")
    public record SearchRes(

        String dgr3LevlOgCd,

        String dgr3LevlOgNm,

        String prtnrNo,

        String prtnrKnm,

        String qlfDvCd,

        String qlfDvNm,

        String cntrNo,

        String cntrSn,

        String rcgvpKnm,

        String sapMatCd,

        String pdctPdCd,

        String pdNm,

        String npPtrm, /*미처리기간*/

        String vstDuedt, /*방문예정일*/

        String cntcDt, /*최종컨택일자*/

        String absncRsonCd, /*최종컨택코드*/

        String absncRsonNm /*최종컨택코드*/
    ) {}

}
