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
        Long feeSum // 예상수수료합계 - 개인
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
        Long sumPr01, /* 개인-가전비례 */
        Long sumPr02, /* 개인-가전외비례 */
        Long sumPr03, /* 개인-판매장려 */
        Long sumPr04, /* 개인-정착 */
        Long sumPr05, /* 개인-상조 */
        Long sumOg01, /* 조직-가전비례 */
        Long sumOg02, /* 조직-가전외비례 */
        Long sumOg03, /* 조직-판매장려 */
        Long sumOg04 /* 조직-상조 */
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
        Long sumAmt01,
        Long sumAmt04,
        Long sumAmt28
    ) {}

    public record SearchOgPRes(
        BaseP base,
        List<PerformanceP> performances,
        List<EstimateP> estimates,
        List<SaleP> sales
    ) {}
}
