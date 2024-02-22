package com.kyowon.sms.wells.web.service.allocate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WsncAsInterfaceDto {

    @ApiModel(value = "WsncAsInterfaceDto-SearchCustInfoReq")
    public record SearchCustInfoReq(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("CST_KNM")
        String cstKnm,
        @JsonProperty("PD_GRP_ID")
        String pdGrpId,
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO_ENCR")
        String mexnoEncr,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("ADR_ID")
        String adrId
    ) {}

    @ApiModel(value = "WsncAsInterfaceDto-SearchCustInfoRes")
    public record SearchCustInfoRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CST_NO")
        String cstNo,
        @JsonProperty(value = "CST_KNM")
        String cstKnm,
        @JsonProperty(value = "CNTR_CNFM_DTM")
        String cntrCnfmDtm,
        @JsonProperty(value = "PD_CD")
        String pdCd,
        @JsonProperty(value = "PD_NM")
        String pdNm,
        @JsonProperty(value = "CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty(value = "MEXNO_ENCR")
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
        String adrDvCd,
        @JsonProperty(value = "LTN_ADR")
        String ltnAdr,
        @JsonProperty(value = "RCGVP_KNM")
        String rcgvpKnm,
        @JsonProperty(value = "BRYY_FMMDD")
        String bryyMmdd,
        @JsonProperty(value = "SEX_DV_CD")
        String sexDvCd,
        @JsonProperty(value = "CNTR_CST_NO")
        String cntrCstNo,
        @JsonProperty(value = "ITEM_NM")
        String itemNm,
        @JsonProperty(value = "ITEM_DESC")
        String itemDesc
    ) {
        public SearchCustInfoRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

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
    ) {
        public SearchRecInfoRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            exnoEncr = DbEncUtil.dec(exnoEncr);
        }
    }

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
        String adr,
        @JsonProperty(value = "BC_PBL_DV_CD")
        String bcPblDvCd
    ) {
        public SearchUsingProductsRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    @ApiModel(value = "WsncAsInterfaceDto-SearchServiceHistoryReq")
    public record SearchServiceHistoryReq(
        @NotBlank
        @JsonProperty(value = "CNTR_NO")
        String cntrNo
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
        String svBizDclsfNm,
        @JsonProperty(value = "ITEM_NM")
        String itemNm,
        @JsonProperty(value = "ITEM_DESC")
        String itemDesc
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustomerInformationReq")
    public record SearchCustomerInformationReq(

        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotNull
        @JsonProperty("CNTR_SN")
        String cntrSn
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustomerInformationRes")
    public record SearchCustomerInformationRes(

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
        @JsonProperty(value = "RCP_DT_START")
        String rcpDtStart, // 접수일자시작
        @JsonProperty(value = "RCP_DT_END")
        String rcpDtEnd, // 접수일자종료
        @JsonProperty(value = "PD_CD")
        String pdCd, // 상품코드
        @JsonProperty(value = "CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty(value = "BC_NO")
        String bcNo, // 바코드번호
        @JsonProperty("PAGE_INDEX")
        int pageIndex,
        @JsonProperty("PAGE_SIZE")
        int pageSize
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchAsSidingChangeRes")
    public record SearchAsSidingChangeRes(
        @JsonProperty(value = "TP_CD")
        String tpCd, /*유형코드*/
        @JsonProperty(value = "RCPDT")
        String rcpdt, /*접수일자*/
        @JsonProperty(value = "CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty(value = "PD_CD")
        String pdCd, /*상품코드*/
        @JsonProperty(value = "PD_NM")
        String pdNm, /*상품명*/
        @JsonProperty(value = "BC_NO")
        String bcNo, /*바코드번호*/
        @JsonProperty(value = "WK_TP")
        String wkTp, /*작업유형*/
        @JsonProperty(value = "AS_RSON")
        String asRson, /*AS사유*/
        @JsonProperty(value = "VST_RQDT")
        String vstRqdt, /*방문요청일자*/
        @JsonProperty(value = "WK_EXCN_DT")
        String wkExcnDt, /*작업수행일자*/
        @JsonProperty(value = "WK_PRGS_STAT")
        String wkPrgsStat, /*작업진행상태*/
        @JsonProperty(value = "PEXT_SDING")
        String pextSding, /*기존모종*/
        @JsonProperty(value = "CH_SDING")
        String chSding, /*변경모종*/
        @JsonProperty(value = "APY_DT")
        String apyDt /*적용일자*/
    ) {}

}
