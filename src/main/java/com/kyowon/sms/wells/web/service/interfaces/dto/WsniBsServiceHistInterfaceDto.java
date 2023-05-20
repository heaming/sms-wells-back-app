package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

public class WsniBsServiceHistInterfaceDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniBsServiceHistInterfaceDto-SearchReq")
    public record SearchReq(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        int cntrSn,
        @JsonProperty(value = "JOB_DATE_FROM")
        String jobDateFrom,
        @JsonProperty(value = "JOB_DATE_TO")
        String jobDateTo,
        @JsonProperty(value = "PAGE")
        int page,
        @JsonProperty(value = "ROWNUM")
        int rownum
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniBsServiceHistInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty(value = "TC")
        String tc,
        @JsonProperty(value = "RN")
        String rn,
        @JsonProperty(value = "TPG")
        String tpg,
        @JsonProperty(value = "CURPAGE")
        String curpage,
        @JsonProperty(value = "CUST_NM")
        String custNm,
        @JsonProperty(value = "CUST_CD")
        String custCd,
        @JsonProperty(value = "TEL_AR")
        String telAr,
        @JsonProperty(value = "TEL_NO1")
        String telNo1,
        @JsonProperty(value = "TEL_NO2")
        String telNo2,
        @JsonProperty(value = "TEL_CO")
        String telCo,
        @JsonProperty(value = "HP_NO1")
        String hpNo1,
        @JsonProperty(value = "HP_NO2")
        String hpNo2,
        @JsonProperty(value = "JOB_DATE")
        String jobDate,
        @JsonProperty(value = "JOB_GB")
        String jobGb,
        @JsonProperty(value = "JOB_GB2")
        String jobGb2,
        @JsonProperty(value = "PROC_TXT")
        String procTxt,
        @JsonProperty(value = "CST_SV_ASN_NO")
        String cstSvAsnNo,
        @JsonProperty(value = "WRK_GB")
        String wrkGb,
        @JsonProperty(value = "EMP_NM")
        String empNm
    ) {
        public SearchRes {
            telNo1 = (StringUtils.isNotEmpty(telNo1) ? DbEncUtil.dec(telNo1) : telNo1);
            hpNo1 = (StringUtils.isNotEmpty(hpNo1) ? DbEncUtil.dec(hpNo1) : hpNo1);
        }
    }

}
