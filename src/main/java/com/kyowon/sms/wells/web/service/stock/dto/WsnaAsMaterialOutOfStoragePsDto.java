package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaAsMaterialOutOfStoragePsDto {

    @ApiModel(value = "WsnaAsMaterialOutOfStoragePsDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt,

        String serviceType,

        String prtnrNo,

        String refriType,

        String pdGrpCd,

        String ogId,

        String installBase,

        String itmKndCd,

        String itmPdCdFrom,

        String itmPdCdTo,

        String svBizDclsfCd
    ) {}

    @ApiModel(value = "WsnaAsMaterialOutOfStoragePsDto-SearchRes")
    public record SearchRes(
        String sapMatCd,

        String cntrNo,

        String cntrSn,

        String rnadr, //주소

        String rdadr, //주소 상세

        String locaraTno, //지역전화번호 (전화번호)

        String exnoEncr, //전화국번호암호화(전화번호)

        String idvTno, //개별전화번호(전화번호)

        String cntrRcpFshDtm,

        String newAdrZip,

        String itmPdCd,

        String itmPdNm,

        String useQty,

        String pdNm,

        String pdGdCd,

        String refriDvNm,

        String sellTpNm, // 판매유형명

        String rcgvpKnm,

        String egerCnrNm,

        String wkPrgsStatNm,

        String vstFshDt,

        String istDt,

        String vstCnfmdt,

        String modelNm,

        String fnlBcNo,

        String bcNo,

        String svBizHclsfNm,

        String prtnrKnm,

        String asLctNm,

        String asPhnNm,

        String asCausNm,

        String svProcsCn,

        String cnslMoCn
    ) {}
}
