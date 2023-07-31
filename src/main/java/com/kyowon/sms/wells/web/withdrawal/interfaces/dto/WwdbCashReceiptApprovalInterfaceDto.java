package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.system.config.annotation.DBDecField;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WwdbCashReceiptApprovalInterfaceDto {

    /* wells 현금영수증 승인 내역 조회 Request Dto */
    @ApiModel("WwdbCashReceiptApprovalInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @NotBlank
        @JsonProperty("CSSR_APRNO")
        String cssrAprno,
        @NotBlank
        @JsonProperty("RVE_YM")
        String rveYm
    ) {}

    /* wells 현금영수증 승인 내역 조회 Result Dto */
    @ApiModel("WwdbCashReceiptApprovalInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("RVE_DT")
        String rveDt,
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, // 구분(일시불/렌탈/멤버십/회사/정기배송 등)
        @JsonProperty("SELL_TP_NM")
        String sellTpNm, // 구분명
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("CST_NO")
        String cstNo, // 고객번호
        @JsonProperty("CST_NM")
        String cstNm, // 고객명
        @JsonProperty("CSSR_IS_DV_CD")
        String cssrIsDvCd, // 신분확인구분(카드/사업자번호/휴대전화..)
        @JsonProperty("CSSR_IS_DV_NM")
        String cssrIsDvNm, // 신분확인구분(카드/사업자번호/휴대전화..)
        @JsonProperty("CSSR_IS_NO")
        @DBDecField
        String cssrIsNoEncr, // 신분확인(카드번호/사업자번호/휴대전화번호)
        @JsonProperty("CSSR_APRNO")
        String cssrAprno, // 승인번호
        @JsonProperty("CSSR_APR_YN")
        String cssrAprYn, // 승인여부
        @JsonProperty("CSSR_AMT")
        String cssrAmt, // 금액
        @JsonProperty("CSSR_APR_TAMT")
        String cssrAprTamt, // 승인합계
        @JsonProperty("CSSR_APR_DV_CD")
        String cssrAprDvCd, // 구분(정상/오류)
        @JsonProperty("CSSR_APR_DV_NM")
        String cssrAprDvNm // 구분(정상/오류)

    ) {}

}
