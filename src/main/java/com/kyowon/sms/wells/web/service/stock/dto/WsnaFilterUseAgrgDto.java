package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaFilterUseAgrgDto {

    @ApiModel(value = "WsnaFilterUseAgrgDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt,

        String pdGrpCd,

        String svBizHclsfCd,

        String mngrDvCd,

        String prtnrNo,

        String ogId,

        String dgr1LevlOgId,

        String dgr2LevlOgId,

        String dgr3LevlOgId,

        String rgsnYn
    ) {}
    @ApiModel(value = "WsnaFilterUseAgrgDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgId, // 1차레벨조직ID

        String dgr1LevlOgCd, // 1차레벨조직코드

        String dgr1LevlOgNm, // 1차레벨조직명

        String dgr2LevlOgId, // 2차레벨조직ID

        String dgr2LevlOgCd, // 2차레벨조직코드

        String dgr2LevlOgNm, // 2차레벨조직명

        String dgr3LevlOgId, // 3차레벨조직ID

        String dgr3LevlOgCd, // 3차레벨조직코드

        String dgr3LevlOgNm, // 3차레벨조직명

        String prtnrNo,

        String prtnrKnm,

        String wareNo,

        String bldNm,

        String pdCds // 상품 목록
    ) {}
    @ApiModel(value = "WsnaFilterUseAgrgDto-FindFilterProducts")
    public record FindFilterProductRes(
        String pdCd,

        String pdNm
    ) {}
}
