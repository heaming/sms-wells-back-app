package com.kyowon.sms.wells.web.bond.consultation.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

public class WbncSameCustomerContractDto {
    @ApiModel(value = "WbncSameCustomerContractDto-FindContractRes")
    public record FindContractRes(
        String mpyDpTpNm,
        String bndBizDvCd,
        String bndBizDvNm,
        String pdClsfNm,
        String pdNm,
        String pdCd,
        String cntrNo,
        String cntrSn,
        String cntrNoSn,
        String cstNo,
        String cstKnm,
        String sfkVal,
        String dlqMcn,
        String ojAmt,
        String totOjDpAmt,
        String totRemain,
        String clctamPrtnrNo,
        String prtnrKnm,
        String authRsgCnfmdt
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositRes")
    public record FindDepositRes(
        String baseYm,
        String rentalTn,
        String jTn,
        String nomSlAmt,
        String slDpAggAmt,
        String thmSlSumAmt,
        String mshDpAmt,
        String lentalDpAmt,
        String prmSlAmt,
        String prpdSlAmt,
        String dlqAmt,
        String dlqMcn,
        String dlqAddAmt,
        String dlqAddDpAmt,
        String eotDlqAddAmt,
        String ucAmt
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositInfoRes")
    public record FindDepositInfoRes(
        String cntrNo,
        String cntrSn,
        String cntrNoSn,
        String ucAmt,
        String dlqMcn,
        String slDpAmt,
        String lentalDpAmt,
        String dpAmt,
        String dlqAmt,
        String thmChramAmt,
        String ojAmt,
        String ojBlam,
        String ucDpAmt,
        String ucBlam,
        String dlqBlam,
        String dlqDpAmt,
        String thmChramDpAmt,
        String thmChramBlam,
        String dlqAddAmt,
        String dlqAddDpAmt,
        String eotDlqAddAmt,
        String slAggAmt,
        String borAmt,
        String lsfe,
        String dscAggAmt,
        String ctrAggAmt,
        String slDpAggAmt
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindBreachOfPromiseRes")
    public record FindBreachOfPromiseRes(
        String eotBorAmt,
        String dpCcamSumAmt,
        String borBlam,
        String thmSlSumAmt,
        String ucAmt,
        String rsgBorAmt,
        String rgstCostDscBorAmt,
        String rentalDscBorAmt,
        String csmbCostBorAmt,
        String pBorAmt,
        String reqdCsBorAmt,
        String lsRntf,
        String rstlBorAmt,
        String acuDpAmt
    ) {}

    @ApiModel(value = "EbncSameCustomerContractDto-FindSalesRes")
    public record FindSalesRes(
        BigDecimal regFee,
        BigDecimal rtlfe1MmChram,
        String rtlfeDutyPtrm,
        String reqdRqdt,
        BigDecimal rgstCostDsc,
        BigDecimal rtlfe1Dsc,
        String pdDcClass,
        String cancDt,
        BigDecimal rntlTotal,
        BigDecimal rtlfe1Mcnt,
        String discCode,
        String leaseDv,
        BigDecimal rtlfe2MmChram,
        BigDecimal mngtPrdMcnt,
        String onePlusOneLkCntr,
        BigDecimal rtlfe2Dsc,
        String parCntr,
        String alncLkCntr,
        BigDecimal rtlfe2Mcnt,
        String slDt,
        BigDecimal mmSspcs,
        String discCode2,
        String wdwalDt,
        BigDecimal vstPrd,
        BigDecimal stplMcnt,
        BigDecimal dscAmt,
        String vstDt,
        BigDecimal mngtPrd,
        String jDt,
        BigDecimal vstNmn,
        BigDecimal frisuMsh,
        BigDecimal salePrice,
        BigDecimal tkAmt,
        BigDecimal istmMcnt,
        BigDecimal frisuAs,
        BigDecimal cntrctAmt,
        BigDecimal istmAmt,
        BigDecimal recapMsh,
        BigDecimal sbscm,
        BigDecimal mmIntam
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositDtlRes")
    public record FindDepositDtlRes(
        String rveNoSn,
        String rveDt,
        String perfDt,
        String rveCd,
        String rveNm,
        String dpDvCd,
        String dpDvNm,
        String sellTpCd,
        String sellTpNm,
        String dpTpCd,
        String dpTpNm,
        String rveAmt,
        String dpMesCd,
        String dpMesNm,
        String rveDvCd,
        String rveDvNm
    ) {}
}
