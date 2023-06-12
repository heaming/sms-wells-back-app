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

    public record BaseM(
        String prtnrKnm,
        String ogCd, // 조직코드
        String rsbDvCd, // 직책구분코드
        Long amtEstSalFee, // 예상판매수수료
        Long amtEstBsFee, // 예상BS수수료
        Long amtFeeSum, // 예상수수료합계
        String ojDsbYm // 개시차월
    ) {}

    public record PerformanceM(
        String type, // 구분
        Long elhmAckmtCt,/* 가전인정건수 */
        Long spayIstm,/* 일시불/할부  */
        Long baseRtlfe,/* 기준렌탈료   */
        Long bfsvcAckmtCt,/* BS인정건수   */
        String plarSrtup,/* 플래너 스타트업 */
        String topmrPlarStmnt,/* 수석플래너 정착 */
        String plarPrtic,/* 플래너 실전     */
        String managerSrtup,/* 매니저 스타트업 */
        String managerStmnt,/* 매니저 정착     */
        String cmpfEduc,/* 보수교육        */
        Long metgPrscDc/* 미팅참석일수 */
    ) {}

    public record BsM(
        String type, // 구분
        Long wrfr01, /* 정수기 */
        Long wrfr02, /* 정수기 */
        Long wrfr03, /* 정수기 */
        Long wrfr04, /* 정수기 */
        Long unWrfr, /* 비정수기 */
        Long puf01,/* 청정기1             */
        Long puf02,/* 청정기2             */
        Long otscEtc,/* 아웃소싱,커피,삼성  */
        Long bdtEtc,/* 비데,연수기         */
        Long etcFxamDsb,/* 미지정              */
        String w1,/* 급지구분    - 1:1급지, 2:2급지, 3:3급지 */
        String w2 /* 급지구분    - 1:1급지, 2:2급지, 3:3급지 */

    ) {}

    public record EstimateM(
        Long amtSumElhmPrpn, /* 가전비례   */
        Long amtSumElhmMetg, /* 가전미팅   */
        Long amtSumElhmExcpPrpn, /* 가전외비례 */
        Long amtSumElhmExcpMetg, /* 가전외미팅 */
        Long amtSumSalIntv, /* 판매장려   */
        Long amtEdu, /* 교육       */
        Long amtSumStmnt, /* 정착       */
        Long amtBsMgmt, /* BS관리     */
        Long amtRglvl /* 급지       */
    ) {}

    public record SaleM(
        String cntrRcpFshDtm,  /* 접수일자 - 계약접수완료일시        */
        String cntrCnfmDtm,  /* 매출일자 - 계약확정일시            */
        String cntrNo,  /* 계약번호                           */
        String cstKnm,  /* 계약자                             */
        String pdNm,  /* 상품명                             */
        String cntrwTpCd,  /* 상품구분 - 계약서유형코드:BH, 렌탈 T4.CNTRW_TP_CD*/
        String cntrTpCd,  /* 계약유형                           */
        Long pdAccCnt, /* 인정건수 - 판매인정건수 */
        Long bfsvcAckmtCt, /* BS인정건수 - BS판매인정건수 */
        Long amtElhmRental, /* 가전렌탈 - (인정)렌탈금액 */
        Long amtElhmSpay, /* 가전일시불 - 홈케어일시불 */
        Long elhmExcpSpay, /* 가전외일시불 - (기준금액)환경외－일시불 */
        Long etc
    ) {}

    public record SearchOgMRes(
        BaseM base,
        List<PerformanceM> performances,
        List<BsM> bses,
        List<EstimateM> estimates,
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
    public record SearchTabletHomeRes(
        BaseHome base,
        List<PerformanceTabletHome> performances,
        List<EstimateTabletHome> estimates,
        List<SaleHome> sales
    ) {}

    public record PerformanceTabletHome(
        String type, // 구분
        Integer elhmAckmtCt,
        Long amtSpayHcr,
        Integer allProcCt,
        Integer elhmProcCt,
        String nwcmrEducYn
    ) {}
    public record EstimateTabletHome(
        Long amtEstSalPrpn,
        Long amtEstSalEarlySttlmnt,
        Long amtEstSalEnrg,
        Long amtEstSvcScene,
        Long amtEstSvcActi,
        Long amtEstSvcAcml,
        Long amtEstSvcEdu
    ) {}


}
