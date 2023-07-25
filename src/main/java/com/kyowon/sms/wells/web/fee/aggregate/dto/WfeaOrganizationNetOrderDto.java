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
        String mchnChTpCd
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
