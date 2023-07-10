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
     * @param copnDvCd 개인/법인구분
     * @param prtnrNo 파트너번호
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param cntrCstNo 고객번호
     */
    @ApiModel("WdcaDepositDelinquentDetailDto-SearchReq")
    public record SearchReq(
        String perfYm,
        String dlqDv,
        List<String> dlqMcnt,
        String sellTpCd,

        String sellTpDtlCd,
        String ogTp,
        String inqrDv,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String copnDvCd,
        String prtnrNo,
        String cntrNo,
        String cntrSn,
        String cntrCstNo
    ) {}

    /**
     * 입금 연체 상세 조회 검색결과 dto
     */
    @ApiModel("WdcaDepositDelinquentDetailDto-SearchRes")
    public record SearchRes(
        String ogCd,
        String ogNm,
        String dgr1LevlOgCd,
        String dgr1LevlOgNm,
        String dgr2LevlOgCd,
        String dgr2LevlOgNm,
        String dgr3LevlOgCd,
        String dgr3LevlOgNm,
        String prtnrNo,
        String prtnrKnm,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlNm,
        String totAccN,
        String ucamTam,
        String thmNwUcAmt,
        String thmNwAccN,
        String thmNwDpAmt,
        String thmNwDpRt,
        String nomUcAmt,
        String nomAccN,
        String nomDpAmt,
        String nomDpRt,
        String dlqAmt,
        String dlqAccN,
        String dlqDpAmt,
        String ucCprDlqRt,
        String totDpAmt,
        String dpRt,
        String bilAgg,
        String dpAgg,
        String dlqRtSum
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
        String perfYm,
        String dlqDv,
        List<String> dlqMcnt,
        String sellTpCd,
        String sellTpDtlCd,
        String ogTp,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String copnDvCd,
        String prtnrNo,
        String cntrNo,
        String cntrSn,
        String cntrCstNo
    ) {}

    /**
     * 입금 연체 상세-계약별 상세조회 검색결과 dto
     */
    @ApiModel("WdcaDepositDelinquentDetailDto-SearchContractRes")
    public record SearchContractRes(
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlNm,
        String cntrDtlNo,
        String cntrCstNo,
        String cstKnm,
        String slClDt,
        String basePdCd,
        String pdNm,
        String dlqMcn,
        String eotDlqAmt,
        String billingAmount,
        String depositAmount,
        String uncollectedAmount,
        String dpTpCd,
        String mpyBsdt,
        String approveCode,
        String nonPaymentReason,
        String slAggAmt,
        String depositAggregate,
        String prtnrKnm,
        String prtnrNo,
        String fstCntrDt,
        String pstnDvCd,
        String rsbDvCd,
        String dgr3LevlOgCd,
        String dgr3LevlDgPrtnrNm,
        String bldNm,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String insidePurchase,
        String clctamPrtnrNo,
        String clctamPrtnrKnm,
        String crpMpno,
        String mmIstmAmt
    ) {}

}
