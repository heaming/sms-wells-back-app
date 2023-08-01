package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

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
        String cntrSn // 계약일련번호
    ) {}

    /*
        wells 렌탈/멤버십 선납 정보 조회 Result Dto
     */
    @ApiModel("WwdbRentalMembershipPrepaymentInterfaceDto-SearchInfoRes")
    public record SearchInfoRes(
        @JsonProperty("NMN")
        String rentalTn, // 차월
        @JsonProperty("PRM_MCNT")
        String prmMcn, // 선납개월
        @JsonProperty("PRM_DSCR")
        String prmDscr, // 선납시 할인율
        @JsonProperty("PRM_AMT")
        String prmAmt, // 선납금액 (선납매출금액)
        @JsonProperty("PRM_PTRM_STRTDT")
        String prmStrtYm, // 선납기간_from
        @JsonProperty("PRM_PTRM_ENDDT")
        String prmEndYm, // 선납기간_to
        @JsonProperty("FMN_PR_AMT")
        String mmPrmDpAmt, // 한달_선납액 (선납입금금액)
        @JsonProperty("FMN_DSC_AMT")
        String mmPrmDscAmt, // 한달_할인_금액 (선납할인금액)
        @JsonProperty("PRPD_AMT")
        String prpdAmt, // 선수금액 (기말선수금)
        @JsonProperty("UC_AMT")
        String ucAmt, // 미수금액 (기말미수금액)
        @JsonProperty("PY_AMT")
        String pyAmt // 납입금액
    ) {}

    /*
        wells 렌탈/멤버십 선납 예정정보 조회 Request Dto
     */
    @ApiModel("WwdbRentalMembershipPrepaymentInterfaceDto-SearchExpectedInfoReq")
    public record SearchExpectedInfoReq(
        @NotBlank
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, // 구분(일시불/렌탈/멤버십/회사/정기배송), 판매유형코드
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @NotBlank
        @JsonProperty("PRM_FROM_YM")
        String prmFromYm, // 선납기간년월from
        @NotBlank
        @JsonProperty("PRM_TO_YM")
        String prmToYm // 선납기간년월to
    ) {}

    /*
        wells 렌탈/멤버십 선납 예정정보 조회 Result Dto
     */
    @ApiModel("WwdbRentalMembershipPrepaymentInterfaceDto-SearchExpectedInfoRes")
    public record SearchExpectedInfoRes(
        @JsonProperty("RCNT")
        String rcnt, // 차월
        @JsonProperty("TOT_PRPM")
        String totPrpm, //총선납개월 (선납개월수?)
        @JsonProperty("DSC_RAT")
        String dscRat, //선납시_할인율 (선납할인율?)
        @JsonProperty("DSC_MM")
        String dscMm, // 할인_대상_선납개월
        @JsonProperty("DSC_AMT")
        String dscAmt, //할인_대상_선납금액 (선납할인금액?)
        @JsonProperty("NO_DSC_MM")
        String noDscMm, // 미할인_대상_선납개월
        @JsonProperty("NO_DSC_AMT")
        String noDscAmt, // 미할인_대상_선납금액,
        @JsonProperty("PRM_FROM_YM")
        String prmFromYm, //선납기간from
        @JsonProperty("PRM_TO_YM")
        String prmToYm, //선납기간to
        @JsonProperty("MM_AMT")
        String mmAmt, // 월납입금액, //(렌탈로/멤버십비용등)
        @JsonProperty("MM_DSC_AMT")
        String mmDscAmt, // 한달_할인금액 (할인금액?)
        @JsonProperty("ATAM")
        String atam, // 선수금액 (당월선수금입금금액?)
        @JsonProperty("UC_AMT")
        String ucAmt, // 미수금액 (원금미수금액+이자미수금액?)
        @JsonProperty("DP_AMT")
        String dpAmt // 납입금액
    ) {}
}
