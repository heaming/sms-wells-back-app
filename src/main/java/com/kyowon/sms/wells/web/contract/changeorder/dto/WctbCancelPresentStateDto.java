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
        String ogCd, // 소속구분
        String dtDiv, // 일자검색구분
        String cancelFromDt, // 시작일
        String cancelToDt, // 종료일
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련변호
        String sellOgTpCd, // 판매구분
        String basePdCd, // 상품콛,
        String pdGdCd, // 상품등급
        String rgstUsrEpNo, // 등록담당자사번
        String clctamPrtnrNo, // 집금담당
        String printDiv, // 출력구분
        String copnDvCd, // 계약구분
        String sellTpCd, // 판매유형
        String sellTpDtlCd, // 판매세부
        String alncmpCd, // 상조취소
        String cntrStatChRsonCd, // 취소유형
        String reqdDiv, // 철거구
        String wellsFarmCancelDiv, // 웰스팜취소구분
        String pdHclsfId, // 상품대분류
        String pdMclsfId // 상품중분류
    ) {}

    // *********************************************************
    // Rental - Result Dto
    // *********************************************************
    // 렌탈 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchRentalRes")
    public record SearchRentalRes(
        String ogCd,
        String ogNm,
        String seq,
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
    ) {}

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
    ) {}

    // *********************************************************
    // Single payment - Result Dto
    // *********************************************************
    // Single payment 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchSinglePayRes")
    public record SearchSinglePayRes(
        String cntrNo,
        String cntrSn
    ) {}

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
    ) {}
}



