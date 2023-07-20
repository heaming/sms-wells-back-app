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
        @JsonProperty("RVE_DV_CD")
        String rveDvCd, // 수납구분코드: 구분(계약금, 렌탈/할부금/멤버십 등 정확하게는 모름)
        @JsonProperty("RVE_DV_NM")
        String rveDvNm, // 수납구분명
        @JsonProperty("RVE_DT")
        String rveDt, // 수납일자
        @JsonProperty("PERF_DT")
        String perfDt, // 실적일자
        @JsonProperty("RFND_FSH_DT")
        String rfndFshDt, // 환불일자
        @JsonProperty("DP_AMT")
        String dpAmt, // 금액
        @JsonProperty("RVE_PH_CD")
        String rvePhCd, // 수납경로코드: 입금유형(영업/가상 등)
        @JsonProperty("RVE_PH_NM")
        String rvePhNm,
        @JsonProperty("DP_TP_CD")
        String dpTpCd, // 처리구분(비씨카드, 삼성카드, 가상계좌 등)
        @JsonProperty("DP_TP_NM")
        String dpTpNm,
        @JsonProperty("FNIT_CD")
        String fnitCd, // 금융기관코드
        @JsonProperty("FNIT_NM")
        String fnitNm,
        @JsonProperty("CNO")
        String cno, // 카드/계좌번호
        @JsonProperty("ISTM_MCN")
        String istmMcn, // 할부개월
        @JsonProperty("PRM_YN")
        String prmYn, // 선납유무
        @JsonProperty("HDWR_YN")
        String hdwrYn, // HDWR_YN
        @JsonProperty("LOCK_YN")
        String lockYn // 자료보관 TODO: 확인필요

    ) {
        public SearchRes {
            cno = StringUtils.isNotEmpty(cno) ? DbEncUtil.dec(cno) : cno; //복호화
        }
    }

}
