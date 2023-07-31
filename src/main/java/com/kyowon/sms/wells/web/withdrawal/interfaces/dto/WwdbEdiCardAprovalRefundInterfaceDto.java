package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WwdbEdiCardAprovalRefundInterfaceDto {

    /*
        wells EDI카드 승인/환불 내역 조회 Request Dto
     */
    @ApiModel("WwdbEdiCardAprovalRefundInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CRCD_NO")
        String crcdnoEncr // 카드번호
    ) {
        public SearchReq {
            crcdnoEncr = StringUtils.isNotEmpty(crcdnoEncr) ? DbEncUtil.enc(crcdnoEncr) : crcdnoEncr; // 암호화
        }
    }

    /*
        wells EDI카드 승인/환불 내역 조회 Result Dto
     */
    @ApiModel("WwdbEdiCardAprovalRefundInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("EDI_CARD_TP_NM")
        String ediCardTpNm, // 구분(승인/환불) (EDI카드유형코드)
        @JsonProperty("CNFMDT")
        String ediCardAgrgPerfDt, // 승인일자 (확정일자)
        @JsonProperty("SL_CAN_DT")
        String crdcdSlCanDt, // 취소일자 (매출취소일자)
        @JsonProperty("CARD_AMT")
        String crdcdTrdAmt, // 금액 (카드금액)
        @JsonProperty("ISTM_MCN")
        String crdcdIstmMcn, // 할부개월,
        @JsonProperty("CARD_APRNO")
        String crdcdAprno, // 승인번호
        @JsonProperty("CARD_EXPDT_YM")
        String crdcdExpdtYm, // 유효기간 (카드유효기간년월)
        @JsonProperty("EDI_RVE_DT")
        String ediRveDt, // 수납일자 (EDI수납일자)
        @JsonProperty("TF_DT")
        String ediCardAgrgTfDt, //이관일자
        @JsonProperty("CNTR_NO")
        String CntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("EDI_PD_DV_CD_NM")
        String ediPdDvNm, //상품명 (EDI상품구분코드)
        @JsonProperty("EDI_DP_TP_CD_NM")
        String ediDpTpCd, // 유형(인수금, 할부금 …) (EDI입금유형코드)
        @JsonProperty("CST_FNM")
        String cntrCstNm //계약자명(EDI카드내역.고객성명)
    ) {}
}
