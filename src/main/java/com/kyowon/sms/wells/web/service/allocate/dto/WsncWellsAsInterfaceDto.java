package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncWellsAsInterfaceDto {

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustInfoReq")
    public record SearchCustInfoReq(
        String cntrNo,
        int cntrSn,
        String cstKnm,
        String hpno,
        String newAdrZip,
        String pdGrpId
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustInfoRes")
    public record SearchCustInfoRes(
        String cntrNo,
        String cstKnm,
        String cntrDt,
        String pdCd,
        String pdNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String lgsysCd,
        String oldAdrZip,
        String ltnAdr,
        String ltnDtlAdr,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String addRefer,
        String addGb,
        String addr,
        String hpno,
        String telno,
        String rcgvpKnm,
        String bryyMmdd,
        String sexDvCd,
        String cntrCstNo
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchRecInfoReq")
    public record SearchRecInfoReq(
        String cntrNo,
        String svBizDclsfCd
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchRecInfoRes")
    public record SearchRecInfoRes(
        String cntrNo,
        String inChnlDvCd,
        String svBizHclsfCd,
        String rcpdt,
        String asIstOjNo,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String cnslMoCn,
        String wkPrgsStatCd,
        String wkAcpteStatCd,
        String vstCnfmdt,
        String vstCnfmHh,
        String vstDuedt,
        String vstExpHh,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String oldAdrZip,
        String ltnAdr,
        String ltnDtlAdr,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String addRefer,
        String addGb
    ) {}
}
