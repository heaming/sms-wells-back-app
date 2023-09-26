package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbBsContactVisitPsAgrgDto {

    @ApiModel(value = "WsnbBsContactVisitPsAgrgDto-SearchReq")
    public record SearchReq(

        String perfYm,

        String mngrDvCd,

        String dgr1LevlOgId,

        String dgr2LevlOgId,

        String dgr3LevlOgId,

        String prtnrNo

    ) {}
    @ApiModel(value = "WsnbBsContactVisitPsAgrgDto-SearchRes")
    public record SearchRes(

        String dgr1LevlOgCd,

        String dgr1LevlOgNm,

        String dgr2LevlOgCd,

        String dgr2LevlOgNm,

        String npPtrmTcnt, /*이월배정*/

        String thmAsnTcnt, /*당월배정*/

        String asnTcnt, /* 합계 이월배정+당월배정*/

        String ncttTcnt, /*미컨택*/

        String cttFshTcnt, /*컨택완료*/

        String cttFshRat, /*컨택율*/

        String npPtrmWrkTcnt, /*이월배정방문완료*/

        String thmAsnWrkTcnt, /*당월배정방문완료*/

        String asnFshTcnt, /*합계(이월배정방문완료 + 당월배정방문완료)*/

        String asnFshRat, /*처리율*/

        String npPtrm1Tcnt, /*이월기간 1개월*/

        String npPtrm2Tcnt /*이월기간 2개월*/
    ) {}

}
