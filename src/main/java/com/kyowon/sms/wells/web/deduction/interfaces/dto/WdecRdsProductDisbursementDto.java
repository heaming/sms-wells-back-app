package com.kyowon.sms.wells.web.deduction.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdecRdsProductDisbursementDto {

    @ApiModel("WdecRdsDisbursementDto-SaveReq")
    public record SaveReq(

        @JsonProperty("OG_TP_CD")
        String ogTpCd, /*조직유형코드*/

        @JsonProperty("CO_CD")
        String coCd, /*회사코드*/

        @JsonProperty("RDS_DSB_REF_ID")
        String rdsDsbRefId, /*RDS지급참조ID*/

        @JsonProperty("AK_OG_TP_CD")
        String akOgTpCd, /*요청조직유형코드*/

        @JsonProperty("PRTNR_NO")
        String prtnrNo, /*파트너번호*/

        @JsonProperty("BLNG_YM")
        String blngYm, /*귀속년월*/

        @JsonProperty("RDS_RV_USE_DT")
        String rdsRvUseDt, /*RDS적립사용일자*/

        @JsonProperty("RDS_AMT")
        Integer rdsAmt, /*RDS금액*/

        @JsonProperty("RDS_EARNR_DV_CD")
        String rdsEarnrDvCd, /*RDS소득자구분코드*/

        @JsonProperty("RDS_RV_USE_TP_CD")
        String rdsRvUseTpCd, /*RDS적립사용유형코드*/

        @JsonProperty("RDS_SMRY_CD")
        String rdsSmryCd, /*RDS적요코드*/

        @JsonProperty("RDS_SMRY_CN")
        String rdsSmryCn /*RDS적요내용*/

    ) {}

    @ApiModel("WdecRdsDisbursementDto-SaveRes")
    @Builder
    public record SaveRes(

        @JsonProperty("RDS_DSB_REF_ID")
        String rdsDsbRefId, /*RDS지급참조ID*/

        @JsonProperty("RS_CD")
        String rsCd, /*결과코드*/

        @JsonProperty("RS_MSG")
        String rsMsg /*결과메세지*/
    ) {}

    @ApiModel("WdecRdsDisbursementDto-FindRes")
    public record FindRes(
        String count /*레코드카운트*/

    ) {}

}
