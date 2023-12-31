package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WwdbCashReceiptApprovalInterfaceDto {

    /* wells 현금영수증 승인 내역 조회 Request Dto */
    @ApiModel("WwdbCashReceiptApprovalInterfaceDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("APR_NO")
        String aprNo,
        @JsonProperty("STRT_RVE_YM")
        String strtRveYm,
        @JsonProperty("END_RVE_YM")
        String endRveYm

    ) {}

    /* wells 현금영수증 승인 내역 조회 Result Dto */
    @ApiModel("WwdbCashReceiptApprovalInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("RVE_DT")
        String rveDt,
        @JsonProperty("SELL_TP_CD_NM")
        String sellTpCdNm, // 구분명
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("CST_KNM")
        String cstKnm, // 고객명
        @JsonProperty("CSSR_IS_DV_CD_NM")
        String cssrIsDvCdNm, // 신분확인구분(카드/사업자번호/휴대전화..)
        @JsonProperty("CSSR_IS_NO")
        String cssrIsNo, // 신분확인(카드번호/사업자번호/휴대전화번호)
        @JsonProperty("CSSR_APRNO")
        String cssrAprno, // 승인번호
        @JsonProperty("CSSR_YN")
        String cssrYn, // 승인여부
        @JsonProperty("CSSR_TRD_AMT")
        String cssrTrdAmt, // 금액
        @JsonProperty("CSSR_SUM_AMT")
        String cssrSumAmt, // 승인합계
        @JsonProperty("CSSR_APR_DV_CD_NM")
        String cssrAprDvCdNm // 구분(정상/오류)

    ) {
        public SearchRes {
            cssrIsNo = StringUtils.isNotEmpty(cssrIsNo) ? DbEncUtil.dec(cssrIsNo) : cssrIsNo;
        }
    }

}
