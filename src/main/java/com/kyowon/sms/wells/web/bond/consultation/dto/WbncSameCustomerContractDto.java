package com.kyowon.sms.wells.web.bond.consultation.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

public class WbncSameCustomerContractDto {
    @ApiModel(value = "WbncSameCustomerContractDto-FindContractRes")
    public record FindContractRes(
        String mpyBsdt,
        String sellTpCd,
        String sellTpNm,
        String prdGrp,
        String pdNm,
        String bndCntrRefId,
        String cstKnm,
        String dlqMcn,
        String cntrRsgDt,
        BigDecimal ojAmt,
        BigDecimal ojDt,
        BigDecimal ojBlam
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositRes")
    public record FindDepositRes(
        String perfDt,
        String nmn,
        BigDecimal slAmt,
        BigDecimal dpAmt,
        BigDecimal bznsAtamBlam,
        BigDecimal dlqAmt,
        String dlqMcnt,
        BigDecimal dlqAdamt,
        BigDecimal dlqAddDp,
        BigDecimal dlqAddBlam
    ) {}

    @ApiModel(value = "WbncSameCustomerContractDto-FindDepositDtlRes")
    public record FindDepositDtlRes(
        BigDecimal ojAmt,
        BigDecimal rsgBorAmt,
        BigDecimal dlqAmt,
        BigDecimal slAggAmt,
        BigDecimal ojDt,
        BigDecimal lsfe,
        BigDecimal dlqDpAmt,
        BigDecimal dpAgg,
        BigDecimal ojBlam,
        BigDecimal dlqMcn,
        BigDecimal dlqBlam,
        BigDecimal dscAgg,
        BigDecimal ucAmt,
        BigDecimal thmChramAmt,
        BigDecimal dlqAddAmt,
        BigDecimal ctrAgg,
        BigDecimal ucDp,
        BigDecimal thmChramDpAmt,
        BigDecimal dlqAddDpAmt,
        BigDecimal ucBlam,
        BigDecimal chramBlam,
        BigDecimal addBlam
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
