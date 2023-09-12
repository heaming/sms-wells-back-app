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
        String cntrNo,
        String cntrSn,
        String pdctPdCd,
        String svPdCd,
        String istDt,
        String reqdDt,
        String cpsDt,
        String cntrNmnN,
        String updtPsicDvCd,
        String bcNo,
        String wkPsicDvCd,
        String mngtPrtnrOgTpCd,
        String mngtPrtnrNo
    ){}

    // 매니저 계정 및 재적 현황
    @ApiModel(value = "WsncManagerAccountEnrlPsInqrDto-SearchMngerAccEnrlPsRes")
    public record SearchMngerAccEnrlPsRes(
        String asIstOjNo,
        String cntrNo,
        String cntrSn,
        String cntrCstNo,
        String inChnlDvCd,
        String svBizHclsfCd,
        String svBizDclsfCd,
        String rcpOgTpCd,
        String rcpIchrPrtnrNo,
        String rcpdt,
        String fnlRcpdt
    ){}
}
