package com.kyowon.sms.wells.web.service.allocate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WsncAsInterfaceDto {

    @ApiModel(value = "WsncAsInterfaceDto-SearchCustInfoReq")
    public record SearchCustInfoReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("CST_KNM")
        String cstKnm,
        //        @JsonProperty("HPNO")
        //        String hpno,
        //        @JsonProperty("NEW_ADR_ZIP")
        //        String newAdrZip,
        @JsonProperty("PD_GRP_ID")
        String pdGrpId,
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO_ENCR")
        @DBEncField
        @DBDecField
        String mexnoEncr,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("ADR_ID")
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
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchRecInfoRes")
    public record SearchRecInfoRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "IN_CHNL_DV_CD")
        String inChnlDvCd,
        @JsonProperty(value = "SV_BIZ_HCLSF_CD")
        String svBizHclsfCd,
        @JsonProperty(value = "RCPDT")
        String rcpdt,
        @JsonProperty(value = "AS_IST_OJ_NO")
        String asIstOjNo,
        @JsonProperty(value = "SV_BIZ_DCLSF_CD")
        String svBizDclsfCd,
        @JsonProperty(value = "SV_BIZ_DCLSF_NM")
        String svBizDclsfNm,
        @JsonProperty(value = "CNSL_MO_CN")
        String cnslMoCn,
        @JsonProperty(value = "CLTN_YN")
        String cltnYn,
        @JsonProperty(value = "VST_CNFM_DT")
        String vstCnfmDt,
        @JsonProperty(value = "VST_CNFM_M")
        String vstCnfmM,
        @JsonProperty(value = "VST_EXP_DT")
        String vstExpDt,
        @JsonProperty(value = "VST_EXP_M")
        String vstExpM,
        @JsonProperty(value = "CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty(value = "MEXNO_ENCR")
        @DBEncField
        @DBDecField
        String mexnoEncr,
        @JsonProperty(value = "CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty(value = "LOCARA_TNO")
        String locaraTno,
        @JsonProperty(value = "EXNO_ENCR")
        String exnoEncr,
        @JsonProperty(value = "IDV_TNO")
        String idvTno,
        @JsonProperty(value = "NEW_ADR_ZIP")
        String newAdrZip,
        @JsonProperty(value = "RNADR")
        String rnadr,
        @JsonProperty(value = "RDADR")
        String rdadr,
        @JsonProperty(value = "ADR_DV_CD")
        String adrDvCd
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchUsingProductReq")
    public record SearchUsingProductsReq(
        @NotBlank
        @JsonProperty(value = "CNTR_NO")
        String cntrNo
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchUsingProductsRes")
    public record SearchUsingProductsRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_CST_NO")
        String cntrCstNo,
        @JsonProperty(value = "RCGVP_KNM")
        String rcgvpKnm,
        @JsonProperty(value = "PDCT_PD_CD")
        String pdctPdCd,
        @JsonProperty(value = "PDCT_PD_NM")
        String pdctPdNm,
        @JsonProperty(value = "BASE_PD_CD")
        String basePdCd,
        @JsonProperty(value = "BASE_PD_NM")
        String basePdNm,
        @JsonProperty(value = "BC_NO")
        String bcNo,
        @JsonProperty(value = "CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty(value = "MEXNO_ENCR")
        String mexnoEncr,
        @JsonProperty(value = "CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty(value = "ZIP")
        String zip,
        @JsonProperty(value = "ADR")
        String adr
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchServiceHistoryReq")
    public record SearchServiceHistoryReq(
        @NotBlank
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @NotNull
        @Min(1L)
        @JsonProperty(value = "PAGE_INDEX")
        Integer pageIndex,
        @NotNull
        @Min(10L)
        @JsonProperty(value = "PAGE_SIZE")
        Integer pageSize
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchServiceHistoryRes")
    public record SearchServiceHistoryRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        String cntrSn,
        @JsonProperty(value = "CST_SV_ASN_NO")
        String cstSvAsnNo,
        @JsonProperty(value = "AS_IST_OJ_NO")
        String asIstOjNo,
        @JsonProperty(value = "WK_EXCN_DT")
        String wkExcnDt,
        @JsonProperty(value = "WK_PRTNR_KNM")
        String wkPrtnrKnm,
        @JsonProperty(value = "RGST_DT")
        String rgstDt,
        @JsonProperty(value = "RCPDT")
        String rcpdt,
        @JsonProperty(value = "RCP_HH")
        String rcpHh,
        @JsonProperty(value = "PD_CD")
        String pdCd,
        @JsonProperty(value = "PD_NM")
        String pdNm,
        @JsonProperty(value = "SV_PROCS_CN")
        String svProcsCn,
        @JsonProperty(value = "CNSL_MO_CN")
        String cnslMoCn,
        @JsonProperty(value = "SV_BIZ_HCLSF_CD")
        String svBizHclsfCd,
        @JsonProperty(value = "SV_BIZ_HCLSF_NM")
        String svBizHclsfNm,
        @JsonProperty(value = "SV_BIZ_DCLSF_CD")
        String svBizDclsfCd,
        @JsonProperty(value = "SV_BIZ_DCLSF_NM")
        String svBizDclsfNm,
        @JsonProperty(value = "IST_NMN_N")
        String istNmnN
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchServiceContentsReq")
    public record SearchServiceContentsReq(
        @NotBlank
        @JsonProperty(value = "CST_SV_ASN_NO")
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchServiceContentsRes")
    public record SearchServiceContentsRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        String cntrSn,
        @JsonProperty(value = "CST_SV_ASN_NO")
        String cstSvAsnNo,
        @JsonProperty(value = "AS_IST_OJ_NO")
        String asIstOjNo,
        @JsonProperty(value = "WK_EXCN_DT")
        String wkExcnDt,
        @JsonProperty(value = "WK_PRTNR_KNM")
        String wkPrtnrKnm,
        @JsonProperty(value = "RGST_DT")
        String rgstDt,
        @JsonProperty(value = "RCPDT")
        String rcpdt,
        @JsonProperty(value = "RCP_HH")
        String rcpHh,
        @JsonProperty(value = "PD_CD")
        String pdCd,
        @JsonProperty(value = "PD_NM")
        String pdNm,
        @JsonProperty(value = "SV_PROCS_CN")
        String svProcsCn,
        @JsonProperty(value = "CNSL_MO_CN")
        String cnslMoCn,
        @JsonProperty(value = "SV_BIZ_HCLSF_CD")
        String svBizHclsfCd,
        @JsonProperty(value = "SV_BIZ_HCLSF_NM")
        String svBizHclsfNm,
        @JsonProperty(value = "SV_BIZ_DCLSF_CD")
        String svBizDclsfCd,
        @JsonProperty(value = "SV_BIZ_DCLSF_NM")
        String svBizDclsfNm
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
        String rcpDtStart, // 접수일자시작
        String rcpDtEnd, // 접수일자종료
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
        String apyDt, /*적용일자*/
        String rcpDtStart,
        String rcpDtEnd
    ) {}

}
