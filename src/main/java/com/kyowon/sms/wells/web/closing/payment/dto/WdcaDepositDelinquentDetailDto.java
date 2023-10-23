package com.kyowon.sms.wells.web.closing.payment.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-CL-U-0017M02 입금 연체 상세조회 / W-CL-U-0017M01 입금 연체 상세-계약별 상세조회
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-05-22
 */
public class WdcaDepositDelinquentDetailDto {
    /**
     * 입금 연체 상세 조회 dto
     * @param perfYm 실적년월
     * @param dlqDv 연체구분
     * @param dlqMcnt 연체개월
     * @param sellTpCd 판매유형
     * @param ogTp 조직유형
     * @param inqrDv 조회구분
     * @param dgr1LevlOgCd 조직레벨-총괄단
     * @param dgr2LevlOgCd 조직레벨-지역단
     * @param dgr3LevlOgCd 조직레벨-지점
     * @param copnDvCd 법인격구분코드
     * @param prtnrNo 파트너번호
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param cntrCstNo 고객번호
     */
    @ApiModel("WdcaDepositDelinquentDetailDto-SearchReq")
    public record SearchReq(
        String perfYm, /*실적년월*/
        String dlqDv, /*연체구분*/
        List<String> dlqMcnt, /*연체개월*/
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세*/
        String ogTp, /*조직유형*/
        String inqrDv, /*조회구분*/
        String dgr1LevlOgCd, /*1차레벨조직코드*/
        String dgr2LevlOgCd, /*2차레벨조직코드*/
        String dgr3LevlOgCd, /*3차레벨조직코드*/
        String copnDvCd, /*법인격구분코드*/
        String prtnrNo, /*파트너번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrCstNo /*고객번호*/
    ) {}

    /**
     * 입금 연체 상세 조회 검색결과 dto
     */
    @ApiModel("WdcaDepositDelinquentDetailDto-SearchRes")
    public record SearchRes(
        String ogCd, /*조직코드*/
        String ogNm, /*조직명*/
        String dgr1LevlOgCd, /*1차레벨조직코드*/
        String dgr1LevlOgNm, /*1차레벨조직명*/
        String dgr2LevlOgCd, /*2차레벨조직코드*/
        String dgr2LevlOgNm, /*2차레벨조직명*/
        String dgr3LevlOgCd, /*3차레벨조직코드*/
        String dgr3LevlOgNm, /*3차레벨조직명*/
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*파트너한글명*/
        String sellTpCd, /*판매유형코드*/
        String sellTpNm, /*판매유형명*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellTpDtlNm, /*판매유형상세명*/
        String totAccN, /*총 계정수*/
        String ucamTam, /*미수금 총액*/
        String thmNwAccN, /*계정수*/
        String thmNwDpAmt, /*입금금액*/
        String nomUcAmt, /*미수금액*/
        String nomAccN, /*계정수*/
        String nomDpAmt, /*입금금액*/
        String nomDpRt, /*입금률*/
        String dlqAmt, /*연체금액*/
        String dlqAccN, /*계정수*/
        String dlqDpAmt, /*입금금액*/
        String ucCprDlqRt, /*미수대비연체율*/
        String totDpAmt, /*총입금액*/
        String dpRt, /*입금률*/
        String bilAgg, /*매출 누계*/
        String dpAgg, /*입금 누계*/
        String dlqRtSum /*연체율계*/
    ) {}

    /**
     * 입금 연체 상세-계약별 상세조회 dto
     * @param perfYm 실적년월
     * @param dlqDv 연체구분
     * @param dlqMcnt 연체개월
     * @param sellTpCd 판매유형
     * @param ogTp 조직유형
     * @param dgr1LevlOgCd 조직레벨-총괄단
     * @param dgr2LevlOgCd 조직레벨-지역단
     * @param dgr3LevlOgCd 조직레벨-지점
     * @param copnDvCd 개인/법인구분
     * @param prtnrNo 파트너번호
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param cntrCstNo 고객번호
     */
    @ApiModel("WdcaDepositDelinquentDetailDto-SearchContractReq")
    public record SearchContractReq(
        String perfYm, /*실적년월*/
        String dlqDv, /*연체구분*/
        List<String> dlqMcnt, /*연체개월*/
        String sellTpCd, /*판매유형코드*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String ogTp, /*조직유형*/
        String dgr1LevlOgCd, /*1차레벨조직코드*/
        String dgr2LevlOgCd, /*2차레벨조직코드*/
        String dgr3LevlOgCd, /*3차레벨조직코드*/
        String copnDvCd, /*법인격구분코드*/
        String prtnrNo, /*파트너번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrCstNo /*계약고객번호*/
    ) {}

    /**
     * 입금 연체 상세-계약별 상세조회 검색결과 dto
     */
    @ApiModel("WdcaDepositDelinquentDetailDto-SearchContractRes")
    public record SearchContractRes(
        String sellTpCd, /*판매유형코드*/
        String sellTpNm, /*판매유형코드명*/
        String sellTpDtlCd, /*판매유형상세코드*/
        String sellTpDtlNm, /*판매유형상세코드명*/
        String cntrDtlNo, /*계약번호+계약일련번호*/
        String cntrCstNo, /*계약고객번호*/
        String cstKnm, /*고객한글명*/
        String slClDt, /*매출마감일자*/
        String basePdCd, /*기준상품코드*/
        String pdNm, /*상품명*/
        String dlqMcn, /*연체개월수*/
        String eotDlqAmt, /*기말연체금액*/
        String billingAmount, /*매출누계금액*/
        String depositAmount, /*입금금액*/
        String uncollectedAmount, /*미수금액*/
        String dpTpCd, /*입금유형코드*/
        String mpyBsdt, /*납부기준일자*/
        String approveCode, /**/
        String nonPaymentReason, /**/
        String slAggAmt, /*매출누계금액*/
        String depositAggregate, /*입금누계액*/
        String prtnrKnm, /*파트너한글명*/
        String prtnrNo, /*파트너번호*/
        String fstCntrDt, /*최초계약일자*/
        String pstnDvCd, /*직급구분코드*/
        String rsbDvCd, /*직책구분코드*/
        String dgr3LevlOgCd, /*3차레벨조직코드*/
        String dgr3LevlDgPrtnrNm, /*3차레벨대표파트너명*/
        String bldNm, /*빌딩명*/
        String dgr1LevlOgCd, /*1차레벨조직코드*/
        String dgr2LevlOgCd, /*2차레벨조직코드*/
        String insidePurchase, /*내부구매*/
        String clctamPrtnrNo, /*집금파트너번호*/
        String clctamPrtnrKnm, /*파트너한글명*/
        String crpMpno, /*법인휴대전화번호*/
        String mmIstmAmt /*월할부금액*/
    ) {}

}
