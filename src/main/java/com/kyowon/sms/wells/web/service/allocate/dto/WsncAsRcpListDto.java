package com.kyowon.sms.wells.web.service.allocate.dto;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsncAsRcpListDto {

    @ApiModel(value = "WsncAsRcpListDto-SearchReq")
    public record SearchReq(

        String baseYm,

        String procYn,

        String rgsnYn,

        String rcpGb,

        String mngrDvCd,

        String dgr1LevlOgId,

        String dgr2LevlOgId,

        String dgr3LevlOgId,

        String ogId,

        String prtnrNo
    ) {}

    @ApiModel(value = "WsncAsRcpListDto-SearchRes")
    public record SearchRes(

        String dgr1LevlOgCd,

        String dgr1LevlOgNm,

        String dgr2LevlOgCd,

        String dgr2LevlOgNm,

        String dgr3LevlOgNm,

        String prtnrNo,

        String prtnrKnm,

        String bldNm,

        String istDt,

        String cntrNo,

        String cntrSn,

        String rcgvpKnm,

        String sapMatCd,

        String pdCd,

        String pdNm,

        String cralLocaraTno,

        String mexnoEncr,

        String cralIdvTno,

        String locaraTno,

        String exnoEncr,

        String idvTno,

        String newAdrZip,

        String rnadr,

        String rdadr,

        String asAskCn,

        String rcpDt,

        String rcpPrtnrKnm,

        String svBizDclsfNm,

        String weccProcsYn,

        String pcsDt,

        String pcpId,

        String wkPrtnrKnm,

        String wkExcnDt,

        String wkPrgsStatNm,

        String wkCanRsonNm,

        String wkCanCn
    ) {
        public SearchRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }
}
