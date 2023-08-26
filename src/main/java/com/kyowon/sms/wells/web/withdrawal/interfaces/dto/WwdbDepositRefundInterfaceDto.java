package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WwdbDepositRefundInterfaceDto {

    @ApiModel("WwdbDepositRefundInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn // 계약일련번호
    ) {}

    @ApiModel("WwdbDepositRefundInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("RVE_DV_CD_NM")
        String rveDvCdNm, // 수납구분명
        @JsonProperty("RVE_DT")
        String rveDt, // 수납일자
        @JsonProperty("PERF_DT")
        String perfDt, // 실적일자
        @JsonProperty("RFND_DT")
        String rfndDt, // 환불일자
        @JsonProperty("RVE_AMT")
        Integer rveAmt, // 금액
        @JsonProperty("DP_TP_CD_NM")
        String dpTpCdNm, // 입금유형명
        @JsonProperty("FNIT_CD_NM")
        String fnitCdNm, // 금융기관명
        @JsonProperty("AC_CRCD_NO")
        String acCrcdNo, // 카드/계좌번호
        @JsonProperty("ISTM_MCN")
        Integer istmMcn, // 할부개월
        @JsonProperty("PRM_EYN")
        String prmEyn, // 선납유무
        @JsonProperty("HDWR_YN")
        String hdwrYn, // HDWR_YN
        @JsonProperty("MTR_KEP_EYN")
        String mtrKepEyn // 자료보관 TODO: 확인필요

    ) {
        public SearchRes {
            acCrcdNo = StringUtils.isNotEmpty(acCrcdNo) ? DbEncUtil.dec(acCrcdNo) : acCrcdNo; //복호화
        }
    }

}
