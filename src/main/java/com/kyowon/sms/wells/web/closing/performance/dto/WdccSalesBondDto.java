package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccSalesBondDto {

    /**
     * 목록 조회 요청
     * @param slClYm 기준월
     * @param agrgDv 집계구분(1차 작업 시 설정 정보)
     * @param sellTpCd 판매유형
     * @param sellTpDtlCd 판매유형상세
     * @param sellChnlDtl 판매채널상세코드(1차 작업 시 설정 정보)
     * @param sapPdDvCd SAP상품구분코드
     * @param cntrNo 계약번호
     * @param cntrSn 계약상세번호
     */
    @ApiModel(value = "WdccDelinquentDto-SearchReq")
    public record SearchReq(
        String slClYm,
        String agrgDv,
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtl,
        String sapPdDvCd,
        String cntrNo,
        String cntrSn
    ) {}
    @ApiModel(value = "WdccDelinquentDto-SearchRes")
    public record SearchRes(
        String slClYm,
        String slClDt,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdAtcNm,
        String cntrNo,
        String cstNo,
        String cstKnm,
        String pdCd,
        String pdNm,
        String slRcogDt,
        String preTotUcAmt,
        String preUcAmt1,
        String preUcAmt2,
        String nomSlAmt,
        String nomSlAmt1,
        String nomSlAmt2,
        String nomSlAmt3,
        String nomSlAmt4,
        String nomSlAmt5,
        String nomSlAmt6,
        String slCanAmt,
        String totSlAmt,
        String totDpAmt,
        String slBndAlrpyAmt,
        String totUcBlam,
        String ucBlam1,
        String ucBlam2,
        String borRemAmt,
        String borRemAmt1,
        String borRemAmt2,
        String borAdjAmt,
        String borAdjAmt1,
        String borAdjAmt2,
        String borAdjAmt3,
        String dpCngAmt,
        String dpCngAmt1,
        String dfaProcsAmt,
        String slClYm2,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String w1Am011,
        String w1Am012,
        String w1Am013,
        String oriSlAmt,
        String itrSlAmt,
        String svcSlAmt,
        String slAdjAmt,
        String w1Am081,
        String w1Am082,
        String w1Am09,
        String w1Am10,
        String w1Am50,
        String w1Am121,
        String w1Am122,
        String w1Am123,
        String canSlAmt,
        String w1Am05,
        String w1Am07,
        String w1Am08,
        String feeSlAmt,
        String boutNorSlAmt,
        String boutCanSlAmt,
        String cntrDpAmt,
        String instDpAmt,
        String eotUcAmt,
        String crpUcAmt,
        String boutDpAmt,
        String etcDpAmt,
        String interContNomSlAmt,
        String interContCanSlAmt,
        String interContDpAmt,
        String interContDpAmt1,
        String interContDpAmt2,
        String bfThmUcBlam,
        String bfEotDlqAddAmt,
        String bfEotBorAmt,
        String w1Am191,
        String w1Am192,
        String w1Am193,
        String w1Am194,
        String w1Am195,
        String w1Am196,
        String thmUcBlam,
        String eotBorAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (일시블, 집계)
    @ApiModel(value = "WdccDelinquentDto-SearchlumpSumAggregationRes")
    public record SearchlumpSumAggregationRes(
        String slClYm, /*실적년월*/
        String cwprep, /*전기이월*/
        String cwwa111, /* 매출 - 정상매출*/
        String cwwa121, /* 매출 - 취소매출*/
        String cwwa14, /* 매출 - 수수료매출*/
        String cwwa112, /* 매출 - 상품권_정상매출*/
        String cwwa1221, /* 매출 - 상품권_취소*/
        String sumMeAmt, /* 매출 - 매출합계*/
        String cwwa161, /*매출대사 - 계약금입금*/
        String cwwa181, /*매출대사 - 할부금입금*/
        String sumInAmt, /*매출대사 - 입금합계*/
        String cwprop, /*기말기수*/
        String cwwa201, /*법인미수입금*/
        String cwwa182, /*상품권입금*/
        String cwwa183, /*기타선수대체*/
        String cwwa113, /*시간거래_정상*/
        String cwwa123, /*시간거래_취소*/
        String cwwa164 /*시간거래_입금*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (일시블, 일자별, 주문별, 가로세로) SearchLumpSumPaymentByDateRes
    @ApiModel(value = "WdccDelinquentDto-SearchLumpSumPaymentByDateRes")
    public record SearchLumpSumPaymentByDateRes(
        String perfDt, /*실적일자*/
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세*/
        String sapPdDvCd, /*SAP상품구분코드명*/
        String cntrNo, /*계약상세번호*/
        String cstKnm, /*고객명*/
        String basePdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String slRcogDt, /*매출일자*/
        String cwprep, /*전기이월*/
        String cwwa111, /* 매출 - 정상매출*/
        String cwwa121, /* 매출 - 취소매출*/
        String cwwa14, /* 매출 - 수수료매출*/
        String cwwa112, /* 매출 - 상품권_정상매출*/
        String cwwa1221, /* 매출 - 상품권_취소*/
        String sumMeAmt, /* 매출 - 매출합계*/
        String cwwa161, /*매출대사 - 계약금입금*/
        String cwwa181, /*매출대사 - 할부금입금*/
        String sumInAmt, /*매출대사 - 입금합계*/
        String cwprop, /*기말기수*/
        String cwwa201, /*법인미수입금*/
        String cwwa182, /*상품권입금*/
        String cwwa183, /*기타선수대체*/
        String cwwa113, /*시간거래_정상*/
        String cwwa123, /*시간거래_취소*/
        String cwwa164 /*시간거래_입금*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (렌탈, 집계) SearchRentalAggregateRes
    @ApiModel(value = "WdccDelinquentDto-SearchRentalAggregateRes")
    public record SearchRentalAggregateRes(
        String slClYm, // 실적년월
        String w1Am01,
        String w1Am02,
        String w1Am05,
        String w1Am06,
        String w1Am08,
        String w1Am11,
        String w1Am13,
        String w1Am14,
        String w1Am34
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (리스, 집계) SearchLeaseAggregateRes
    @ApiModel(value = "WdccDelinquentDto-SearchLeaseAggregateRes")
    public record SearchLeaseAggregateRes(
        String slClYm, // 실적년월
        String w1Am01, //전기이월
        String w1Am04, /*--원금매출*/
        String w1Am07, /*--이자매출*/
        String w1Am58, /*--서비스매출*/
        String w1Am59, /*--합계*/
        String w1Am08, /*--매출조정*/
        String w1Am11, /*--매출대사 */
        String w1Am15, /*--대손*/
        String w1Am12 /*--미수금액*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (렌탈, 주문별, 일자별, 가로세로)SearchRentalLeaseDayPerOrderRes
    @ApiModel(value = "WdccDelinquentDto-SearchRentalDayPerOrderRes")
    public record SearchRentalDayPerOrderRes(
        String perfDt,
        String sellTpCd,
        String sellTpDtlCd,
        String sapPdDvCd,
        String cntrNo,
        String cstKnm,
        String basePdCd,
        String pdNm,
        String slRcogDt,
        String w1Am01,
        String w1Am02,
        String w1Am05,
        String w1Am06,
        String w1Am08,
        String w1Am11,
        String w1Am13,
        String w1Am14,
        String w1Am15
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (리스, 주문별, 일자별, 가로세로)SearchRentalLeaseDayPerOrderRes
    @ApiModel(value = "WdccDelinquentDto-SearchLeaseDayPerOrderRes")
    public record SearchLeaseDayPerOrderRes(
        String perfDt,
        String sellTpCd,
        String sellTpDtlCd,
        String sapPdDvCd,
        String cntrNo,
        String cstKnm,
        String basePdCd,
        String pdNm,
        String slRcogDt,
        String w1Am01,
        String w1Am04,
        String w1Am07,
        String w1Am58,
        String w1Am59,
        String w1Am11,
        String w1Am15,
        String w1Am12
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (맴버쉽, 집계)SearchMembersAggregateRes
    @ApiModel(value = "WdccDelinquentDto-SearchMemberAggregateRes")
    public record SearchMemberAggregateRes(
        String slClYm,
        String w1Am01,
        String w1Am02,
        String w1Am03,
        String w1Am06,
        String w1Am09,
        String w1Am15,
        String w1Am12
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (맴버쉽, 주문별, 일자별, 가로세로)SearchMembersDayPerOrderRes
    @ApiModel(value = "WdccDelinquentDto-SearchMemberDayPerOrderRes")
    public record SearchMemberDayPerOrderRes(
        String perfDt,
        String sellTpCd,
        String sellTpDtlCd,
        String sapPdDvCd,
        String cntrNo,
        String cstKnm,
        String basePdCd,
        String pdNm,
        String slRcogDt,
        String w1Am01, /*-- 전기이월*/
        String w1Am02, /*-- 정상매출*/
        String w1Am03, /*-- 취소매출 */
        String w1Am06, /*-- 매출합계*/
        String w1Am09, /*-- 매출대사*/
        String w1Am15, /*-- 대손*/
        String w1Am12 /*-- 미수*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (정기배송, 집계)SearchRegularDeliveryAggregateRes
    @ApiModel(value = "WdccDelinquentDto-SearchRegularDeliveryAggregateRes")
    public record SearchRegularDeliveryAggregateRes(
        String slClYm,
        String w1Am00,
        String w1Am19,
        String w1Am29,
        String w1Am15,
        String w1Am99
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 매출채권 (정기배송, 주문별, 일자별, 가로세로)SearchRegularDeliveryDayPerOrdereRes
    @ApiModel(value = "WdccDelinquentDto-SearchRegularDeliveryDayPerOrdereRes")
    public record SearchRegularDeliveryDayPerOrdereRes(

    ) {}
}
