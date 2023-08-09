package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class SalesControlItemizationDto {
    @ApiModel("SalesControlItemizationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, //계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, //계약일련번호
        @NotBlank
        @JsonProperty("INQR_YM")
        String inqrYm //조회년월
    ) {}

    @ApiModel("SalesControlItemizationDto-SearchRes")
    public record SearchRes(

        @JsonProperty("END_NM")
        String endNm, //시작년월
        @JsonProperty("RS_CD_NM")
        String rsCdNm, //종료년월
        @JsonProperty("SL_CTR_AMT")
        String slCtrAmt, //매출조정금액
        @JsonProperty("SL_CTR_DV_CD_NM")
        String slCtrDvCdNm, //매출조정구분코드명
        @JsonProperty("SL_CTR_TP_CD_NM")
        String slCtrTpCdNm, //매출조정유형명
        @JsonProperty("SL_CTR_RSON_NM")
        String slCtrRsonNm, //매출조정사유명
        @JsonProperty("RGST_DT")
        String rgstDt, //등록일자
        @JsonProperty("RGR_NM")
        String rgrNm, //등록자명
        @JsonProperty("RGR_ID")
        String rgrId //등록자ID
    ) {}
}
