package com.kyowon.sms.wells.web.fee.simulation.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfefEstimateFeeMgtDto {
    public record SearchOgPReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String perType,
        @NotBlank
        String sellPrtnrNo
    ) {}

    public record BaseP(
        String prtnrKnm,
        String ogCd, // 조직코드
        String rsbDvCd, // 직책구분코드
        Long amtEstSalFee, // 예상판매수수료 - 개인
        Long amtMutAidFee, // 예상상조수수료 - 개인
        Long amtFeeSum // 예상수수료합계 - 개인
    ) {}

    public record PerformanceP(
        String type, // 구분
        Long amtElhm, // 가전
        Long amtExceptElhm, // 가전외
        Long amtMutu429, // 상조-429
        Long amtMutu599, // 상조-599
        String eduCertSrtupYn, // 교육수료-플래너스타트업
        String eduCertPlarPriticYn // 교육수료-플래너실전
    ) {}

    public record EstimateP(
        Long amtSumElhmPrpn, /* 개인-가전비례 */
        Long amtSumElhmExcpPrpn, /* 개인-가전외비례 */
        Long amtSumSalIntv, /* 개인-판매장려 */
        Long amtSumStmnt, /* 개인-정착 */
        Long amtSumMutu, /* 개인-상조 */
        Long amtSumOgElhmPrpn, /* 조직-가전비례 */
        Long amtSumOgElhmExcpPrpn, /* 조직-가전외비례 */
        Long amtSumOgSalIntv, /* 조직-판매장려 */
        Long amtSumOgMutu /* 조직-상조 */
    ) {}

    public record SaleP(
        String prtnrNo,
        String prtnrKnm,
        String perfDvCd,
        String cntrwTpCd,
        String cntrRcpFshDtm,
        String cntrCnfmDtm,
        String cntrNo,
        String pdNm,
        String cstKnm,
        String sellDvCd,
        Long amtSumElhm,
        Long amtSumExceptElhm,
        Long amtSumChng
    ) {}

    public record SearchOgPRes(
        BaseP base,
        List<PerformanceP> performances,
        List<EstimateP> estimates,
        List<SaleP> sales
    ) {}
}
