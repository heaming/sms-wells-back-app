package com.kyowon.sms.wells.web.closing.sales.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class WdcbSalesInterfaceDto {

    @ApiModel(value = "WdcbSalesInterfaceDto-SearchByContractReq")
    @Builder
    public record SearchByContractReq(

        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,

        @JsonProperty("CNTR_SN")
        int cntrSn
    ) {}

    public record SearchAllianceContractRes(

        @JsonProperty("CNTR_CST_NM")
        String cntrCstNm, // 계약고객명
        @JsonProperty("KLYEAR")
        String klyear, // 라이프계약년도
        @JsonProperty("KLCODE")
        String klcode, // 라이프회원코드
        @JsonProperty("CNTR_AMT")
        BigDecimal cntrAmt, // 계약금액
        @JsonProperty("RENTAL_AMT")
        BigDecimal rentalAmt, // 렌탈금액
        @JsonProperty("RENTAL_AMT2")
        BigDecimal rentalAmt2, // 렌탈금액2
        @JsonProperty("SL_RCOG_DT")
        String slRcogDt, //  매출인식일자
        @JsonProperty("CAN_DT")
        String canDt, // 취소일자
        @JsonProperty("ALNCMP_CD")
        String alncmpCd, // 제휴사코드
        @JsonProperty("SELL_PRTNR_NO")
        String sellPrtnrNo, // 판매파트너번호
        @JsonProperty("SELL_PRTNR_NM")
        String sellPrtnrNm, // 판매파트너명
        @JsonProperty("SELL_PRTNR_OG_CD")
        String sellPrtnrOgCd, // 판매파트너조직코드
        @JsonProperty("RES_PNT")
        BigDecimal resPnt, // 잔여포인트
        @JsonProperty("MM_RENTAL_AMT")
        BigDecimal mmRentalAmt, // 월렌탈금액
        @JsonProperty("PNT_RVE_AMT")
        BigDecimal pntRveAmt // 포인트수납금액
    ) {}
}
