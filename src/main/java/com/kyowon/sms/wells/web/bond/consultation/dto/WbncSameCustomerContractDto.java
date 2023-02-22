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

}
