package com.kyowon.sms.wells.web.service.interfaces.dto;

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
        String CUST_NM,
        String IN_GB,
        String IN_GB_NM,
        String SELL_TP_CD,
        String SELL_TP_CD_NM,
        String CNTR_NO,
        Integer CNTR_SN,
        String PD_CD,
        String PD_CD_NM,
        String GDS_GR,
        String GUBUN,
        String SV_BIZ_HCLSF_CD,
        String SV_BIZ_HCLSF_CD_NM,
        String SV_BIZ_MCLSF_CD,
        String SV_BIZ_MCLSF_CD_NM,
        String SV_BIZ_LCLSF_CD,
        String SV_BIZ_LCLSF_CD_NM,
        String SV_BIZ_DCLSF_CD,
        String SV_BIZ_DCLSF_CD_NM,
        String REG_ID,
        String REG_KNM,
        String REG_MEMO,
        String VST_SCH_DT_TM,
        String RECD_DT_TM,
        String ACC_DT_TM,
        String ARR_DT_TM,
        String CFRM_DT_TM,
        String WRK_DT_TM,
        String PRTNR_OG_TP_CD,
        String PRTNR_NO,
        String PRTNR_KNM,
        String TEL_NO,
        String OG_CD,
        String OG_NAME,
        String JURISDICTION_OG_NM,
        String CHAG_GB,
        String RET_YN,
        String BC_NO,
        String PROC_TXT,
        String CANC_NM,
        String CANC_MEMO,
        String PART_USE_YN,
        String FILTER_CD_1,
        String FILTER_CD_2,
        String FILTER_CD_3,
        String FILTER_CD_4,
        String FILTER_CD_5,
        String FILTER_CD_6,
        String FILTER_CD_1_NM,
        String FILTER_CD_2_NM,
        String FILTER_CD_3_NM,
        String FILTER_CD_4_NM,
        String FILTER_CD_5_NM,
        String FILTER_CD_6_NM,
        String PART_CD_1,
        String PART_CD_2,
        String PART_CD_3,
        String CHULGO_C,
        String CHULGO_C_YN,
        String CHULGO_U,
        String CHULGO_U_YN,
        Integer REQ_AMT_TOT,
        Integer ACC_TOT,
        String STOP_DT,
        String RCP_STAT_CD,
        String RCP_STAT_CD_NM,
        String CLTN_DT,
        Integer AS_MTHS,
        Integer BS_MTHS,
        String IN_DLV_COMP,
        String IN_INVOICE,
        String OUT_DLV_COMP,
        String OUT_INVOICE,
        String CST_SV_ASN_NO_1,
        String CST_SV_ASN_NO_2,
        Integer FS_VST_CNT
    ) {}
}
