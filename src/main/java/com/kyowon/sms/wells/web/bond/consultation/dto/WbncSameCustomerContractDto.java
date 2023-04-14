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
        int cntrSn,
        String cstNo,
        String cstKnm,
        String sfkVal,
        int dlqMcn,
        BigDecimal ojAmt,
        BigDecimal totOjDpAmt,
        BigDecimal totRemain,
        String clctamPrtnrNo,
        String prtnrKnm,
        String authRsgCnfmdt
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositRes")
    public record FindDepositRes(
        String baseYm,
        int rentalTn,
        int jTn,
        BigDecimal nomSlAmt,
        BigDecimal slDpAggAmt,
        BigDecimal thmSlSumAmt,
        BigDecimal mshDpAmt,
        BigDecimal lentalDpAmt,
        BigDecimal prmSlAmt,
        BigDecimal prpdSlAmt,
        BigDecimal dlqAmt,
        int dlqMcn,
        BigDecimal dlqAddAmt,
        BigDecimal dlqAddDpAmt,
        BigDecimal eotDlqAddAmt,
        BigDecimal ucAmt
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositDtlRes")
    public record FindDepositDtlRes(
        String cntrNo,
        int cntrSn,
        BigDecimal ucAmt,
        int dlqMcn,
        BigDecimal slDpAmt,
        BigDecimal lentalDpAmt,
        BigDecimal dpAmt,
        BigDecimal dlqAmt,
        BigDecimal thmChramAmt,
        BigDecimal ojAmt,
        BigDecimal ojBlam,
        BigDecimal ucDpAmt,
        BigDecimal ucBlam,
        BigDecimal dlqBlam,
        BigDecimal dlqDpAmt,
        BigDecimal thmChramDpAmt,
        BigDecimal thmChramBlam,
        BigDecimal dlqAddAmt,
        BigDecimal dlqAddDpAmt,
        BigDecimal eotDlqAddAmt,
        BigDecimal slAggAmt,
        BigDecimal borAmt,
        BigDecimal lsfe,
        BigDecimal dscAggAmt,
        BigDecimal ctrAggAmt,
        BigDecimal slDpAggAmt
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindBreachOfPromiseRes")
    public record FindBreachOfPromiseRes(
        BigDecimal ccamTam,
        BigDecimal acuDp,
        BigDecimal rentalRntf,
        BigDecimal borDp,
        BigDecimal bndNpd,
        BigDecimal csmbCs,
        BigDecimal borBlam,
        BigDecimal rgstCsDsc,
        BigDecimal reqdCs,
        BigDecimal slAmt,
        BigDecimal ucAmt,
        BigDecimal lsfe
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
}
