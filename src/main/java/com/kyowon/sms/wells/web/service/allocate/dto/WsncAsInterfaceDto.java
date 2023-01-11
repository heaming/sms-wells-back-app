package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WsncAsInterfaceDto {

    @ApiModel(value = "WsncAsInterfaceDto-SearchCustInfoReq")
    public record SearchCustInfoReq(
        @NotBlank
        String cntrNo,
        @NotNull
        int cntrSn,
        String cstKnm,
        String hpno,
        String newAdrZip,
        String pdGrpId
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchCustInfoRes")
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

    @ApiModel(value = "WsncAsInterfaceDto-SearchRecInfoReq")
    public record SearchRecInfoReq(
        @NotBlank
        String cntrNo,
        String svBizDclsfCd
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchRecInfoRes")
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

    @ApiModel(value = "WsncAsInterfaceDto-SearchUsingProductReq")
    public record SearchUsingProductsReq(
        @NotBlank
        String cntrNo
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchUsingProductsRes")
    public record SearchUsingProductsRes(
        String cstNm,
        String cntrNo,
        String pdCd,
        String bcNo,
        String mpno,
        String adr,
        String pdNm
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchServiceHistoryReq")
    public record SearchServiceHistoryReq(
        @NotBlank
        String cntrNo,
        @NotNull
        @Min(1L)
        Integer pageIndex,
        @NotNull
        @Min(10L)
        Integer pageSize
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchServiceHistoryRes")
    public record SearchServiceHistoryRes(
        String svTpNm,
        String cntrNo,
        String wkExcnDt,
        String rgstDt,
        String asIstOjNo,
        String cstSvAsnNo,
        String svProcsCn,
        String cnslMoCn,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String rcpDtm,
        String istNmnN,
        String wkpNm,
        @NotNull
        @Min(1L)
        Integer pageIndex,
        @NotNull
        @Min(1L)
        Integer pageSize,
        Long totalCount
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchServiceContentsReq")
    public record SearchServiceContentsReq(
        @NotBlank
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchServiceContentsRes")
    public record SearchServiceContentsRes(
        String svTpNm,
        String cntrNo,
        String wkExcnDt,
        String rgstDt,
        String asIstOjNo,
        String cstSvAsnNo,
        String svProcsCn,
        String cnslMoCn,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String rcpDtm,
        String wkpNm,
        String pdNm,
        String matNm
    ) {}

}
