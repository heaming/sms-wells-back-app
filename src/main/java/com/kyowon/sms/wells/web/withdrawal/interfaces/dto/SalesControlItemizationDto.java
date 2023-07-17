package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class SalesControlItemizationDto {
    @ApiModel("SalesControlItemizationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @NotBlank
        @JsonProperty("INQR_YM")
        String inqrYm
    ) {}

    @ApiModel("SalesControlItemizationDto-SearchRes")
    public record SearchRes(

        @JsonProperty("END_NM")
        String endNm,
        @JsonProperty("RS_CD_NM")
        String rsCdNm,
        @JsonProperty("SL_CTR_AMT")
        String slCtrAmt,
        @JsonProperty("SL_CTR_DV_CD_NM")
        String slCtrDvCdNm,
        @JsonProperty("SL_CTR_TP_CD_NM")
        String slCtrTpCdNm,
        @JsonProperty("SL_CTR_RSON_NM")
        String slCtrRsonNm,
        @JsonProperty("RGST_DT")
        String rgstDt,
        @JsonProperty("RGR_NM")
        String rgrNm,
        @JsonProperty("RGR_ID")
        String rgrId
    ) {}
}
