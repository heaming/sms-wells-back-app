package com.kyowon.sms.wells.web.closing.etcPerformances.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdccEtcPerformanceInterfaceDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불외_실적정보 Result Dto
    @ApiModel("WdccEtcPerformanceInterfaceDto-FindReq")
    public record FindReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        int cntrSn,
        @JsonProperty("CNTRT_NM")
        String cntrtNm,
        @JsonProperty("SL_CL_YM1")
        String slClYm1,
        @JsonProperty("SL_CL_YM2")
        String slClYm2
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells_일시불외_실적정보 Result Dto
    @ApiModel("WdccEtcPerformanceInterfaceDto-FindRes")
    public record FindRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("SL_CL_YM")
        String slClYm,
        @JsonProperty("RENTAL_TN")
        String rentalTn,
        @JsonProperty("SL_STP_YN")
        String slStpYn,
        @JsonProperty("THM_SL_SUM_AMT")
        String thmSlSumAmt,
        @JsonProperty("SL_DP_AGG_AMT")
        String slDpAggAmt,
        @JsonProperty("ACU_OC_ATAM")
        String acuOcAtam,
        @JsonProperty("EOT_ATAM")
        String eotAtam,
        @JsonProperty("EOT_DLQ_AMT")
        String eotDlqAmt,
        @JsonProperty("EOT_DLQ_ADD_AMT")
        String eotDlqAddAmt,
        @JsonProperty("SL_STP_AMT")
        String slStpAmt,
        @JsonProperty("CLCTAM_DV_CD")
        String clctamDvCd,
        @JsonProperty("CLCTAM_DV_CD_NM")
        String clctamDvCdNm,
        @JsonProperty("PRTNR_NO")
        String prtnrNo,
        @JsonProperty("PRTNR_FNM")
        String prtnrFnm
    ) {
    }
}
