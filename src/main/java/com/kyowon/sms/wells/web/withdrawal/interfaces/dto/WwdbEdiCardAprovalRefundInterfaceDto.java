package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WwdbEdiCardAprovalRefundInterfaceDto {

    /*
        wells EDI카드 승인/환불 내역 조회 Request Dto
     */
    @ApiModel("WwdbEdiCardAprovalRefundInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CRCDNO_ENCR")
        String crcdnoEncr // 카드번호
    ) {}

    /*
        wells EDI카드 승인/환불 내역 조회 Result Dto
        TODO: 테이블 변경 후 속성 확인 필요
     */
    @ApiModel("WwdbEdiCardAprovalRefundInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("EDI_CARD_TP_NM")
        String ediCardTpNm, // 구분(승인/환불) (EDI카드유형코드)
        @JsonProperty("CNFMDT")
        String cnfmdt, // 승인일자 (확정일자)
        @JsonProperty("SL_CAN_DT")
        String slCanDt, // 취소일자 (매출취소일자)
        @JsonProperty("CARD_AMT")
        String cardAmt, // 금액 (카드금액)
        @JsonProperty("ISTM_MCN")
        String istmMcn, // 할부개월,
        @JsonProperty("CARD_APRNO")
        String cardAprno, // 승인번호
        @JsonProperty("CARD_EXPDT_YM")
        String cardExpdtYm, // 유효기간 (카드유효기간년월)
        @JsonProperty("EDI_RVE_DT")
        String ediRveDt, // 수납일자 (EDI수납일자)
        @JsonProperty("TF_DT")
        String tfDt, //이관일자
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("EDI_PD_DV_CD")
        String ediPdDvCd, //상품명 (EDI상품구분코드)
        @JsonProperty("EDI_DP_TP_CD")
        String ediDpTpCd, // 유형(인수금, 할부금 …) (EDI입금유형코드)
        @JsonProperty("CST_FNM")
        String cstFnm //계약자명(EDI카드내역.고객성명)
    ) {}
}
