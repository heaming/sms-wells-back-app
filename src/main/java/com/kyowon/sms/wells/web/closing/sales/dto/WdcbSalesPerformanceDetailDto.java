package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbSalesPerformanceDetailDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchMembershipRes")
    public record SearchMembershipRes(
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
        String dlqAdamtOcAmt,
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
    // 리스매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchLeaseRes")
    public record SearchLeaseRes(
        String cstKnm,
        String cntrDtlNo,
        String slClYm,
        String pdNm,
        String cntrDt,
        String slDt,
        String col1,
        String rentalRgstCost,
        String istmAmt,
        String rentalPtrm,
        String col2,
        String rentalTn,
        String col3,
        String col4,
        String fnnLeasePcamTam,
        String col5,
        String slAggAmt,
        String col6,
        String col7,
        String ovrCtrDpAmt,
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
        String btdAtam,
        String thmAtamDpAmt,
        String thmAtamRfndAmt,
        String col18,
        String col19,
        String eotTotalAmt,
        String mlgEotPrpdAmt,
        String eotAtam,
        String btdDlqAddAmt,
        String dlqAdamtOcAmt,
        String thmCtrDlqAddAmt,
        String thmDlqDpSumAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt,
        String col20,
        String dlqMcn,
        String thmOcDlqAmt,
        String actcsDt,
        String clctamPrtnrNo,
        String col21
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchRentalRes")
    public record SearchRentalRes(
        String cstKnm,
        String cntrDtlNo,
        String slClYm,
        String pdNm,
        String col1,
        String slDt,
        String svPrd,
        String rentalRgstCost,
        String istmAmt,
        String rentalPtrm,
        String rentalPtrm2,
        String rstlYn,
        String col2,
        String prmMcn,
        String prmDscr,
        String prmDpAmt,
        String prmSlAmt,
        String col3,
        String rentalTn,
        String rentalDc,
        String col4,
        String nomSlAmt,
        String nomDscAmt,
        String col5,
        String spmtSlAmt,
        String spmtDscAmt,
        String slCtrAmt,
        String thmSlSumAmt,
        String slSumVat,
        String slAggAmt,
        String col6,
        String col7,
        String col8,
        String btdAtam,
        String thmAtamDpAmt,
        String thmAtamRfndAmt,
        String col9,
        String slBndAlrpyAmt,
        String slDpAggAmt,
        String col10,
        String eotTotalAmt,
        String eotAtam,
        String mlgEotPrpdAmt,
        String col11,
        String col12,
        String col13,
        String col14,
        String col15,
        String col16,
        String col17,
        String btdDlqAddAmt,
        String dlqAdamtOcAmt,
        String thmCtrDlqAddAmt,
        String thmDlqDpSumAmt,
        String thmDlqRfndSumAmt,
        String eotDlqAddAmt,
        String col18,
        String dlqMcn,
        String thmOcDlqAmt,
        String dfaAmt,
        String dfaDpAmt,
        String col19,
        String actcsDt,
        String clctamPrtnrNo,
        String col20
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 정기배송매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchRegularRes")
    public record SearchRegularRes(
        String cstKnm,
        String cntrDtlNo,
        String slClYmlcpkyn,
        String lcpkagT1,
        String lcPknm,
        String lcJtypNm,
        String lcJcnam,
        String pdCd,
        String pdNm,
        String sellAmt,
        String rentalAmt,
        String dscAmt,
        String lcTam1,
        String cntrPdStrtdt,
        String lcBatt,
        String slDt,
        String slDtYn,
        String sppYn,
        String lcrcnt,
        String slDc,
        String canDt,
        String nomSlAmt,
        String nomDscAmt,
        String fshDt,
        String spmtSlAmt,
        String spmtDscAmt,
        String slCtrAmt,
        String thmSlSumAmt,
        String slSumVat,
        String slAggAmt,
        String lcam18,
        String lcam19,
        String spAmt,
        String btdAtam,
        String thmAtamDpAmt,
        String thmAtamRfndAmt,
        String prpdSlAmt,
        String slBndAlrpyAmt,
        String slDpAggAmt,
        String lcam34,
        String lcam35,
        String thmUcBlam,
        String thmOcDlqAmt,
        String btdUcAmt,
        String lcmam2,
        String lcmam6,
        String lcmam3,
        String lcmam4,
        String eotUcAmt,
        String lcmam8,
        String lcmam9,
        String dlqMcn,
        String dlqAcuMcn,
        String slStpYn,
        String actcsDt,
        String clctamPrtnrNo
    ) {}
}
