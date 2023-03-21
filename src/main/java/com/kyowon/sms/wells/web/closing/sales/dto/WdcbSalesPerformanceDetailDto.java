package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbSalesPerformanceDetailDto {
    // *********************************************************
    // Search Dto
    // *********************************************************
    // 매출 상세정보 Search Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String slDt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-MembershipSearchRes")
    public record MembershipSearchRes(
        String cstKnm,
        String cntrDtlNo,
        String slClYm,
        String pdNm,
        String cntrDt,
        String cntrCnfmDtm,
        String sellAmt,
        String dscAmt,
        String col9,
        String col10,
        String prmMcn,
        String prmStrt,
        String prmEnd,
        String prmDscr,
        String prmDpAmt,
        String col16,
        String col17,
        String rentalTn,
        String rentalDc,
        String col20,
        String nomSlAmt,
        String nomDscAmt,
        String col23,
        String spmtSlAmt,
        String spmtDscAmt,
        String col26,
        String thmSlSumAmt,
        String slSumVat,
        String slCtrAmt,
        String col30,
        String col31,
        String slAggAmt,
        String prmBlamEotAmt,
        String prmDpAmt2,
        String prmRfndAmt,
        String prmRplcAmt,
        String prmSlAmt,
        String col38,
        String eotAtam,
        String thmAtamDpAmt,
        String thmAtamRfndAmt,
        String col42,
        String prpdSlAmt,
        String btdAtam,
        String slBndAlrpyAmt,
        String slDpAggAmt,
        String thmUcBlam,
        String col48,
        String col49,
        String col50,
        String col51,
        String col52,
        String col53,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqDpSumAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt,
        String col60,
        String dlqMc,
        String thmOcDlqAmt,
        String dfaAmt,
        String dfaDpAmt,
        String actcsDt,
        String clctamPrtnrNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-LeaseSearchRes")
    public record LeaseSearchRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8,
        String col9,
        String col10,
        String col11,
        String col12,
        String col13,
        String col14,
        String col15,
        String col16,
        String col17,
        String col18,
        String col19,
        String col20,
        String col21,
        String col22,
        String col23,
        String col24,
        String col25,
        String col26,
        String col27,
        String col28,
        String col29,
        String col30,
        String col31,
        String col32,
        String col33,
        String col34,
        String col35,
        String col36,
        String col37,
        String col38,
        String col39,
        String col40,
        String col41,
        String col42,
        String col43,
        String col44,
        String col45,
        String col46,
        String col47,
        String col48,
        String col49,
        String col50
    ) {}

}
