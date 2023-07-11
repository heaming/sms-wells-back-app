package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccSalesPerformPenaltyDto {
    @ApiModel(value = "WdccSalesPerformPenaltyDto-SearchReq")
    public record SearchReq(
        String slClYm,
        String cntrNo,
        String cntrSn,
        String duedt
    ) {}

    @ApiModel(value = "WdccSalesPerformPenaltyDto-SearchRes")
    public record SearchRes(
        String prmTn,
        String prmMcn,
        String prmDscr,
        String prmStrtYm,
        String prmEndYm,
        String prmDscAmt,
        String totPrmAmt,
        String rentalAmt,
        String rentalDscAmt,
        String rentalTn,
        String nomSlAmt,
        String rentalDc,
        String slDc,
        String chngDt,
        String spmtSlAmt,
        String nomDscAmt,
        String canCtrAmt,
        String adnSv,
        String spmtDscAmt,
        String slCtrAmt,
        String thmSlSumAmt,
        String slSumVat,
        String slAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String ucAmt,
        String btdDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String thmCtrDlqAddAmt,
        String eotDlqAddAmt
    ) {}
}
