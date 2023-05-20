package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 단장 수당 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfedLedrAllowanceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 단장 수당 관리 Search Request Dto
    @ApiModel(value = "WfedLeaderAllowanceMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        String rsbDvCd,
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 단장 수당 관리 Search Result Dto
    @ApiModel(value = "WfedLeaderAllowanceMgtDto-SearchIndividualRes")
    public record SearchIndividualRes(
        String dsbYm, /*지급년월*/
        String hmnrscEmpno, /*사번*/
        String ogLevlDvNm, /*조직레벨구분명*/
        String ogNm, /*조직명*/
        String hgrOgCd, /*상위조직코드*/
        String ogCd, /*조직코드*/
        String prtnrKnm, /*성명*/
        Integer brchCt, /*지점건수*/
        Integer basAwAmt, /*기본수당금액*/
        Integer hhExcpAwAmt, /*시간외수당금액 */
        Integer rsbAwAmt, /*직책수당금액*/
        Integer fxnAwSumAmt, /*고정수당합계금액*/
        Integer trgCt, /*실적목표건수*/
        Integer perfCt, /*실적건수*/
        Integer perfAchvRat, /*실적달성비율*/
        Integer trgAchvAwAmt, /*목표달성수당금액*/
        Integer brchAvPerfCt, /*지점평균실적건수(2021년02월이전)*/
        Integer encrgAwAmt, /*장려수당금액(2021년02월이후)*/
        String mngrPerfGdCd, /*관리자실적등급코드(2021년02월이전)*/
        Integer ogAwAmt, /*조직수당금액(2021년02월이후)*/
        Integer evlAwAmt, /*평가수당금액*/
        Integer perfAwSumAmt, /*실적수당합계금액*/
        Integer exclDivAwAmt, /*우수사업부수당금액*/
        Integer ictAwAmt, /*인센티브수당금액*/
        Integer ldstcAtdcAwAmt, /*장거리출근수당금액*/
        Integer mrntSpptAwAmt, /*월세지원수당금액*/
        Integer lectrAdnAwAmt, /*강의부가수당금액*/
        Integer etcAdnAwAmt, /*기타부가수당금액*/
        Integer etcAdnAwSumAmt, /*기타부가수당합계금액*/
        Integer fxnPerfAwSumAmt, /*고정실적수당합계금액(고정급+업적수당)*/
        Integer awCalcSumAmt, /*수당계산합계금액*/
        Integer hinsrDdctam, /*건강보험공제금액*/
        Integer nrsnInsrDdctam, /*요양보험공제금액*/
        Integer ntnlPnsnDdctam, /*국민연금공제금액*/
        Integer einsrDdctam, /*고용보험공제금액*/
        Integer erntx, /*소득세*/
        Integer rsdntx, /*주민세*/
        Integer crptBznsDdctam, /*비정도영업조치금*/
        Integer eddtnAmt, /*기타공제금액*/
        Integer ddtnSumAmt, /*공제합계금액*/
        Integer acpyAmt /*실지급액*/
    ) {}

    @ApiModel(value = "WfedLeaderAllowanceMgtDto-SearchSumRes")
    public record SearchSumRes(
        String dsbYm, /*지급년월*/
        String hmnrscEmpnoCnt, /*인원수*/
        String ogLevlDvNm, /*조직레벨구분명*/
        Integer basAwAmt, /*기본수당금액*/
        Integer hhExcpAwAmt, /*시간외수당금액 */
        Integer rsbAwAmt, /*직책수당금액*/
        Integer fxnAwSumAmt, /*고정수당합계금액*/
        Integer trgCt, /*실적목표건수*/
        Integer perfCt, /*실적건수*/
        Integer perfAchvRat, /*실적달성비율*/
        Integer trgAchvAwAmt, /*목표달성수당금액*/
        Integer brchAvPerfCt, /*지점평균실적건수(2021년02월이전)*/
        Integer encrgAwAmt, /*장려수당금액(2021년02월이후)*/
        String mngrPerfGdCd, /*관리자실적등급코드(2021년02월이전)*/
        Integer ogAwAmt, /*조직수당금액(2021년02월이후)*/
        Integer evlAwAmt, /*평가수당금액*/
        Integer perfAwSumAmt, /*실적수당합계금액*/
        Integer exclDivAwAmt, /*우수사업부수당금액*/
        Integer ictAwAmt, /*인센티브수당금액*/
        Integer ldstcAtdcAwAmt, /*장거리출근수당금액*/
        Integer mrntSpptAwAmt, /*월세지원수당금액*/
        Integer lectrAdnAwAmt, /*강의부가수당금액*/
        Integer etcAdnAwAmt, /*기타부가수당금액*/
        Integer etcAdnAwSumAmt, /*기타부가수당합계금액*/
        Integer fxnPerfAwSumAmt, /*고정실적수당합계금액(고정급+업적수당)*/
        Integer awCalcSumAmt, /*수당계산합계금액*/
        Integer hinsrDdctam, /*건강보험공제금액*/
        Integer nrsnInsrDdctam, /*요양보험공제금액*/
        Integer ntnlPnsnDdctam, /*국민연금공제금액*/
        Integer einsrDdctam, /*고용보험공제금액*/
        Integer erntx, /*소득세*/
        Integer rsdntx, /*주민세*/
        Integer crptBznsDdctam, /*비정도영업조치금*/
        Integer eddtnAmt, /*기타공제금액*/
        Integer ddtnSumAmt, /*공제합계금액*/
        Integer acpyAmt /*실지급액*/
    ) {}

}
