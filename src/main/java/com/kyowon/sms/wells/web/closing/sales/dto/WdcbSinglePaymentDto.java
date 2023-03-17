package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbSinglePaymentDto {
    // *********************************************************
    // Search Dto
    // *********************************************************
    // 매출 상세정보 Search Dto
    @ApiModel("WdcbSinglePaymentDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrDtlNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSinglePaymentDto-BaseSearchRes")
    public record BaseSearchRes(
        String taskDiv,
        String cntrDtlNo,
        String cstKnm,
        String copnDvCd,
        String sellPrtnrNo,
        String cntrCnfmDtm,
        String slDt,
        String slCanDt,
        String sellOgTpCd,
        String tbSsctCntrDtl,
        String dscAmt,
        String dscApyTpCd,
        String alncmpCd,
        String evInf,
        String sellAmt,
        String alncFee,
        String subscAmt,
        String tkAmt,
        String crpUcAmt,
        String blam,
        String istmMcn,
        String feeAckmtBaseAmt,
        String fulpyDt,
        String fstFulpyDt,
        String chgDt,
        String accDv,
        String statChDt,
        String slChDt,
        String slChSn,
        String dpAmt1,
        String dpAmt2,
        String dpAmt3,
        String dpAmt4,
        String dpAmt5,
        String dpTam,
        String fntDvCd,
        String rglFntD,
        String dlqMcn,
        String eotDlqAmt,
        String ucAmt,
        String ucPrnt,
        String dfaBsdt,
        String dfaProcsAmt,
        String dfaDpAmt,
        String dfaBlam,
        String dfaPrcsdt,
        String dfaFulpyAmt,
        String actcsDt,
        String clctamDvCd,
        String clctamPrtnrNo,
        String dsbGur,
        String buNoti,
        String redfDt,
        String adsbDt,
        String notiCn,
        String bnkCd,
        String vtAc
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSinglePaymentDto-SalesSearchRes")
    public record SalesSearchRes(
        String slClYm,
        String dlqMcn,
        String eotDlqAmt,
        String clctamDvCd,
        String clctamPrtnrNo,
        String col6,
        String col7,
        String col8,
        String sellAmt,
        String istmMcn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSinglePaymentDto-DepositSearchRes")
    public record DepositSearchRes(
        String rveDvCd,
        String rveDt,
        String perfDt,
        String dpDvCd,
        String dpKndCd,
        String dpTpCd,
        String rveAmt
    ) {}

}
