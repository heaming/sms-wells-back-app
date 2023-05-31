package com.kyowon.sms.wells.web.service.interfaces.converter;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchRes;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniServiceHistoryInterfaceDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WsniServiceHistoryInterfaceConverter {
    @Mapping(target = "CUST_NM", source = "custNm")
    @Mapping(target = "IN_GB", source = "inGb")
    @Mapping(target = "IN_GB_NM", source = "inGbNm")
    @Mapping(target = "SELL_TP_CD", source = "sellTpCd")
    @Mapping(target = "SELL_TP_CD_NM", source = "sellTpCdNm")
    @Mapping(target = "CNTR_NO", source = "cntrNo")
    @Mapping(target = "CNTR_SN", source = "cntrSn")
    @Mapping(target = "PD_CD", source = "pdCd")
    @Mapping(target = "PD_CD_NM", source = "pdCdNm")
    @Mapping(target = "GDS_GR", source = "gdsGr")
    @Mapping(target = "GUBUN", source = "gubun")
    @Mapping(target = "SV_BIZ_HCLSF_CD", source = "svBizHclsfCd")
    @Mapping(target = "SV_BIZ_HCLSF_CD_NM", source = "svBizHclsfCdNm")
    @Mapping(target = "SV_BIZ_MCLSF_CD", source = "svBizMclsfCd")
    @Mapping(target = "SV_BIZ_MCLSF_CD_NM", source = "svBizMclsfCdNm")
    @Mapping(target = "SV_BIZ_LCLSF_CD", source = "svBizLclsfCd")
    @Mapping(target = "SV_BIZ_LCLSF_CD_NM", source = "svBizLclsfCdNm")
    @Mapping(target = "SV_BIZ_DCLSF_CD", source = "svBizDclsfCd")
    @Mapping(target = "SV_BIZ_DCLSF_CD_NM", source = "svBizDclsfCdNm")
    @Mapping(target = "REG_ID", source = "regId")
    @Mapping(target = "REG_KNM", source = "regKnm")
    @Mapping(target = "REG_MEMO", source = "regMemo")
    @Mapping(target = "VST_SCH_DT_TM", source = "vstSchDtTm")
    @Mapping(target = "RECD_DT_TM", source = "recdDtTm")
    @Mapping(target = "ACC_DT_TM", source = "accDtTm")
    @Mapping(target = "ARR_DT_TM", source = "arrDtTm")
    @Mapping(target = "CFRM_DT_TM", source = "cfrmDtTm")
    @Mapping(target = "WRK_DT_TM", source = "wrkDtTm")
    @Mapping(target = "PRTNR_OG_TP_CD", source = "prtnrOgTpCd")
    @Mapping(target = "PRTNR_NO", source = "prtnrNo")
    @Mapping(target = "PRTNR_KNM", source = "prtnrKnm")
    @Mapping(target = "TEL_NO", expression = "java((StringUtils.isNotEmpty(dvo.getTelNo1()) ? dvo.getTelNo1() : \"\") + (StringUtils.isNotEmpty(dvo.getTelNo1()) && StringUtils.isNotEmpty(dvo.getTelNo2()) ? \"-\" : \"\") + (StringUtils.isNotEmpty(dvo.getTelNo2()) ? dvo.getTelNo2() : \"\") + (StringUtils.isNotEmpty(dvo.getTelNo2()) && StringUtils.isNotEmpty(dvo.getTelNo3()) ? \"-\" : \"\") + (StringUtils.isNotEmpty(dvo.getTelNo3()) ? dvo.getTelNo3() : \"\"))")
    @Mapping(target = "OG_CD", source = "ogCd")
    @Mapping(target = "OG_NAME", source = "ogName")
    @Mapping(target = "JURISDICTION_OG_NM", source = "jurisdictionOgNm")
    @Mapping(target = "CHAG_GB", source = "chagGb")
    @Mapping(target = "RET_YN", source = "retYn")
    @Mapping(target = "BC_NO", source = "bcNo")
    @Mapping(target = "PROC_TXT", source = "procTxt")
    @Mapping(target = "CANC_NM", source = "cancNm")
    @Mapping(target = "CANC_MEMO", source = "cancMemo")
    @Mapping(target = "PART_USE_YN", source = "partUseYn")
    @Mapping(target = "FILTER_CD_1", source = "filterCd1")
    @Mapping(target = "FILTER_CD_2", source = "filterCd2")
    @Mapping(target = "FILTER_CD_3", source = "filterCd3")
    @Mapping(target = "FILTER_CD_4", source = "filterCd4")
    @Mapping(target = "FILTER_CD_5", source = "filterCd5")
    @Mapping(target = "FILTER_CD_6", source = "filterCd6")
    @Mapping(target = "FILTER_CD_1_NM", source = "filterCd1Nm")
    @Mapping(target = "FILTER_CD_2_NM", source = "filterCd2Nm")
    @Mapping(target = "FILTER_CD_3_NM", source = "filterCd3Nm")
    @Mapping(target = "FILTER_CD_4_NM", source = "filterCd4Nm")
    @Mapping(target = "FILTER_CD_5_NM", source = "filterCd5Nm")
    @Mapping(target = "FILTER_CD_6_NM", source = "filterCd6Nm")
    @Mapping(target = "PART_CD_1", source = "partCd1")
    @Mapping(target = "PART_CD_2", source = "partCd2")
    @Mapping(target = "PART_CD_3", source = "partCd3")
    @Mapping(target = "CHULGO_C", source = "chulgoC")
    @Mapping(target = "CHULGO_C_YN", source = "chulgoCYn")
    @Mapping(target = "CHULGO_U", source = "chulgoU")
    @Mapping(target = "CHULGO_U_YN", source = "chulgoUYn")
    @Mapping(target = "REQ_AMT_TOT", source = "reqAmtTot")
    @Mapping(target = "ACC_TOT", source = "accTot")
    @Mapping(target = "STOP_DT", source = "stopDt")
    @Mapping(target = "RCP_STAT_CD", source = "rcpStatCd")
    @Mapping(target = "RCP_STAT_CD_NM", source = "rcpStatCdNm")
    @Mapping(target = "CLTN_DT", source = "cltnDt")
    @Mapping(target = "AS_MTHS", source = "asMths")
    @Mapping(target = "BS_MTHS", source = "bsMths")
    @Mapping(target = "IN_DLV_COMP", source = "inDlvComp")
    @Mapping(target = "IN_INVOICE", source = "inInvoice")
    @Mapping(target = "OUT_DLV_COMP", source = "outDlvComp")
    @Mapping(target = "OUT_INVOICE", source = "outInvoice")
    @Mapping(target = "CST_SV_ASN_NO_1", source = "cstSvAsnNo1")
    @Mapping(target = "CST_SV_ASN_NO_2", source = "cstSvAsnNo2")
    @Mapping(target = "FS_VST_CNT", source = "fsVstCnt")
    SearchRes mapServiceHistoryInterfaceDvoToSearchRes(WsniServiceHistoryInterfaceDvo dvo);

    List<SearchRes> mapAllServiceHistoryInterfaceDvoToSearchRes(
        List<WsniServiceHistoryInterfaceDvo> dvos
    );
}
