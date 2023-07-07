package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbCancelBaseDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 취소등록 Search Request Dto
    @Builder
    @ApiModel("WctbCancelBaseDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String cstNo,
        String dm
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 취소등록 Search Result Dto
    @ApiModel("WctbCancelBaseDto-SearchRes")
    public record SearchRes(
        String sellTpCd,
        String sellTpNm,
        String cntrCnfmDt,
        String cntrNo,
        String cntrSn,
        String copnDvNm,
        String cntrCstNo,
        String cntrCstKnm,
        String pdNm,
        String cntrGbn,
        String hooPrtnrNm,
        String hooPrtnrNo,
        String ogCd,
        String basePdCd,
        String stplPtrm,
        String cntrPdStrtdt,
        String cntrAmt,
        String cntramDscAmt,
        String cntrTam,
        String pdBaseAmt,
        String cntrPtrm,
        String fnlAmt,
        String dscAmt,
        String rstlPtrm,
        String stplDscAmt,
        String stplStrtdt,
        String stplEnddt,
        String nomSlAmt,
        String rentalDc,
        String slDc,
        String chgDt,
        String spmtSlAmt,
        String nomDscAmt,
        String canCtrAmt,
        String addSrv,
        String spmtDscAmt,
        String slCtrAmt,
        String thmSlSumAmt,
        String slSumVat,
        String slAggAmt,
        String thmPaiamAmt,
        String thmSrvAmt,
        String eotPcamBlam,
        String btdDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAmt

    ) {}
}
