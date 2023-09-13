package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

public class WsndServiceIndicatorDto {
    @ApiModel(value = "WsndServiceIndicatorDto-SearchReq")
    public record SearchReq(
        String mgtYnm,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String prtnrNo
    ) {
    }

    @ApiModel(value = "WsndServiceIndicatorDto-SearchRes")
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
        Integer cntcDtBf3,
        String vstDuedt,
        String vstExpHh,
        String bcInMthdCd,
        String vstFshDt,
        String vstFshHh,
        String vstPrgsStatCd,
        String cnfmPsicPrtnrNo,
        String prtnrKnm,
        String ogNm,
        String hpcallYn,
        Integer hpcallStpcN,
        String npVstDt,
        Integer npVstMth
    ) {
    }
}
