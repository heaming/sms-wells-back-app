package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

public class WwdbIntegrationDepositInterfaceDto {

    /* 공통 통합입금 내역 회사선택 코드 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchCompanyCodeReq")
    public record SearchCompanyCodeReq(
        @NotBlank
        String TENANT_ID
    ) {}

    /* 공통 통합입금 내역 회사선택 코드 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchCompanyCodeRes")
    public record SearchCompanyCodeRes(
        String KW_GRP_CO_CD,
        String KW_GRP_CO_CD_NM
    ) {}

    /* 공통 통합입금 내역 입금유형 코드 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDepoCodeReq")
    public record SearchDepoCodeReq(
        @NotBlank
        String TENANT_ID
    ) {}

    /* 공통 통합입금 내역 입금유형 코드 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDepoCodeRes")
    public record SearchDepoCodeRes(
        String DP_TP_CD,
        String DP_TP_CD_NM
    ) {}

    /* 공통 통합입금 내역 세부 거래내역 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDetailTradeReq")
    public record SearchDetailTradeReq(
        @NotBlank
        String ITG_DP_NO
    ) {}

    /* 공통 통합입금 내역 세부 거래내역 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchDetailTradeRes")
    public record SearchDetailTradeRes(
        String DP_DT,           // 거래일자
        String DP_HMS,          // 거래시간
        String DP_DV_CD,        // 입금구분코드
        String DP_DV_NM,        // 입금구분명
        String ISTM_MCN,        // 할부개월
        String CNTR_NO,         // 계약번호
        String CNTR_DTL_NO,     // 계약상세번호
        String CST_NM,          // 계약자명
        String DP_AMT,          // 입금금액
        String DP_CPRCNF_AMT,   // 대사금액
        String CAN_AMT,         // 취소금액
        String RFND_AMT         // 환불금액
    ) {}

    /* 공통 통합입금 내역 조회 Request Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchItemizationReq")
    public record SearchItemizationReq(
        String CNO_TP,          // 유형(1계좌/2카드)
        String CNO,             // 카드/계좌번호(유형이 1이면 계좌번호, 2면 카드번호)
        String CARD_APRNO,      // 승인번호
        String RVE_CD,          // 수납코드
        String KW_GRP_CO_CD,    // 회사코드
        String DP_BLAM_YN,      // 잔액유무
        String DP_DT_ST,        // 입금일자 from
        String DP_DT_ED,        // 입금일자 to
        String DP_TP_CD         // 입금유형(신용카드/가상계좌/회사통장/계좌이체/카드이체/어음/카드-PG/이체-PG/수수료공제/K머니/W머니/W포인트/K멤버스포인트 등)
    ) {
        public SearchItemizationReq {
            CNO = StringUtils.isNotEmpty(CNO) ? DbEncUtil.enc(CNO) : CNO; // 암호화
        }
    }

    /* 공통 통합입금 내역 조회 Result Dto */
    @ApiModel("WwdbIntegrationDepositDto-SearchItemizationRes")
    public record SearchItemizationRes(
        String DP_DT,           // 입금일자
        String DP_HMS,          // 일금시간
        String DP_TP_CD,        // 입금유형
        String CNO,             // 카드/계좌번호
        String FNIT_CD,         // 금융기관코드
        String FNIT_NM,         // 카드/은행명
        String CARD_APRNO,      // 승인번호
        String ISTM_MCN,        // 할부개월
        String DPR_NM,          // 예금주/카드주명
        String DP_AMT,          // 입금금액
        String DP_CPRCNF_AMT,   // 대사금액
        String CAN_AMT,         // 취소금액
        String RFND_AMT,        // 환불금액
        String DP_BLAM,         // 잔액
        String OVR_AMT,         // 초과금액
        String ITG_DP_NO,       // 통합입금번호
        String RVE_CD,          // 수납코드
        String RVE_NM,          // 수납처(채권-웰스...)
        String PRTNR_NO,        // 판매인ID
        String PRTNR_NM,        // 판매인명
        String KW_GRP_CO_CD,    // 회사코드
        String KW_GRP_CO_NM     // 회사명
    ) {
        public SearchItemizationRes {
            CNO = StringUtils.isNotEmpty(CNO) ? DbEncUtil.dec(CNO) : CNO; //복호화
        }
    }

}
