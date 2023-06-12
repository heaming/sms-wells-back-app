package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

public class WsniFalseVisitRgstInterfaceDto {
    @ApiModel(value = "WsniFalseVisitRgstInterfaceDto-SaveReq")
    public record SaveReq(
        @JsonProperty(value = "CST_SV_ASN_NO_2")
        String cstSvAsnNo2,
        @JsonProperty(value = "CUST_NM")
        String custNm,
        @JsonProperty(value = "SELL_TP_CD_NM")
        String sellTpCdNm,
        @JsonProperty(value = "PD_CD")
        String pdCd,
        @JsonProperty(value = "JOB_CD")
        String jobCd,
        @JsonProperty(value = "SV_BIZ_HCLSF_CD")
        String svBizHclsfCd,
        @JsonProperty(value = "SV_BIZ_MCLSF_CD")
        String svBizMclsfCd,
        @JsonProperty(value = "SV_BIZ_LCLSF_CD")
        String svBizLclsfCd,
        @JsonProperty(value = "SV_BIZ_DCLSF_CD")
        String svBizDclsfCd,
        @JsonProperty(value = "ARR_DT")
        String arrDt,
        @JsonProperty(value = "RECD_DT")
        String recdDt,
        @JsonProperty(value = "REG_DT")
        String regDt,
        @JsonProperty(value = "WRK_DT")
        String wrkDt,
        @JsonProperty(value = "OG_CD")
        String ogCd,
        @JsonProperty(value = "PRTNR_OG_TP_CD")
        String prtnrOgTpCd,
        @JsonProperty(value = "PRTNR_NO")
        String prtnrNo,
        @JsonProperty(value = "CFRM_DT_TM")
        String cfrmDtTm,
        @JsonProperty(value = "PART_USE_YN")
        String partUseYn,
        @JsonProperty(value = "FILTER_CD_1")
        String filterCd1,
        @JsonProperty(value = "FILTER_CD_2")
        String filterCd2,
        @JsonProperty(value = "FILTER_CD_3")
        String filterCd3,
        @JsonProperty(value = "FILTER_CD_4")
        String filterCd4,
        @JsonProperty(value = "FILTER_CD_5")
        String filterCd5,
        @JsonProperty(value = "FILTER_CD_6")
        String filterCd6,
        @JsonProperty(value = "STOP_DT")
        String stopDt,
        @JsonProperty(value = "AS_MTHS")
        Integer asMths,
        @JsonProperty(value = "BS_MTHS")
        Integer bsMths,
        @JsonProperty(value = "DTA_DL_YN")
        String dtaDlYn,
        @JsonProperty(value = "FST_RGST_DTM")
        String fstRgstDtm,
        @JsonProperty(value = "FST_RGST_USR_ID")
        String fstRgstUsrId,
        @JsonProperty(value = "FST_RGST_PRG_ID")
        String fstRgstPrgId,
        @JsonProperty(value = "FST_RGST_DEPT_ID")
        String fstRgstDeptId,
        @JsonProperty(value = "FNL_MDFC_DTM")
        String fnlMdfcDtm,
        @JsonProperty(value = "FNL_MDFC_USR_ID")
        String fnlMdfcUsrId,
        @JsonProperty(value = "FNL_MDFC_PRG_ID")
        String fnlMdfcPrgId,
        @JsonProperty(value = "FNL_MDFC_DEPT_ID")
        String fnlMdfcDeptId
    ) {

    }

    @ApiModel(value = "WsniFalseVisitRgstInterfaceDto-SaveRes")
    public record SaveRes(
        @JsonProperty(value = "RESULT")
        String result
    ) {

    }
}
