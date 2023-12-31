package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WwdbRentalMembershipPrepaymentInterfaceDto {

    /*
        wells 렌탈/멤버십 선납 정보 조회 Request Dto
     */
    @ApiModel("WwdbRentalMembershipPrepaymentInterfaceDto-SearchInfoReq")
    public record SearchInfoReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("PRM_MCN")
        String prmMcn // 선납개월수
    ) {}

    /*
        wells 렌탈/멤버십 선납 정보 조회 Result Dto
     */
    @ApiModel("WwdbRentalMembershipPrepaymentInterfaceDto-SearchInfoRes")
    public record SearchInfoRes(
        @JsonProperty("NMN")
        String nmn, // 차월
        @JsonProperty("PRM_MCNT")
        BigDecimal prmMcnt, // 선납개월
        @JsonProperty("PRM_DSCR")
        BigDecimal prmDscr, // 선납시 할인율
        @JsonProperty("PRM_AMT")
        BigDecimal prmAmt, // 선납금액 (선납매출금액)
        @JsonProperty("PRM_PTRM_STRTDT")
        String prmPtrmStrtdt, // 선납기간_from
        @JsonProperty("PRM_PTRM_ENDDT")
        String prmPtrmEnddt, // 선납기간_to
        @JsonProperty("FMN_PR_AMT")
        BigDecimal fmnPrAmt, // 한달_선납액 (선납입금금액)
        @JsonProperty("FMN_DSC_AMT")
        BigDecimal fmnDscAmt, // 한달_할인_금액 (선납할인금액)
        @JsonProperty("PRPD_AMT")
        BigDecimal prpdAmt, // 선수금액 (기말선수금)
        @JsonProperty("UC_AMT")
        BigDecimal ucAmt, // 미수금액 (기말미수금액)
        @JsonProperty("PY_AMT")
        BigDecimal pyAmt // 납입금액
    ) {}

    /*
        wells 렌탈/멤버십 선납 예정정보 조회 Request Dto
     */
    @ApiModel("WwdbRentalMembershipPrepaymentInterfaceDto-SearchExpectedInfoReq")
    public record SearchExpectedInfoReq(

        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @NotBlank
        @JsonProperty("RVE_DV_CD")
        String sellTpCd, // 구분(일시불/렌탈/멤버십/회사/정기배송), 판매유형코드
        @NotBlank
        @JsonProperty("PRM_PTRM_STRTDT")
        String prmPtrmStrtdt, // 선납기간시작일
        @NotBlank
        @JsonProperty("PRM_PTRM_ENDDT")
        String prmPtrmEnddt // 선납기간종료일
    ) {}

    /*
        wells 렌탈/멤버십 선납 예정정보 조회 Result Dto
     */
    @ApiModel("WwdbRentalMembershipPrepaymentInterfaceDto-SearchExpectedInfoRes")
    public record SearchExpectedInfoRes(
        @JsonProperty("NMN")
        String nmn, // 차월
        @JsonProperty("TOT_PRM_MCNT")
        BigDecimal totPrmMcnt, //총선납개월 (선납개월수?)
        @JsonProperty("DSC_OBJ_PRM_MCNT")
        BigDecimal dscObjPrmMcnt, // 할인대상선납개월
        @JsonProperty("PRM_DSCR")
        BigDecimal prmDscr, //선납시_할인율 (선납할인율?)
        @JsonProperty("DSC_OBJ_PRM_AMT")
        BigDecimal dscObjPrmAmt, //할인_대상_선납금액 (선납할인금액?)
        @JsonProperty("NON_DSC_OBJ_PRM_MCNT")
        BigDecimal nonDscObjPrmMcnt, // 미할인_대상_선납개월
        @JsonProperty("NON_DSC_OBJ_PRM_AMT")
        BigDecimal nonDscObjPrmAmt, // 미할인_대상_선납금액,
        @JsonProperty("PRM_PTRM_STRTDT")
        String prmPtrmStrtdt, //선납기간from
        @JsonProperty("PRM_PTRM_ENDDT")
        String prmPtrmEnddt, //선납기간to
        @JsonProperty("FMN_AMT")
        BigDecimal fmnAmt, // 월납입금액, //(렌탈로/멤버십비용등)
        @JsonProperty("FMN_DSC_AMT")
        BigDecimal fmnDscAmt, // 한달_할인금액 (할인금액?)
        @JsonProperty("PRPD_AMT")
        BigDecimal prpdAmt, // 선수금액 (당월선수금입금금액?)
        @JsonProperty("UC_AMT")
        BigDecimal ucAmt, // 미수금액 (원금미수금액+이자미수금액?)
        @JsonProperty("PY_AMT")
        BigDecimal pyAmt // 납입금액
    ) {}
}
