package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 개인별 수수료 관리 (홈마스터)
 * </pre>
 *
 * @author LEE SUNHO
 * @since 2023.10.17
 */
public class WfedIndividualFeeHomeMasterMgtDto {

    @ApiModel(value = "WfedIndividualFeeHomeMasterMgtDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}

    @ApiModel(value = "WfedIndividualFeeHomeMasterMgtDto-FindHmstEntrpRes")
    public record FindHmstEntrpRes(
        String emplNm, /* 성명 */
        String blg, /* 소속 */
        String rsb, /* 직책 */
        String cwMm, /* 차월 */
        String prtnrGdCd, /* 급수 */
        String asetEducPtcpDc, /* 집합교육참여일수 */
        String acpnEducPtcpDc, /* 동행교육참여일수 */
        String cntrYm, /* 업무등록월 */
        String sellAckmtCt, /* 총판매건수 */
        String svCt, /* 서비스건수 */
        String svProcRt, /* 서비스처리율 */
        String hcrMsh, /* 홈케어멤버십 */
        String mattRtlfe, /* 매트리스 렌탈료 */
        String mattExcpRtlfe, /* 매트리스 외 렌탈료 */
        String envrElhmPerfAmt, /* 환경가전 일시불 */
        String envrElhmExcpPerfAmt, /* 환경가전 외 일시불 */
        String intbsAmt, /* 과표계 */
        String ddctam, /* 공제계 */
        String dsbOjAmt /* 실지급액 */
    ) {}

    @ApiModel(value = "WfedIndividualFeeHomeMasterMgtDto-SearchHmstFeeRes")
    public record SearchHmstFeeRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3,
        String item4,
        String fval4
    ) {}

    @ApiModel(value = "WfedIndividualFeeHomeMasterMgtDto-FindHmstDeductionRes")
    public record FindHmstDeductionRes(
        String erntx,
        String rsdntx,
        String rds,
        String hirInsr,
        String inddInsr,
        String buDdtn,
        String pnpyam,
        String ddtnSum

    ) {}

    @ApiModel(value = "WfedIndividualFeeHomeMasterMgtDto-SearchHmstControlRes")
    public record SearchHmstControlRes(
        String div,
        String item,
        String ctrBf,
        String ctrAf,
        String rsn,
        String ctrDtm,
        String ctrr
    ) {}
}
