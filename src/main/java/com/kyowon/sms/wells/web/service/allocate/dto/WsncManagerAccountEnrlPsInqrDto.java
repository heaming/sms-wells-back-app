package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncManagerAccountEnrlPsInqrDto {
    @ApiModel(value = "WsncManagerAccountEnrlPsInqrDto-SearchReq")
    public record SearchReq(
        String metgStartDt
    ){}

    // 총괄단 집계
    @ApiModel(value = "WsncManagerAccountEnrlPsInqrDto-SearchGnrdvAgrgRes")
    public record SearchGnrdvAgrgRes(
        String dgr1LevlOgCd,
        String dgr2LevlCnt,
        String hooPrtnrNm,
        String mngCnt,
        String realActMngCnt,
        String realActJijumCnt,
        String actMngCnt,
        String noActMngCnt,
        String cntAverage,
        String recruitCnt,
        String allVisitCnt,
        String visitCompleteCnt,
        String avgAge
    ){}

    // 매니저 계정 및 재적 현황
    @ApiModel(value = "WsncManagerAccountEnrlPsInqrDto-SearchMngerAccEnrlPsRes")
    public record SearchMngerAccEnrlPsRes(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String hooPrtnrNm,
        String bldNm,
        String mngCnt,
        String realActMngCnt,
        String realActJijumCnt,
        String actMngCnt,
        String noActMngCnt,
        String cntAverage,
        String recruitCnt,
        String allVisitCnt,
        String visitCompleteCnt,
        String avgAge
    ){}
}
