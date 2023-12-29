package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.annotation.DBDecField;

import io.swagger.annotations.ApiModel;

public class WsniCustomerCenterInterfaceDto {
    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CST_SV_ASN_NO")
        String cstSvAsnNo,
        @JsonProperty("WK_PRTNR_NO")
        String wkPrtnrNo,
        @JsonProperty("WK_DT_FROM") /** 2023.11.06(월) 작업 시작일자 수정 홍세기 **/
        String wkDtFrom,
        @JsonProperty("WK_DT_TO") /** 2023.11.06(월) 작업 종료일자 수정 홍세기 **/
        String wkDtTo,
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("SV_BIZ_DCLSF_CD")
        String svBizDclsfCd,
        @JsonProperty("WK_DT")
        String wkDt
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchContactRes")
    public record SearchContactRes(
        @JsonProperty("CROW")
        String crow,
        @JsonProperty("TROW")
        String trow,
        @JsonProperty("FMT_CNTC_DT")
        String fmtCntcDt,
        @JsonProperty("FMT_CNTC_HH")
        String fmtCntcHh,
        @JsonProperty("ABSNC_RSON_CD")
        String absncRsonCd,
        @JsonProperty("ABSNC_RSON_NM")
        String absncRsonNm,
        @JsonProperty("CST_SV_ASN_NO")
        String cstSvAsnNo,
        @JsonProperty("CNTC_DT")
        String cntcDt,
        @JsonProperty("CNTC_HH")
        String cntcHh,
        @JsonProperty("PRTNR_NO")
        String prtnrNo,
        @JsonProperty("PRTNR_KNM")
        String prtnrKnm
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchPromChRes")
    public record SearchPromChRes(
        @JsonProperty("VST_RQDT")
        String vstRqdt,
        @JsonProperty("VST_AK_HH")
        String vstAkHh,
        @JsonProperty("PROM_DT")
        String promDt,
        @JsonProperty("PROM_HH")
        String promHh,
        @JsonProperty("OG_NM")
        String ogNm,
        @JsonProperty("WK_PRTNR_NO")
        String wkPrtnrNo,
        @JsonProperty("WK_PRTNR_KNM")
        String wkPrtnrKnm,
        @JsonProperty("PROM_CH_RSON_CN")
        String promChRsonCn,

        @JsonProperty("FST_RGST_DTM")
        String fstRgstDtm
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchCancelRes")
    public record SearchCancelRes(
        @JsonProperty("AS_AK_ID")
        String asAkId,
        @JsonProperty("SYS_DV_CD")
        String sysDvCd,
        @JsonProperty("SV_BIZ_HCLSF_CD")
        String svBizHclsfCd,
        @JsonProperty("SV_BIZ_HCLSF_CD_NM")
        String svBizHclsfCdNm,
        @JsonProperty("SV_BIZ_DCLSF_CD")
        String svBizDclsfCd,
        @JsonProperty("SV_BIZ_DCLSF_CD_NM")
        String svBizDclsfCdNm,
        @JsonProperty("AS_IST_OJ_NO")
        String asIstOjNo,
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("SELL_TP_CD")
        String sellTpCd,
        @JsonProperty("SELL_TP_CD_NM")
        String sellTpCdNm,
        @JsonProperty("FST_RGST_USR_ID")
        String fstRgstUsrId,
        @JsonProperty("FST_RGST_USR_NM")
        String fstRgstUsrNm,
        @JsonProperty("MTR_STAT_CD")
        String mtrStatCd,
        @JsonProperty("MTR_STAT_CD_NM")
        String mtrStatCdNm,
        @JsonProperty("WK_EXCN_DT")
        String wkExcnDt,
        @JsonProperty("WK_EXCN_HH")
        String wkExcnHh,
        @JsonProperty("WK_PRGS_STAT_CD")
        String wkPrgsStatCd,
        @JsonProperty("WK_PRGS_STAT_CD_NM")
        String wkPrgsStatCdNm,
        @JsonProperty("WK_CAN_RSON_CD")
        String wkCanRsonCd,
        @JsonProperty("WK_CAN_MO_CN")
        String wkCanMoCn,
        @JsonProperty("URGT_YN")
        String urgtYn,
        @JsonProperty("VST_RQDT")
        String vstRqdt,
        @JsonProperty("VST_AK_HH")
        String vstAkHh,
        @JsonProperty("CNSL_TP_HCLSF_CD")
        String cnslTpHclsfCd,
        @JsonProperty("CNSL_TP_HCLSF_CD_NM")
        String cnslTpHclsfCdNm,
        @JsonProperty("CNSL_TP_MCLSF_CD")
        String cnslTpMclsfCd,
        @JsonProperty("CNSL_TP_MCLSF_CD_NM")
        String cnslTpMclsfCdNm,
        @JsonProperty("CNSL_TP_LCLSF_CD")
        String cnslTpLclsfCd,
        @JsonProperty("CNSL_TP_LCLSF_CD_NM")
        String cnslTpLclsfCdNm,
        @JsonProperty("CNSL_DTLP_TP_CD")
        String cnslDtlpTpCd,
        @JsonProperty("SMS_FW_YN")
        String smsFwYn,
        @JsonProperty("DP_DV_CD")
        String dpDvCd,
        @JsonProperty("DP_DV_CD_NM")
        String dpDvCdNm,
        @JsonProperty("SV_ET_AMT")
        String svEtAmt,
        @JsonProperty("SV_CNR_OG_ID")
        String svCnrOgId,
        @JsonProperty("SV_CNR_OG_NM")
        String svCnrOgNm,
        @JsonProperty("WK_PRTNR_NO")
        String wkPrtnrNo,
        @JsonProperty("WK_PRTNR_KNM")
        String wkPrtnrKnm
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchSppPdctRes")
    public record SearchSppPdctRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("SV_BIZ_HCLSF_CD")
        String svBizHclsfCd,
        @JsonProperty("SV_BIZ_HCLSF_NM")
        String svBizHclsfNm,
        @JsonProperty("SDING_SPP_NO")
        String sdingSppNo,
        // @JsonProperty("SPP_SN")
        // String sppSn,
        @JsonProperty("SDING_PD_CD1")
        String sdingPdCd1,
        @JsonProperty("SDING_PD_NM1")
        String sdingPdNm1,
        @JsonProperty("SDING_QTY1")
        String sdingQty1,
        @JsonProperty("SDING_PD_CD2")
        String sdingPdCd2,
        @JsonProperty("SDING_PD_NM2")
        String sdingPdNm2,
        @JsonProperty("SDING_QTY2")
        String sdingQty2,
        @JsonProperty("SDING_PD_CD3")
        String sdingPdCd3,
        @JsonProperty("SDING_PD_NM3")
        String sdingPdNm3,
        @JsonProperty("SDING_QTY3")
        String sdingQty3,
        @JsonProperty("SDING_PD_CD4")
        String sdingPdCd4,
        @JsonProperty("SDING_PD_NM4")
        String sdingPdNm4,
        @JsonProperty("SDING_QTY4")
        String sdingQty4,
        @JsonProperty("SDING_PD_CD5")
        String sdingPdCd5,
        @JsonProperty("SDING_PD_NM5")
        String sdingPdNm5,
        @JsonProperty("SDING_QTY5")
        String sdingQty5,
        @JsonProperty("WK_DT")
        String wkDt,
        @JsonProperty("BSSVC_SPP_STP_DT")
        String bssvcSppStpDt
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchSppVstRes")
    public record SearchSppVstRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("VST_DUEDT")
        String vstDuedt,
        @JsonProperty("CH_VST_CL_DT")
        String chVstClDt,
        @JsonProperty("STP_DUEDT")
        String stpDuedt,
        @JsonProperty("STP_CL_DT")
        String stpClDt,
        @JsonProperty("LAST_STP_DT")
        String lastStpDt,
        @JsonProperty("GDC_CD")
        String gdcCd,
        @JsonProperty("GDC_NM")
        String gdcNm,
        @JsonProperty("SALE_CD")
        String saleCd
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchAsRes")
    public record SearchAsRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("WK_GRP_CD1")
        String wkGrpCd1,
        @JsonProperty("WK_GRP_NM1")
        String wkGrpNm1
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-CreateShpadrReq")
    public record CreateShpadrReq(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("SPP_TCNT")
        String sppTcnt,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO_ENCR")
        String mexnoEncr,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO_ENCR")
        String exnoEncr,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("SPP_ZIP")
        String sppZip,
        @JsonProperty("SPP_BAS_ADR")
        String sppBasAdr,
        @JsonProperty("SPP_DTL_ADR")
        String sppDtlAdr,
        @JsonProperty("REF_ADR")
        String refAdr,
        @JsonProperty("ADR_DV_CD")
        String adrDvCd,
        @JsonProperty("SPP_DPTU_DT")
        String sppDptuDt,
        @JsonProperty("SPP_FSH_DT")
        String sppFshDt,
        @JsonProperty("USE_YN")
        String useYn
    ) {
        public CreateShpadrReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
            exnoEncr = DbEncUtil.enc(exnoEncr);
        }
    }

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-CreateShpadrRes")
    public record CreateShpadrRes(
        @JsonProperty("RESULT_MESSAGE")
        String resultMessage,
        @JsonProperty("RESULT_CODE")
        String resultCode
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-EditShpadrReq")
    public record EditShpadrReq(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("SPP_FSH_DT")
        String sppFshDt
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-EditShpadrRes")
    public record EditShpadrRes(
        @JsonProperty("MSG")
        String msg,
        @JsonProperty("RESULT")
        String result
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-FindAdnInfReq")
    public record FindAdnInfReq(
        @NotBlank
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @NotNull
        @JsonProperty(value = "CNTR_SN")
        String cntrSn
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-FindAdnInfRes")
    public record FindAdnInfRes(
        @JsonProperty("IST_LCT_DTL_CN")
        String istLctDtlCn,
        @JsonProperty("ALL_CLEAN_MSG")
        String allCleanMsg,
        @JsonProperty("ALL_CLEAN_YN")
        String allCleanYn,
        @JsonProperty("CHANGE_COUNT")
        String changeCount,
        @JsonProperty("DTL_CNTR_NO")
        String dtlCntrNo,
        @JsonProperty("DTL_CNTR_SN")
        String dtlCntrSn,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO_ENCR")
        String mexnoEncr,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO_ENCR")
        String exnoEncr,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("NEW_ADR_ZIP")
        String newAdrZip,
        @JsonProperty("RNADR")
        String rnadr,
        @JsonProperty("RDADR")
        String rdadr,
        @JsonProperty("BS_STOP_YN")
        String bsStopYn,
        @JsonProperty("CHANGE_YN")
        String changeYn,
        @JsonProperty("CHANGE_POSSIBLE_YN")
        String changePossibleYn
    ) {
        public FindAdnInfRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            exnoEncr = DbEncUtil.dec(exnoEncr);
        }
    }

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchPkgChRes")
    public record SearchPkgChRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("AK_SN")
        String akSn,
        @JsonProperty("RGST_DT")
        String rgstDt,
        @JsonProperty("AS_AK_DV_CD")
        String asAkDvCd,
        @JsonProperty("AS_AK_DV_NM")
        String asAkDvNm,
        @JsonProperty("AK_CHDT")
        String akChdt,
        @JsonProperty("BFCH_PD_CD")
        String bfchPdCd,
        @JsonProperty("BFCH_PD_NM")
        String bfchPdNm,
        @JsonProperty("AFCH_PD_CD")
        String afchPdCd,
        @JsonProperty("AFCH_PD_NM")
        String afchPdNm,
        @JsonProperty("PRTNR_KNM")
        String prtnrKnm,
        @JsonProperty("PART_INFO")
        String partInfo,
        @JsonProperty("MTR_PROCS_STAT_CD")
        String mtrProcsStatCd,
        @JsonProperty("MTR_PROCS_STAT_NM")
        String mtrProcsStatNm
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchfiltShpadrReq")
    public record SearchFiltShpadrReq(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchfiltShpadrRes")
    public record SearchFiltShpadrRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("SPP_TCNT")
        String sppTcnt,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO_ENCR")
        @DBDecField
        String mexnoEncr,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO_ENCR")
        String exnoEncr,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("SPP_ZIP")
        String sppZip,
        @JsonProperty("SPP_BAS_ADR")
        String sppBasAdr,
        @JsonProperty("SPP_DTL_ADR")
        String sppDtlAdr,
        @JsonProperty("REF_ADR")
        String refAdr,
        @JsonProperty("ADR_DV_CD")
        String adrDvCd,
        @JsonProperty("ADR_DV_NM")
        String adrDvNm,
        @JsonProperty("SPP_DPTU_DT")
        String sppDptuDt,
        @JsonProperty("SPP_FSH_DT")
        String sppFshDt,
        @JsonProperty("USE_YN")
        String useYn,
        @JsonProperty("POPUP_YN")
        String popupYn
    ) {
        public SearchFiltShpadrRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            exnoEncr = DbEncUtil.dec(exnoEncr);
        }
    }
}
