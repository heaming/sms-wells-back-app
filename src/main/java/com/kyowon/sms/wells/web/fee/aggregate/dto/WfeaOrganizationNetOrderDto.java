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
        String ogTp
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SaveBsReq")
    public record SaveBsReq(
        @NotBlank
        String perfYm
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String schTcnt,
        @NotBlank
        String schDv,
        String schPdctTp,
        String schPdCdStrt,
        String schPdCdEnd,
        String schSlDtStrt,
        String schSlDtEnd,
        String schRcpDtStrt,
        String schRcpDtEnd,
        String schPerfYm,
        String schVstDtStrt,
        String schVstDtEnd
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerReq")
    public record SearchMngerReq(
        @NotBlank
        String schInqrDv,
        String schOrdr,
        String schDiv,
        String schFeePerf,
        String schPdctTp,
        String schSelType,
        String schDtStrt,
        String schDtEnd,
        String schCancDtStrt,
        String schCancDtEnd,
        String schPdCdStrt,
        String schPdCdEnd,
        String schPkgCdStrt,
        String schPkgCdEnd,
        String ogLevl1,
        String ogLevl2,
        String ogLevl3,
        String schPrtnrNo,
        String schPerfYm,
        String schRsbDv
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String schInqrDv,
        String schOrdr,
        String schDiv,
        String schFeePerf,
        String schPdctTp,
        String schSelType,
        String schDtStrt,
        String schDtEnd,
        String schCancDtStrt,
        String schCancDtEnd,
        String schPdCdStrt,
        String schPdCdEnd,
        String schPkgCdStrt,
        String schPkgCdEnd,
        String ogLevl1,
        String ogLevl2,
        String ogLevl3,
        String schPrtnrNo,
        String schPerfYm,
        String schRsbDv
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
        String col1, /*소속*/
        String col2, /*번호*/
        String col3, /*성명*/
        String col4, /*직책*/
        String col5, /*미팅일수*/
        String col6, /*수수료 월*/
        String col7, /*M+1 수석플래너*/
        String col8, /*플래너 스타트업*/
        String col9, /*수석실전*/
        String col10, /*등록기준월*/
        String col11, /*최초업무등록월*/
        String col12, /*재등록월*/
        String col13, /*최종해약월*/
        String col14, /*업무해약월*/
        String col15, /*승진월*/
        String col16, /*웰스매니저 개시일*/
        String col17, /*정착수수료*/
        String col18, /*가전실적*/
        String col19, /*가전외 실적*/
        String col20, /*기변*/
        String col21, /*상조당월*/
        String col22, /*상조유지*/
        String col23, /*환경*/
        String col24, /*환경외*/
        String col25, /*라이브팩 건수*/
        String col26, /*홈케어 판매가*/
        String col27, /*관리상품 판매건수*/
        String col28, /*정액 수수료*/
        String col29, /*라이브팩*/
        String col30, /*홈케어*/
        String col31, /*가전개인비례*/
        String col32, /*가전외 개인비례*/
        String col33, /*판매장려*/
        String col34, /*상조수수료*/
        String col35, /*미팅수수료*/
        String col36, /*정착*/
        String col37, /*재지급*/
        String col38, /*기타지원*/
        String col39 /*과표합계 */
    ) {}
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstSellFeeRes")
    public record SearchHmstSellFeeRes(
        String og2Nm, /* 지역단 */
        String og3Nm, /* 지점 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 성명 */
        String cntrNo, /*계약상세번호*/
        String rcpDt, /*접수일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String prdtyp, /*제품유형*/
        String pdCd, /*상품코드*/
        String prdgrp, /*상품그룹*/
        String pdNm, /*상품명*/
        String ackmtPerfCt, /*인정건수*/
        String mchnChTpCd /*기변유형*/
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerRes")
    public record SearchMngerRes(
        String blg,
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
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerSellFeeRes")
    public record SearchMngerSellFeeRes(
        String og1Lv,
        String og2Lv,
        String og3Lv,
        String prtnrNo,
        String prtnrKnm,
        String cntrDtlNo,
        String rcpdt,
        String slDt,
        String canDt,
        String feePdctTpCd,
        String pdCd,
        String feePerfTpCd,
        String pdNm,
        String ackmtPerfCt,
        String bfsvcPrdCd,
        String mchnChTpCd
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerAgrgRes")
    public record SearchMngerAgrgRes(
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
        String totAmt,
        String ehPerfCnt,
        String ehAmt1,
        String ehAmt2,
        String ehMcCnt,
        String fxamCnt,
        String lpkCnt,
        String hmbCnt
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
        String prtnrNo,
        String prtnrKnm,
        String cntrDtlNo,
        String rcpdt,
        String slDt,
        String canDt,
        String feePdctTpCd,
        String pdCd,
        String feePerfTpCd,
        String pdNm,
        String ackmtPerfCt,
        String bfsvcPrdCd,
        String mchnChTpCd
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
