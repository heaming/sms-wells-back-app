package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WwdbVirtualAccountInterfaceDto {

    /*
        wells 가상계좌 발급 내역 조회 Request Dto
     */
    @ApiModel("WwdbVirtualAccountInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("KW_GRP_CO_CD")
        String kwGrpCoCd, //교원그룹코드
        @JsonProperty("VAC_NO")
        String vacNo, //가상계좌번호
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn // 계약일련번호
    ) {}

    /*
        wells 가상계좌 발급 내역 조회 Result Dto
     */
    @ApiModel("WwdbVirtualAccountInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("KW_GRP_CO_CD_NM")
        String kwGrpCoCdNm, // 교원 그룹 회사 코드
        @JsonProperty("RVE_AK_NO")
        String rveAkNo, // 계약번호
        @JsonProperty("RVE_AK_SN")
        String rveAkSn, // 계약일련번호
        @JsonProperty("VAC_BNK_NM")
        String vacBnkNm, // 은행명
        @JsonProperty("VAC_NO")
        String vacNo, // 가상계좌번호
        @JsonProperty("VAC_DPR_NM")
        String vacDprNm, // 입금자명
        @JsonProperty("EDI_PD_DV_CD_NM")
        String ediPdDvCdNm, // 상품구분코드
        @JsonProperty("SELL_TP_CD_NM")
        String sellTpCdNm // 판매유형명, 구분(할부/렌탈/멤버십/회사/정기배송/필터)
    ) {}

    /*
        wells 가상계좌 발급 Request Dto
     */
    @ApiModel("WwdbVirtualAccountInterfaceDto-SaveReq")
    public record SaveReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @NotBlank
        @JsonProperty("FNIT_CD")
        String fnitCd, //금융기관코드
        @NotBlank
        @JsonProperty("DP_AMT")
        String dpAmt //입금금액
    ) {}

    /*
        wells 가상계좌 발급 Result Dto
     */
    @ApiModel("WwdbVirtualAccountInterfaceDto-SaveRes")
    @Builder
    public record SaveRes(
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("FNIT_CD")
        String fnitCd, // 금융기관코드
        @JsonProperty("FNIT_NM")
        String fnitNm, // 금융기관명
        @JsonProperty("VAC_NO")
        String vacNo // 가상계좌번호
    ) {}
}
