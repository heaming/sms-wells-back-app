package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdccProductAccountDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Dto
    @ApiModel("WdccProductAccountDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYmFrom,
        @NotBlank
        String baseYmTo,
        String sellTpCd,
        String sellTpDtlCd,
        String ogTpCd,
        String prdtCateHigh,
        String prdtCateMid
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchTotalRes")
    public record SearchTotalRes(
        String baseYm,
        String sellTpCd,
        String sellTpDtlCd,
        String agrgCt1,
        String agrgCt2,
        String agrgCt3,
        String agrgCt4,
        String agrgCt5,
        String agrgCt6
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchProductRes")
    public record SearchProductRes(
        String baseYm,
        String sellTpCd,
        String sellTpDtlCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        String pdNm,
        String agrgCt1,
        String agrgCt2,
        String agrgCt3,
        String agrgCt4,
        String agrgCt5,
        String agrgCt6
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품별 매출 현황 Search Result Dto
    @ApiModel("WdccProductAccountDto-SearchExcelRes")
    public record SearchExcelRes(
        String sellTpDtlCd,
        String custClsCd,
        String cntrNo,
        String cstKnm,
        String newCustYn,
        String cstNo,
        String bryyMmdd,
        String sexDvCd,
        String ogTpNm,
        String ogCd,
        String prtnrNo,
        String prtnrNm,
        String ctntCstZip,
        String ojZip,
        String pdHclsfNm,
        String pdMclsfNm,
        String pdCd,
        String pdNm,
        String svPrd,
        String vstPrdVal,
        String dutyUseMcn,
        String cntrPtrm,
        String mmIstmAmt,
        String cntrPmotId,
        String rcpdt,
        String cntrDt,
        String rsgDt,
        String reqdDt,
        String exnDt,
        String cntrExnDt,
        String dutyUseMcnYn,
        String rentalTn,
        String nomAccYn,
        String sellTpDtlCd2,
        String custClsCd2,
        String cntrNo2,
        String cstKnm2,
        String newCustYn2,
        String cstNo2,
        String bryyMmdd2,
        String sexDvCd2,
        String ogTpNm2,
        String ogCd2,
        String prtnrNo2,
        String prtnrNm2,
        String ctntCstZip2,
        String ojZip2,
        String pdHclsfNm2,
        String pdMclsfNm2,
        String pdCd2,
        String pdNm2,
        String svPrd2,
        String vstPrdVal2,
        String dutyUseMcn2,
        String cntrPtrm2,
        String mmIstmAmt2,
        String cntrPmotId2,
        String rcpdt2,
        String cntrDt2,
        String rsgDt2,
        String reqdDt2,
        String exnDt2,
        String cntrExnDt2,
        String dutyUseMcnYn2,
        String rentalTn2,
        String nomAccYn2,
        String sellTpDtlCd3,
        String custClsCd3,
        String cntrNo3,
        String cstKnm3,
        String newCustYn3,
        String cstNo3,
        String bryyMmdd3,
        String sexDvCd3,
        String ogTpNm3,
        String ogCd3,
        String prtnrNo3,
        String prtnrNm3,
        String ctntCstZip3,
        String ojZip3,
        String pdHclsfNm3,
        String pdMclsfNm3,
        String pdCd3,
        String pdNm3,
        String svPrd3,
        String vstPrdVal3,
        String dutyUseMcn3,
        String cntrPtrm3,
        String mmIstmAmt3,
        String cntrPmotId3,
        String rcpdt3,
        String cntrDt3,
        String rsgDt3,
        String reqdDt3,
        String exnDt3,
        String cntrExnDt3,
        String dutyUseMcnYn3,
        String rentalTn3,
        String nomAccYn3
    ) {}
}
