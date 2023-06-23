package com.kyowon.sms.wells.web.closing.payment.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-CL-U-0018M01 영업부 입금/연체 현황
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-06-20
 */
public class WdcaBusinessDepositDelinquentDto {
    /**
     * 입금 연체 현황 조회 dto
     * @param baseYm 기준년월
     * @param dgr1LevlOgCd 조직레벨-총괄단
     * @param dgr2LevlOgCd 조직레벨-지역단
     * @param dgr3LevlOgCd 조직레벨-지점
     */
    @ApiModel("WdcaBusinessDepositDelinquentDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd
    ) {}

    /**
     * 입금 연체 현황-검색결과 dto
     */
    @ApiModel("WdcaBusinessDepositDelinquentDto-SearchRes")
    public record SearchRes(
        String dgr2LevlOgCd,
        String dgr2LevlDgPrtnrNm,
        String dgr3LevlOgCd,
        String dgr3LevlDgPrtnrNm,
        String dgr3LevlDgPrtnrNo,
        String prtnrKnm,
        String prtnrNo,
        String cltnDt,
        String nowDgr3LevlOgCd,
        String nowDgr3LevlDgPrtnrNm,
        String nowDgr3LevlDgPrtnrNo,
        String nowPrtnrKnm,
        String nowPrtnrNo,
        String nowCltnDt,
        String sellTpNm,
        String cntrDtlNo,
        String cstNo,
        String cstKnm,
        String pdNm,
        String cntrCnfmDtm,
        String istDtm,
        String rentalAmt,
        String istmMcn,
        String billing3,
        String billing2,
        String billing1,
        String billingSum,
        String deposit3,
        String deposit2,
        String deposit1,
        String depositSum,
        String delinquent3,
        String delinquent2,
        String delinquent1,
        String thisMonth,
        String delinquentYn
    ) {}

}
