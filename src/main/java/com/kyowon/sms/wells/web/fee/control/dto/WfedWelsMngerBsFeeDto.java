package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * WM 방문실적 수수료관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.03
 */
public class WfedWelsMngerBsFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WfedWelsMngerBsFeeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}

    @ApiModel(value = "WfedWelsMngerBsFeeDto-SaveFeeReq")
    public record SaveFeeReq(
        String prtnrNo,
        String baseYm,
        String ogTpCd,
        String feeNm,
        String feeCd,
        String feeCalcAmt,
        String feeCtrCnfmAmt,
        String feeCtrRsonCn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 개인별 수수료 관리 Search Result Dto

    @ApiModel(value = "WfedWelsMngerBsFeeDto-FindHumanRes")
    public record FindHumanRes(
        String thmBranch,
        String taskRgs,
        String stmntNmn,
        String thmCrlv,
        String wmOpng,
        String wmCltn
    ) {}

    @ApiModel(value = "WfedWelsMngerBsFeeDto-SearchVisitRes")
    public record SearchVisitRes(
        String cdNm,
        String cnt1,
        String cnt2,
        String amt1,
        String cnt3,
        String cnt4,
        String amt2,
        String sumAmt
    ) {}
    @ApiModel(value = "WfedWelsMngerBsFeeDto-SearchFeeRes")
    public record SearchFeeRes(
        String prtnrNo,
        String baseYm,
        String ogTpCd,
        String feeNm,
        String feeCd,
        String feeCalcAmt,
        String feeCtrCnfmAmt,
        String feeCtrRsonCn
    ) {}

    @ApiModel(value = "WfedWelsMngerBsFeeDto-FindVisitAgrgRes")
    public record FindVisitAgrgRes(
        String visitRecord,
        String procsRt,
        String pdAccCnt,
        String w1Cnt,
        String w2Cnt,
        String envrCnt
    ) {}
    @ApiModel(value = "WfedWelsMngerBsFeeDto-FindFeeAgrgRes")
    public record FindFeeAgrgRes(
        String ddtnSum,
        String dsbSum,
        String dsbTam
    ) {}
}
