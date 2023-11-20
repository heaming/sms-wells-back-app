package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaFilterOutOfStorageDetailDto {

    @ApiModel(value = "WsnaFilterOutOfStorageDetailDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt,

        String wareDvCd,

        String wareNoM,

        String wareNoD,

        String itmKndCd,

        String mngrDvCd,

        String refriDvCd,

        String fnlSellTpCd,

        String svBizHclsfCd,

        String pdGrpCd,

        String itmCdFrom,

        String itmCdTo,

        String rgsnYn
    ) {}
    @ApiModel(value = "WsnaFilterOutOfStorageDetailDto-SearchRes")
    public record SearchRes(
        String fnlVstFshDt,

        String cntrNo,

        String cntrSn,

        String cstNm,

        String fnlSellTpCd,

        String fnlSellTpNm,

        String fnlPdCd,

        String pdNm,

        String svBizHclsfCd,

        String svBizHclsfNm,

        String svBizDclsfCd,

        String svBizDclsfNm,

        String sapMatCd,

        String itmPdCd,

        String itmPdNm,

        String useQty,

        String refriDvCd,

        String refriDvNm,

        String prtnrNo,

        String prtnrKnm,

        String ogNm,

        String mngrDvCd,

        String wkWareNo
    ) {}
}
