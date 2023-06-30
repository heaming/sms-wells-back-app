package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbCancelPresentStateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 취소현황 Search Request Dto
    @Builder
    @ApiModel("WctbCancelPresentStateDto-SearchReq")
    public record SearchReq(
        String ogCd,
        String dtDiv,
        String cancelFromDt,
        String cancelToDt,
        String cntrNo,
        String cntrSn,
        String sellOgTpCd,
        String basePdCd,
        String pdGdCd,
        String rgstUsrEpNo,
        String clctamPrtnrNo,
        String printDiv,
        String copnDvCd,
        String sellTpCd,
        String sellTpDtlCd,
        String alncmpCd,
        String cntrStatChRsonCd,
        String reqdDiv,
        String wellsFarmCancelDiv,
        String pdHclsfId,
        String pdMclsfId
    ) {
    }

    // *********************************************************
    // Rental - Result Dto
    // *********************************************************
    // 렌탈 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchRentalRes")
    public record SearchRentalRes(
        String ogCd,
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String cntrNo,
        String cntrSn,
        String cntrCstKnm,
        String mPdCd,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String istDt,
        String canDt,
        String pdUseDc,
        String pdGdCd,
        String reqdAkDt,
        String reqdDt,
        String reqdIchrNm,
        Integer rgstCostAmt,
        Integer rgstCostRfndAmt,
        Integer slAmt,
        Integer slCtrAmt,
        Integer slAggAmt,
        Integer dpTotAmt,
        Integer slDpAggAmt,
        Integer totPrpdAmt,
        Integer ucBlamAmt,
        Integer csmbCostBorAmt,
        Integer reqdCsBorAmt,
        Integer borAmt,
        Integer lsRntf,
        Integer totRfndAmt,
        String clctamPrtnrKnm
    ) {
    }

    // *********************************************************
    // RegularShipping - Result Dto
    // *********************************************************
    // 정기배송 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchRegularShippingRes")
    public record SearchRegularShippingRes(
        String cntrNo,
        String cntrSn,
        String cntrCstKnm,
        String bzrno,
        String txinvPblOjYn,
        String sellTpDtlNm,
        String pdNm,
        String cntrPdStrtdt,
        String rsgAplcDt,
        String rsgFshDt,
        String farmEnddt,
        String pdUseDc,
        Integer rentalAmt,
        Integer frisuBfsvcPtrmN,
        Integer canCtrAmt,
        Integer totRfndAmt,
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String cntrStatChRsonCd,
        String cntrStatChRsonNm,
        String ojDtlCntrNo,
        String ojDtlCntrSn
    ) {
    }


    // *********************************************************
    // Membership - Result Dto
    // *********************************************************
    // Membership 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String cntrNo,
        String cntrSn,
        String sellOgTpCd,
        String ogTpNm,
        String prtnrNo,
        String prtnrKnm,
        String ogCd,
        String ogNm,
        String hooPrtnrNo,
        String cntrCstKnm,
        String copnDvCd,
        String copnDvNm,
        String zip,
        String adr,
        String dtlAdr,
        String pdHclsfNm,
        String pdMclsfNm,
        String basePdCd,
        String pdNm,
        String txinvPblOjYn,
        String dpTpNm,
        String istDt,
        String rsgAplcDt,
        String rsgFshDt,
        String pdUseDc,
        String cntrStatChRsonCd,
        String cntrStatChRsonNm,
        String ojDtlCntrNo,
        String ojDtlCntrSn,
        String slSumAmt,
        String slCtrAmt,
        String totPrpdAmt,
        String borAmt,
        String lsRntf,
        String totRfndAmt,
        String fstRgstUsrId,
        String fstRgstDtm,
        String bizSpptPrtnrNo,
        String bizSpptPrtnrNm
    ) {
    }
}



