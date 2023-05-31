package com.kyowon.sms.wells.web.service.interfaces.dto;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.AutomapConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsniServiceHistoryInterfaceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniServiceHistoryInterfaceDto-SearchReq")
    public record SearchReq(
        String CNTR_NO,
        Integer CNTR_SN,
        String GUBUN,
        String WORK_END_YN,
        String BS_CUR_DT_YN
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniServiceHistoryInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CUST_NM")
        String custNm,
        @JsonProperty("IN_GB")
        String inGb,
        @JsonProperty("IN_GB_NM")
        String inGbNm,
        @JsonProperty("SELL_TP_CD")
        String sellTpCd,
        @JsonProperty("SELL_TP_CD_NM")
        String sellTpCdNm,
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        Integer cntrSn,
        @JsonProperty("PD_CD")
        String pdCd,
        @JsonProperty("PD_CD_NM")
        String pdCdNm,
        @JsonProperty("GDS_GR")
        String gdsGr,
        @JsonProperty("GUBUN")
        String gubun,
        @JsonProperty("SV_BIZ_HCLSF_CD")
        String svBizHclsfCd,
        @JsonProperty("SV_BIZ_HCLSF_CD_NM")
        String svBizHclsfCdNm,
        @JsonProperty("SV_BIZ_MCLSF_CD")
        String svBizMclsfCd,
        @JsonProperty("SV_BIZ_MCLSF_CD_NM")
        String svBizMclsfCdNm,
        @JsonProperty("SV_BIZ_LCLSF_CD")
        String svBizLclsfCd,
        @JsonProperty("SV_BIZ_LCLSF_CD_NM")
        String svBizLclsfCdNm,
        @JsonProperty("SV_BIZ_DCLSF_CD")
        String svBizDclsfCd,
        @JsonProperty("SV_BIZ_DCLSF_CD_NM")
        String svBizDclsfCdNm,
        @JsonProperty("REG_ID")
        String regId,
        @JsonProperty("REG_KNM")
        String regKnm,
        @JsonProperty("REG_MEMO")
        String regMemo,
        @JsonProperty("VST_SCH_DT_TM")
        String vstSchDtTm,
        @JsonProperty("RECD_DT_TM")
        String recdDtTm,
        @JsonProperty("ACC_DT_TM")
        String accDtTm,
        @JsonProperty("ARR_DT_TM")
        String arrDtTm,
        @JsonProperty("CFRM_DT_TM")
        String cfrmDtTm,
        @JsonProperty("WRK_DT_TM")
        String wrkDtTm,
        @JsonProperty("PRTNR_OG_TP_CD")
        String prtnrOgTpCd,
        @JsonProperty("PRTNR_NO")
        String prtnrNo,
        @JsonProperty("PRTNR_KNM")
        String prtnrKnm,
        @JsonProperty("TEL_NO")
        String telNo,
        @JsonProperty("OG_CD")
        String ogCd,
        @JsonProperty("OG_NAME")
        String ogName,
        @JsonProperty("JURISDICTION_OG_NM")
        String jurisdictionOgNm,
        @JsonProperty("CHAG_GB")
        String chagGb,
        @JsonProperty("RET_YN")
        String retYn,
        @JsonProperty("BC_NO")
        String bcNo,
        @JsonProperty("PROC_TXT")
        String procTxt,
        @JsonProperty("CANC_NM")
        String cancNm,
        @JsonProperty("CANC_MEMO")
        String cancMemo,
        @JsonProperty("PART_USE_YN")
        String partUseYn,
        @JsonProperty("FILTER_CD_1")
        String filterCd1,
        @JsonProperty("FILTER_CD_2")
        String filterCd2,
        @JsonProperty("FILTER_CD_3")
        String filterCd3,
        @JsonProperty("FILTER_CD_4")
        String filterCd4,
        @JsonProperty("FILTER_CD_5")
        String filterCd5,
        @JsonProperty("FILTER_CD_6")
        String filterCd6,
        @JsonProperty("FILTER_CD_1_NM")
        String filterCd1Nm,
        @JsonProperty("FILTER_CD_2_NM")
        String filterCd2Nm,
        @JsonProperty("FILTER_CD_3_NM")
        String filterCd3Nm,
        @JsonProperty("FILTER_CD_4_NM")
        String filterCd4Nm,
        @JsonProperty("FILTER_CD_5_NM")
        String filterCd5Nm,
        @JsonProperty("FILTER_CD_6_NM")
        String filterCd6Nm,
        @JsonProperty("PART_CD_1")
        String partCd1,
        @JsonProperty("PART_CD_2")
        String partCd2,
        @JsonProperty("PART_CD_3")
        String partCd3,
        @JsonProperty("CHULGO_C")
        String chulgoC,
        @JsonProperty("CHULGO_C_YN")
        String chulgoCYn,
        @JsonProperty("CHULGO_U")
        String chulgoU,
        @JsonProperty("CHULGO_U_YN")
        String chulgoUYn,
        @JsonProperty("REQ_AMT_TOT")
        Integer reqAmtTot,
        @JsonProperty("ACC_TOT")
        Integer accTot,
        @JsonProperty("STOP_DT")
        String stopDt,
        @JsonProperty("RCP_STAT_CD")
        String rcpStatCd,
        @JsonProperty("RCP_STAT_CD_NM")
        String rcpStatCdNm,
        @JsonProperty("CLTN_DT")
        String cltnDt,
        @JsonProperty("AS_MTHS")
        Integer asMths,
        @JsonProperty("BS_MTHS")
        Integer bsMths,
        @JsonProperty("IN_DLV_COMP")
        String inDlvComp,
        @JsonProperty("IN_INVOICE")
        String inInvoice,
        @JsonProperty("OUT_DLV_COMP")
        String outDlvComp,
        @JsonProperty("OUT_INVOICE")
        String outInvoice,
        @JsonProperty("CST_SV_ASN_NO_1")
        String cstSvAsnNo1,
        @JsonProperty("CST_SV_ASN_NO_2")
        String cstSvAsnNo2,
        @JsonProperty("FS_VST_CNT")
        Integer fsVstCnt
    ) {
        @AutomapConstructor
        public SearchRes(
            String custNm,
            String inGb,
            String inGbNm,
            String sellTpCd,
            String sellTpCdNm,
            String cntrNo,
            Integer cntrSn,
            String pdCd,
            String pdCdNm,
            String gdsGr,
            String gubun,
            String svBizHclsfCd,
            String svBizHclsfCdNm,
            String svBizMclsfCd,
            String svBizMclsfCdNm,
            String svBizLclsfCd,
            String svBizLclsfCdNm,
            String svBizDclsfCd,
            String svBizDclsfCdNm,
            String regId,
            String regKnm,
            String regMemo,
            String vstSchDtTm,
            String recdDtTm,
            String accDtTm,
            String arrDtTm,
            String cfrmDtTm,
            String wrkDtTm,
            String prtnrOgTpCd,
            String prtnrNo,
            String prtnrKnm,
            String telNo1,
            String telNo2,
            String telNo3,
            String ogCd,
            String ogName,
            String jurisdictionOgNm,
            String chagGb,
            String retYn,
            String bcNo,
            String procTxt,
            String cancNm,
            String cancMemo,
            String partUseYn,
            String filterCd1,
            String filterCd2,
            String filterCd3,
            String filterCd4,
            String filterCd5,
            String filterCd6,
            String filterCd1Nm,
            String filterCd2Nm,
            String filterCd3Nm,
            String filterCd4Nm,
            String filterCd5Nm,
            String filterCd6Nm,
            String partCd1,
            String partCd2,
            String partCd3,
            String chulgoC,
            String chulgoCYn,
            String chulgoU,
            String chulgoUYn,
            Integer reqAmtTot,
            Integer accTot,
            String stopDt,
            String rcpStatCd,
            String rcpStatCdNm,
            String cltnDt,
            Integer asMths,
            Integer bsMths,
            String inDlvComp,
            String inInvoice,
            String outDlvComp,
            String outInvoice,
            String cstSvAsnNo1,
            String cstSvAsnNo2,
            Integer fsVstCnt
        ) {
            this(
                custNm,
                inGb,
                inGbNm,
                sellTpCd,
                sellTpCdNm,
                cntrNo,
                cntrSn,
                pdCd,
                pdCdNm,
                gdsGr,
                gubun,
                svBizHclsfCd,
                svBizHclsfCdNm,
                svBizMclsfCd,
                svBizMclsfCdNm,
                svBizLclsfCd,
                svBizLclsfCdNm,
                svBizDclsfCd,
                svBizDclsfCdNm,
                regId,
                regKnm,
                regMemo,
                vstSchDtTm,
                recdDtTm,
                accDtTm,
                arrDtTm,
                cfrmDtTm,
                wrkDtTm,
                prtnrOgTpCd,
                prtnrNo,
                prtnrKnm,
                (StringUtils.isNotEmpty(telNo1) ? telNo1 : "")
                    + (StringUtils.isNotEmpty(telNo1) && StringUtils.isNotEmpty(telNo2) ? "-" : "")
                    + (StringUtils.isNotEmpty(telNo2) ? DbEncUtil.dec(telNo2) : "")
                    + (StringUtils.isNotEmpty(telNo2) && StringUtils.isNotEmpty(telNo3) ? "-" : "")
                    + (StringUtils.isNotEmpty(telNo3) ? telNo3 : ""),
                ogCd,
                ogName,
                jurisdictionOgNm,
                chagGb,
                retYn,
                bcNo,
                procTxt,
                cancNm,
                cancMemo,
                partUseYn,
                filterCd1,
                filterCd2,
                filterCd3,
                filterCd4,
                filterCd5,
                filterCd6,
                filterCd1Nm,
                filterCd2Nm,
                filterCd3Nm,
                filterCd4Nm,
                filterCd5Nm,
                filterCd6Nm,
                partCd1,
                partCd2,
                partCd3,
                chulgoC,
                chulgoCYn,
                chulgoU,
                chulgoUYn,
                reqAmtTot,
                accTot,
                stopDt,
                rcpStatCd,
                rcpStatCdNm,
                cltnDt,
                asMths,
                bsMths,
                inDlvComp,
                inInvoice,
                outDlvComp,
                outInvoice,
                cstSvAsnNo1,
                cstSvAsnNo2,
                fsVstCnt
            );
        }

    }
}
