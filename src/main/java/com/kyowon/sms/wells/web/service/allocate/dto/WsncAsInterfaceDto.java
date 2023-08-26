package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WsncAsInterfaceDto {

    @ApiModel(value = "WsncAsInterfaceDto-SearchCustInfoReq")
    public record SearchCustInfoReq(
        @NotBlank
        String cntrNo,
        String cstKnm,
        String hpno,
        String newAdrZip,
        String pdGrpId,
        String cntrSn,
        String cntrCstNo,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String adrId
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchCustInfoRes")
    public record SearchCustInfoRes(
        String cntrNo,
        String cstNo,
        String cstKnm,
        String cntrCnfmDtm,
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
        String adrDvCd,
        String addr,
        String rcgvpKnm,
        String bryyMmdd,
        String sexDvCd,
        String cntrCstNo
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchRecInfoReq")
    public record SearchRecInfoReq(
        @NotBlank
        String cntrNo,
        String cntrSn
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
        String cltnYn,
        String vstCnfmDt,
        String vstCnfmM,
        String vstExpDt,
        String vstExpM,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String adrDvCd
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

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustomerInformationReq")
    public record SearchCustomerInformationReq(
        @NotBlank
        @JsonProperty("AS_AK_ID")
        String asAkId,
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotNull
        @JsonProperty("CNTR_SN")
        String cntrSn
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustomerInformationRes")
    public record SearchCustomerInformationRes(
        @JsonProperty("AS_AK_ID")
        String asAkId,
        @JsonProperty("SYS_DV_CD")
        String sysDvCd,
        @JsonProperty("FST_RGST_DTM")
        String fstRgstDtm,
        @JsonProperty("FST_RGST_USR_ID")
        String fstRgstUsrId,
        @JsonProperty("PRTNR_KNM")
        String prtnrKnm,
        @JsonProperty("OG_CD")
        String ogCd,
        @JsonProperty("OG_NM")
        String ogNm,
        @JsonProperty("CST_UNUITM_CN")
        String cstUnuitmCn
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchAsSidingChangeReq")
    public record SearchAsSidingChangeReq(
        String rcpdtStrt, // 접수일자시작
        String rcpdtEnd, // 접수일자종료
        String pdCd, // 상품코드
        String cntrNo, // 계약번호
        String bcNo // 바코드번호
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchAsSidingChangeRes")
    public record SearchAsSidingChangeRes(
        String tpCd, /*유형코드*/
        String rcpdt, /*접수일자*/
        String cntrNo, /*계약번호*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String bcNo, /*바코드번호*/
        String wkTp, /*작업유형*/
        String asRson, /*AS사유*/
        String vstRqdt, /*방문요청일자*/
        String wkExcnDt, /*작업수행일자*/
        String wkPrgsStat, /*작업진행상태*/
        String pextSding, /*기존모종*/
        String chSding, /*변경모종*/
        String apyDt /*적용일자*/
    ) {}

}
