package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 조직별 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfeaOrganizationNetOrderDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 조직별 실적 집계 Search Request Dto
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchReq")
    public record SearchReq(

    ) {}

    @Builder
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SaveOgNetOrderReq")
    public record SaveOgNetOrderReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String feeTcntDvCd,
        @NotBlank
        String perfAgrgCrtDvCd,
        String dv
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String feeTcntDvCd,
        String divCd,
        String feePerfCd,
        String pdctTpCd,
        String sellTpCd,
        String strtDt,
        String endDt,
        String cancStrtDt,
        String cancEndDt,
        String pdStrtCd,
        String pdEndCd,
        String pkgStrtCd,
        String pkgEndCd,
        String og1LevlId,
        String og2LevlId,
        String og3LevlId,
        String prtnrNo,
        String perfYm,
        String rsbDvCd
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerDetailReq")
    public record SearchMngerDetailReq(
        @NotBlank
        String dvCd, /* 구분 */
        @NotBlank
        String rcpDtFrom, /* 접수일자 시작 */
        @NotBlank
        String rcpDtTo, /* 접수일자 종료 */
        String cancDtFrom, /* 취소일자 시작 */
        String cancDtTo, /* 취소일자 종료 */
        String pdCdFrom, /* 상품코드 시작 */
        String pdCdTo, /* 상품코드 종료 */
        String pkgCdFrom, /* 패키지코드 시작 */
        String pkgCdTo, /* 패키지코드 종료 */
        String feePerfTpCd, /* 수수료실적유형코드 */
        String sellTpCd, /* 판매유형코드 */
        String prtnrNo, /* 파트너번호 */
        String feePdctTpCd, /* 수수료제품유형코드 */
        String ogLevl1, /* 조직레벨1 */
        String ogLevl2, /* 조직레벨2 */
        String ogLevl3, /* 조직레벨3 */
        String ogLevl4, /* 조직레벨4 */
        String ogLevl5 /* 조직레벨5 */
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerAggregateReq")
    public record SearchMngerAggregateReq(
        @NotBlank
        String dvCd, /* 구분 */
        @NotBlank
        String feeTcntDvCd, /* 수수료차수구분코드 */
        @NotBlank
        String perfYm, /* 실적년월 */
        String ogLevl1, /* 조직레벨1 */
        String ogLevl2, /* 조직레벨2 */
        String ogLevl3, /* 조직레벨3 */
        String ogLevl4, /* 조직레벨4 */
        String ogLevl5, /* 조직레벨5 */
        String prtnrNo /* 파트너번호 */
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerStatusReq")
    public record SearchMngerStatusReq(
        String rsbDvCd, /* 직책구분코드 */
        @NotBlank
        String feeTcntDvCd, /* 수수료차수구분코드 */
        @NotBlank
        String perfYm, /* 실적년월 */
        String ogLevl1, /* 조직레벨1 */
        String ogLevl2, /* 조직레벨2 */
        String ogLevl3, /* 조직레벨3 */
        String ogLevl4, /* 조직레벨4 */
        String ogLevl5, /* 조직레벨5 */
        String prtnrNo /* 파트너번호 */
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String inqrDvCd,
        String feeTcntDvCd,
        String divCd,
        String feePerfCd,
        String pdctTpCd,
        String sellTpCd,
        String strtDt,
        String endDt,
        String cancStrtDt,
        String cancEndDt,
        String pdStrtCd,
        String pdEndCd,
        String pkgStrtCd,
        String pkgEndCd,
        String og1LevlId,
        String og2LevlId,
        String og3LevlId,
        String prtnrNo,
        String perfYm,
        String rsbDvCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 조직별 실적 집계 Search Result Dto
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchRes")
    public record SearchRes(

    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstRes")
    public record SearchHmstRes(
        String og2Lv,
        String og3Lv,
        String sequenceNumber,
        String emplNm,
        String selType,
        String pdctTp,
        String prcTp,
        String chdvcTp,
        String fee,
        String cntrDtlNo,
        String cstDv,
        String prdtNm,
        String prdtCode,
        String pdDcClass,
        String discCode,
        String dscSyst,
        String combiDv,
        String istm,
        String stplMcnt,
        String mngtPrd,
        String pdAccRslt,
        String basePrc,
        String homeCare,
        String hcrMshY3,
        String fxamYn,
        String fnnLease,
        String elhmAckmtCt,
        String nwSellCt,
        String obj,
        String recommitment,
        String cntrDate,
        String slDt,
        String cancDt,
        String demDt,
        String brmgrNo,
        String brmgrFnm,
        String rtlfe,
        String pmotNo,
        String pkgPdNo,
        String pkgSn,
        String mchnPrtnr,
        String mchnPd,
        String perfExcd
    ) {}
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstFeeRes")
    public record SearchHmstFeeRes(
        String og2Lv,
        String og3Lv,
        String sequenceNumber,
        String emplNm,
        String selType,
        String pdctTp,
        String prcTp,
        String chdvcTp,
        String fee,
        String cntrDtlNo,
        String cstDv,
        String prdtNm,
        String prdtCode,
        String pdDcClass,
        String discCode,
        String dscSyst,
        String combiDv,
        String istm,
        String stplMcnt,
        String mngtPrd,
        String pdAccRslt,
        String basePrc,
        String homeCare,
        String hcrMshY3,
        String fxamYn,
        String fnnLease,
        String elhmAckmtCt,
        String nwSellCt,
        String obj,
        String recommitment,
        String cntrDate,
        String slDt,
        String cancDt,
        String demDt,
        String brmgrNo,
        String brmgrFnm,
        String rtlfe,
        String pmotNo,
        String pkgPdNo,
        String pkgSn,
        String mchnPrtnr,
        String mchnPd,
        String perfExcd
    ) {}

    public record SearchHmstFeeRes2(
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String cntrNo,
        String rcpdt,
        String slDt,
        String canDt,
        String feePerfTpCd,
        String pdCd,
        String pdNm,
        String ackmtPerfAmt,
        String ackmtPerfCt,
        String mchnChTpCd
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerDetailRes")
    public record SearchMngerDetailRes(
        String ogCd, /* 조직코드 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String sellTpCd, /* 판매유형코드 */
        String feePdctTpCd, /* 수수료제품유형코드 */
        String rglrSppPrcDvCd, /* 정기배송가격구분코드 */
        String mchnChTpCd, /* 기기변경유형코드 */
        String feePerfTpCd, /* 수수료실적유형코드 */
        String cntrNo, /* 계약번호, 계약일련번호 */
        String copnDvCd, /* 법인격구분코드 */
        String pdNm, /* 상품명 */
        String basePdCd, /* 기준상품코드 */
        String sellDscDvCd, /* 판매할인구분코드 */
        String sellDscrCd, /* 판매할인율코드 */
        String sellDscTpCd, /* 판매할인유형코드 */
        String combiDv, /* 결합구분코드 */
        String cntrPtrm, /* 계약기간 */
        String stplPtrm, /* 약정기간 */
        String svPrd, /* 서비스주기 */
        String ackmtPerfAmt, /* 인정실적금액 */
        String feeAckmtBaseAmt, /* 수수료인정기준금액 */
        String hcr, /* 홈케어 */
        String hcrMshY3, /* 홈케어멤버십3년 */
        String feeFxamYn, /* 정액여부 */
        String fnnLease, /* 금융리스 */
        String feeAckmtCt, /* 수수료인정건수 */
        String nwSellCt, /* 신규판매건수 */
        String bfsvcOjYn, /* bs대상여부 */
        String rstlYn, /* 재약정여부 */
        String cntrCnfmDtm, /* 계약확정일시 */
        String slDt, /* 매출일자 */
        String canDt, /* 취소일자 */
        String reqdDt, /* 철거일자 */
        String dgr3LevlDgPrtnrNo, /* 3차레벨대표파트너번호 */
        String dgr3LevlDgPrtnrNm, /* 3차레벨대표파트너명 */
        String sellAmt, /* 판매금액 */
        String pmotNo, /* 프로모션번호 */
        String pkgCd, /* 패키지코드 */
        String pkgSn, /* 패키지일련번호 */
        String mchnCstCd, /* 기기고객코드 */
        String mchnPdCd, /* 기기상품코드 */
        String perfExcdRgstYn /* 실적제외등록여부 */
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerAggregateRes")
    public record SearchMngerAggregateRes(
        String ogCd, /* 조직코드 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String sellTpCd, /* 판매유형코드 */
        String feePdctTpCd, /* 수수료제품유형코드 */
        String rglrSppPrcDvCd, /* 정기배송가격구분코드 */
        String mchnChTpCd, /* 기기변경유형코드 */
        String feePerfTpCd, /* 수수료실적유형코드 */
        String cntrNo, /* 계약번호, 계약일련번호 */
        String copnDvCd, /* 법인격구분코드 */
        String pdNm, /* 상품명 */
        String basePdCd, /* 기준상품코드 */
        String sellDscDvCd, /* 판매할인구분코드 */
        String sellDscrCd, /* 판매할인율코드 */
        String sellDscTpCd, /* 판매할인유형코드 */
        String combiDv, /* 결합구분코드 */
        String cntrPtrm, /* 계약기간 */
        String stplPtrm, /* 약정기간 */
        String svPrd, /* 서비스주기 */
        String ackmtPerfAmt, /* 인정실적금액 */
        String feeAckmtBaseAmt, /* 수수료인정기준금액 */
        String hcr, /* 홈케어 */
        String hcrMshY3, /* 홈케어멤버십3년 */
        String feeFxamYn, /* 정액여부 */
        String fnnLease, /* 금융리스 */
        String feeAckmtCt, /* 수수료인정건수 */
        String nwSellCt, /* 신규판매건수 */
        String bfsvcOjYn, /* bs대상여부 */
        String rstlYn, /* 재약정여부 */
        String cntrCnfmDtm, /* 계약확정일시 */
        String slDt, /* 매출일자 */
        String canDt, /* 취소일자 */
        String reqdDt, /* 철거일자 */
        String dgr3LevlDgPrtnrNo, /* 3차레벨대표파트너번호 */
        String dgr3LevlDgPrtnrNm, /* 3차레벨대표파트너명 */
        String sellAmt, /* 판매금액 */
        String pmotNo, /* 프로모션번호 */
        String pkgCd, /* 패키지코드 */
        String mchnCstCd, /* 기기고객코드 */
        String mchnPdCd, /* 기기상품코드 */
        String perfExcdRgstYn /* 실적제외등록여부 */
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerStatusRes")
    public record SearchMngerStatusRes(
        String ogCd, /* 조직코드 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String pstnDvCd, /* 직급구분코드 */
        String ehCnt, /* 가전건수 */
        String exCnt, /* 가전외건수 */
        String etCnt, /* 기타건수 */
        String upCnt, /* 미지급건수 */
        String totCnt, /* 총건수 */
        String ehAmt, /* 가전금액 */
        String exAmt, /* 가전외금액 */
        String etAmt, /* 기타금액 */
        String upAmt, /* 미지급금액 */
        String totAmt, /* 총금액 */
        String elhmAckmtCt, /* 가전인정건수 */
        String rentalBasePrc, /* 렌탈기준가 */
        String snglPmntBasePrc, /* 일시불기준가 */
        String elhmExcpAckmtPerf, /* 가전외인정실적 */
        String chng, /* 기변 */
        String ninc, /* 순증 */
        String fxamCt, /* 정액건수 */
        String rstlCt, /* 재약정건수 */
        String livePakg, /* 라이브팩 */
        String mmbr /* 멤버십 */
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarRes")
    public record SearchPlarRes(
        String og1Lv,
        String og2Lv,
        String og3Lv,
        String sequenceNumber,
        String emplNm,
        String selType,
        String pdctTp,
        String chdvcTp,
        String fee,
        String cntrDtlNo,
        String cstDv,
        String prdtNm,
        String prdtCode,
        String uswy,
        String pdDcClass,
        String discCode,
        String dscSyst,
        String combiDv,
        String dpPerf,
        String istm,
        String homeCare,
        String hcrMshY3,
        String fxamYn,
        String fnnLease,
        String recommitment,
        String cntrDate,
        String slDt,
        String cancDt,
        String brmgrNo,
        String brmgrFnm,
        String rtlfe,
        String stplMcnt,
        String mngtPrd,
        String pdAccRslt,
        String pmotNo,
        String pkgSn,
        String pkgNo,
        String ntorMm,
        String mchnPd,
        String perfExcd,
        String bizRgstMm,
        String bizRgstNm
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarSellFeeRes")
    public record SearchPlarSellFeeRes(
        String og1Lv,
        String og2Lv,
        String og3Lv,
        String sequenceNumber,
        String emplNm,
        String selType,
        String pdctTp,
        String chdvcTp,
        String fee,
        String cntrDtlNo,
        String cstDv,
        String prdtNm,
        String prdtCode,
        String uswy,
        String pdDcClass,
        String discCode,
        String dscSyst,
        String combiDv,
        String dpPerf,
        String istm,
        String homeCare,
        String hcrMshY3,
        String fxamYn,
        String fnnLease,
        String recommitment,
        String cntrDate,
        String slDt,
        String cancDt,
        String brmgrNo,
        String brmgrFnm,
        String rtlfe,
        String stplMcnt,
        String mngtPrd,
        String pdAccRslt,
        String pmotNo,
        String pkgSn,
        String pkgNo,
        String ntorMm,
        String mchnPd,
        String perfExcd,
        String bizRgstMm,
        String bizRgstNm
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarAgrgRes")
    public record SearchPlarAgrgRes(
        String ogId,
        String prtnrNo,
        String prtnrKnm,
        String pstnDvCd,
        String ehCnt,
        String exCnt,
        String etCnt,
        String upCnt,
        String totCnt,
        String ehAmt,
        String exAmt,
        String etAmt,
        String upAmt,
        String totAmt
    ) {}
}
