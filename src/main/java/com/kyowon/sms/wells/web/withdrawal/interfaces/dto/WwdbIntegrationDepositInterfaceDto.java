package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

public class WwdbIntegrationDepositInterfaceDto {

    /* 공통 통합입금 내역 회사선택 코드 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchCompanyCodeReq")
    public record SearchCompanyCodeReq(
        @NotBlank
        @JsonProperty("TENANT_ID")
        String tenantId
    ) {}

    /* 공통 통합입금 내역 회사선택 코드 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchCompanyCodeRes")
    public record SearchCompanyCodeRes(
        @JsonProperty("KW_GRP_CO_CD")
        String kwGrpCoCd,
        @JsonProperty("KW_GRP_CO_CD_NM")
        String kwGrpCoCdNm
    ) {}

    /* 공통 통합입금 내역 입금유형 코드 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDepoCodeReq")
    public record SearchDepoCodeReq(
        @NotBlank
        @JsonProperty("TENANT_ID")
        String tenantId
    ) {}

    /* 공통 통합입금 내역 입금유형 코드 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDepoCodeRes")
    public record SearchDepoCodeRes(
        @JsonProperty("DP_TP_CD")
        String dpTpCd,
        @JsonProperty("DP_TP_CD_NM")
        String dpTpCdNm
    ) {}

    /* 공통 통합입금 내역 세부 거래내역 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDetailTradeReq")
    public record SearchDetailTradeReq(
        @NotBlank
        @JsonProperty("ITG_DP_NO")
        String itgDpNo
    ) {}

    /* 공통 통합입금 내역 세부 거래내역 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDetailTradeRes")
    public record SearchDetailTradeRes(
        @JsonProperty("DP_DT")
        String dpDt,           // 거래일자
        @JsonProperty("DP_HMS")
        String dpHms,          // 거래시간
        @JsonProperty("DP_DV_CD")
        String dpDvCd,        // 입금구분코드
        @JsonProperty("DP_DV_NM")
        String dpDvNm,        // 입금구분명
        @JsonProperty("ISTM_MCN")
        String istmMcn,        // 할부개월
        @JsonProperty("CNTR_NO")
        String cntrNo,         // 계약번호
        @JsonProperty("CNTR_DTL_NO")
        String cntrDtlNo,     // 계약상세번호
        @JsonProperty("CST_NM")
        String cstNm,          // 계약자명
        @JsonProperty("DP_AMT")
        String dpAmt,          // 입금금액
        @JsonProperty("DP_CPRCNF_AMT")
        String dpCprcnfAmt,   // 대사금액
        @JsonProperty("CAN_AMT")
        String canAmt,         // 취소금액
        @JsonProperty("RFND_AMT")
        String rfndAmt         // 환불금액
    ) {}

    /* 공통 통합입금 내역 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchItemizationReq")
    public record SearchItemizationReq(
        @JsonProperty("CNO_TP")
        String cnoTp,          // 유형(1계좌/2카드)
        @JsonProperty("CNO")
        String cno,             // 카드/계좌번호(유형이 1이면 계좌번호, 2면 카드번호)
        @JsonProperty("CARD_APRNO")
        String cardAprno,      // 승인번호
        @JsonProperty("RVE_CD")
        String rveCd,          // 수납코드
        @JsonProperty("KW_GRP_CO_CD")
        String kwGrpCoCd,    // 회사코드
        @JsonProperty("DP_BLAM_YN")
        String dpBlamYn,      // 잔액유무
        @JsonProperty("DP_DT_ST")
        String dpDtSt,        // 입금일자 from
        @JsonProperty("DP_DT_ED")
        String dpDtEd,        // 입금일자 to
        @JsonProperty("DP_TP_CD")
        String dpTpCd         // 입금유형(신용카드/가상계좌/회사통장/계좌이체/카드이체/어음/카드-PG/이체-PG/수수료공제/K머니/W머니/W포인트/K멤버스포인트 등)
    ) {
        public SearchItemizationReq {
            cno = StringUtils.isNotEmpty(cno) ? DbEncUtil.enc(cno) : cno; // 암호화
        }
    }

    /* 공통 통합입금 내역 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchItemizationRes")
    public record SearchItemizationRes(
        @JsonProperty("DP_DT")
        String dpDt,           // 입금일자
        @JsonProperty("DP_HMS")
        String dpHms,          // 일금시간
        @JsonProperty("DP_TP_CD")
        String dpTpCd,        // 입금유형
        @JsonProperty("CNO")
        String cno,             // 카드/계좌번호
        @JsonProperty("FNIT_CD")
        String fnitCd,         // 금융기관코드
        @JsonProperty("FNIT_NM")
        String fnitNm,         // 카드/은행명
        @JsonProperty("CARD_APRNO")
        String cardAprno,      // 승인번호
        @JsonProperty("ISTM_MCN")
        String istmMcn,        // 할부개월
        @JsonProperty("DPR_NM")
        String dprNm,          // 예금주/카드주명
        @JsonProperty("DP_AMT")
        String dpAmt,          // 입금금액
        @JsonProperty("DP_CPRCNF_AMT")
        String dpCprcnfAmt,   // 대사금액
        @JsonProperty("CAN_AMT")
        String canAmt,         // 취소금액
        @JsonProperty("RFND_AMT")
        String rfndAmt,        // 환불금액
        @JsonProperty("DP_BLAM")
        String dpBlam,         // 잔액
        @JsonProperty("OVR_AMT")
        String ovrAmt,         // 초과금액
        @JsonProperty("ITG_DP_NO")
        String itgDpNo,       // 통합입금번호
        @JsonProperty("RVE_CD")
        String rveCd,          // 수납코드
        @JsonProperty("RVE_NM")
        String rveNm,          // 수납처(채권-웰스...)
        @JsonProperty("PRTNR_NO")
        String prtnrNo,        // 판매인ID
        @JsonProperty("PRTNR_NM")
        String prtnrNm,        // 판매인명
        @JsonProperty("KW_GRP_CO_CD")
        String kwGrpCoCd,    // 회사코드
        @JsonProperty("KW_GRP_CO_NM")
        String kwGrpCoNm     // 회사명
    ) {
        public SearchItemizationRes {
            cno = StringUtils.isNotEmpty(cno) ? DbEncUtil.dec(cno) : cno; //복호화
        }
    }

}
