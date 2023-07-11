package com.kyowon.sms.wells.web.fee.simulation.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfefEstimateFeeMgtDto {
    public record SearchEstimateReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String perType,
        @NotBlank
        String sellPrtnrNo,
        String userDvCd
    ) {}

    public record BaseP(
        String useOg, // 조직사용여부
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

    public record BaseM(
        String prtnrKnm,
        String ogCd, // 조직코드
        String rsbDvCd, // 직책구분코드
        String startYm, // 개시차월
        String prfmtYm, // 승진차월
        Long amtEstSalFee, // 예상판매수수료
        Long amtEstOgFee, // 예상조직수수료
        Long amtEstBsFee, // 예상BS수수료
        Long amtFeeSum // 예상수수료합계
    ) {}

    public record MeetingM(
        String preSrtup,
        String srtup,
        String metgPrscD,
        String cmpfEduc,
        String stmnt1,
        String stmnt2,
        String stmnt345,
        String brmgrOnline
    ) {}

    public record PerformanceM(
        String type, // 구분
        Long elhmAckmtCt, /* 가전인정건수 */
        Long rentalBasePrc, /* 기준렌탈료   */
        Long snglPmntBasePrc, /* 일시불/할부  */
        Long elhmExcpAckmtPerf, /* 가전외인정실적금액   */
        Long ninc /* 계정순증건수   */
    ) {}

    public record BsM(
        String type, // 구분
        Long wrfr01, /* 정수기 */
        Long wrfr02, /* 정수기 */
        Long wrfr03, /* 정수기 */
        Long wrfr04, /* 정수기 */
        Long unWrfr, /* 비정수기 */
        Long puf01, /* 청정기1             */
        Long puf02, /* 청정기2             */
        Long otscEtc, /* 아웃소싱,커피,삼성  */
        Long bdtEtc, /* 비데,연수기         */
        Long w1,/* 급지구분    - 1:1급지, 2:2급지, 3:3급지 */
        Long w2, /* 급지구분    - 1:1급지, 2:2급지, 3:3급지 */
        Long prdSum
    ) {}

    public record OgBsM(
        String type, // 구분
        Long asgnCt,
        Long fhsCt,
        Long procsRt,
        Long etFhsCt,
        Long etProcsRt
    ) {}

    public record EstimateM(
        Long estSalCommElhmPrpn,
        Long estSalCommElhmExcpPrpn,
        Long estSalCommElhmEnrg,
        Long estSalCommMetg,
        Long estSalCommEduc,
        Long estSalCommStmnt,
        Long estSalCommMchnCh,
        Long estSalCommAgg,
        Long estBsFeeBsMgmt,
        Long estBsFeeBsEnrg,
        Long estBsFeeRglvl,
        Long estBsFeeAgg,
        Long estOgFeeElhmOgPrpn,
        Long estOgFeeElhmOgExcpPrpn,
        Long estOgFeeOgSellEncrg,
        Long estOgFeeNincMgt,
        Long estOgFeeOgEjt1,
        Long estOgFeeOgEjt2,
        Long estOgFeeNbBrch,
        Long estOgFeeAgg
    ) {}

    public record SaleM(
        String prtnrKnm,
        String prtnrNo,
        String cntrRcpFshDtm,
        String cntrCnfmDtm,
        String cntrCanDtm,
        String cntrNo,
        String pdNm,
        String cstKnm,
        String mchnChTpCd,
        Long amtElhmBasePrc,
        Integer elhmAckmtCt,
        Integer elhmExcpAckmtCt
    ) {}

    public record SearchOgMRes(
        String userDvCd,
        BaseM base,
        MeetingM meeting,
        List<PerformanceM> performances,
        List<BsM> bses,
        List<OgBsM> ogBses,
        EstimateM estimate,
        List<SaleM> sales
    ) {}

    public record BaseHome(
        String prtnrKnm,
        String ogCd, // 조직코드
        String rsbDvCd, // 직책구분코드
        Long amtEstSalFee, // 예상판매수수료
        Long amtEstSerFee // 예상서비스수수료
    ) {}

    public record PerformanceHome(
        String type, // 구분
        Integer elhmAckmtCt,
        Long amtSpayHcr,
        Integer allProcCt,
        Integer elhmProcCt,
        String nwcmrEducYn,
        String acpnEducYn
    ) {}

    public record EstimateHome(
        Long amtEstSalPrpn,
        Long amtEstSalEarlySttlmnt,
        Long amtEstSalEnrg,
        Long amtEstSvcScene,
        Long amtEstSvcActi1,
        Long amtEstSvcActi2,
        Long amtEstSvcAcml,
        Long amtEstSvcEdu
    ) {}

    public record SaleHome(
        String cntrRcpFshDtm,
        String cntrCnfmDtm,
        String cntrNo,
        String cstKnm,
        String pdNm,
        String pdCd,
        String cntrwTpCd,
        String cntrTpCd,
        Long sumAckmtCt,
        Long sumRentalAmt,
        Long sumHomeCare,
        Long sumEtc
    ) {}

    public record SearchHomeRes(
        BaseHome base,
        List<PerformanceHome> performances,
        List<EstimateHome> estimates,
        List<SaleHome> sales
    ) {}
}
