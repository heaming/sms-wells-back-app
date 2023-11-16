package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaAgencyLocaraFilterGiveAOrderPsDto {

    @ApiModel(value = "WsnaAgencyLocaraFilterGiveAOrderPsDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt,

        String bfsvcBzsDvCd
    ) {}

    @ApiModel(value = "WsnaAgencyLocaraFilterGiveAOrderPsDto-SearchRes")
    public record SearchRes(

        String yymm, // 기준년월

        String cstKnm,

        String cntrNo,

        String cntrSn,

        String rcgvpKnm,

        String basePdCd,

        String pdctPcCd,

        String sapMatCd,

        String pdNm,

        String cntrAdrpcId,

        String adrpcTpCd,

        String adrId,

        String adrDvCd,

        String newAdrZip,

        String rnadr,

        String rdadr,

        String cralLocaraTno,

        String mexnoEncr,

        String cralIdvTno,

        String locaraTno,

        String exnoEncr,

        String idvTno,

        String sppZip,

        String sppBasAdr,

        String sppDtlAdr,

        String partPdCd,

        String filtNm,

        String partUseQty,

        String locaraDvNm,

        String adrpcTpCdRnk,

        String tn // 발주 회차
    ) {}
    @ApiModel(value = "WsnaAgencyLocaraFilterGiveAOrderPsDto-SearchAgrgRes")
    public record SearchAgrgRes(

        String yymm, // 기준년월

        String locaraDvNm,

        String cstKnm,

        String partPdCd,

        String filtNm,

        String sumPartUseQty //합계수량
    ) {}
}
