package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdbVirtualAccountDepositInterfaceDto {

    /*
        wells 가상계좌 입금처리 Request Dto
     */
    @ApiModel("WwdbVirtualAccountDepositInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("KW_GRP_CO_CD")
        String kwGrpCoCd, //교원그룹코드
        @NotBlank
        @JsonProperty("ITG_DP_NO")
        String itgDpNo, // 통합입금번호
        @NotBlank
        @JsonProperty("VAC_DV_CD")
        String vacDvCd, // 가상계좌구분코드(STL-세틀뱅크, KICC-KICC)
        @JsonProperty("VAC_IS_ID")
        String vacIsId, // 가상계좌발급ID
        /* 세틀뱅크 업데이트시 필요한 데이터*/
        @JsonProperty("VAC_BUR_CD")
        String vacBurCd, //가상계좌기관코드
        @JsonProperty("VAC_TRD_NO")
        String vacTrdNo, // 가상계좌거래번호
        @JsonProperty("VAC_TRD_DTM")
        String vacTrdDtm, // 가상계좌거래일시
        @JsonProperty("VAC_TRD_KND_CD")
        String vacTrdKndCd, // 가상계좌거래종류코드

        /* KICC 업데이트시 필요한 데이터 */
        @JsonProperty("VAC_NO")
        String vacNo, //가상계좌번호
        @JsonProperty("VAC_ORD_NO")
        String vacOrdNo, // 가상계좌 주문번호
        @JsonProperty("PG_TRD_NO")
        String pgTrdNo, // PG거래번호
        @JsonProperty("APR_DTM")
        String aprDtm // 승인일자
    ) {}

    /*
        wells 가상계좌 입금처리 Result Dto
     */
    @ApiModel("WwdbVirtualAccountDepositInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("PROCS_YN")
        String processYn // 처리여부
    ) {}
}
