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
        String baseYm, /*기준년월*/
        @NotBlank
        String dgr1LevlOgCd, /*1차레벨조직코드*/
        String dgr2LevlOgCd, /*2차레벨조직코드*/
        String dgr3LevlOgCd /*3차레벨조직코드*/
    ) {}

    /**
     * 입금 연체 현황-검색결과 dto
     */
    @ApiModel("WdcaBusinessDepositDelinquentDto-SearchRes")
    public record SearchRes(
        String dgr2LevlOgCd, /*접수당시 지역단 코드*/
        String dgr2LevlDgPrtnrNm, /* 접수당시 지역단장 */
        String dgr3LevlOgCd, /*접수당시 지점코드*/
        String dgr3LevlDgPrtnrNm, /* 접수당시 지점장 */
        String dgr3LevlDgPrtnrNo, /* 접수당시 번호 */
        String prtnrKnm, /*접수당시 판매자명*/
        String prtnrNo, /*접수당시 번호*/
        String cltnDt, /*접수당시 해약일자*/
        String nowDgr3LevlOgCd, /*현재소속 지점코드*/
        String nowDgr3LevlDgPrtnrNm, /*현재소속 지점장*/
        String nowDgr3LevlDgPrtnrNo, /*현재소속 번호*/
        String nowPrtnrKnm, /*현재소속 판매자명*/
        String nowPrtnrNo, /*현재소속 번호*/
        String nowCltnDt, /*현재소속 해약일자*/
        String sellTpNm, /*업무구분*/
        String cntrDtlNo, /*계약상세번호*/
        String cstNo, /*고객번호*/
        String cstKnm, /*고객명*/
        String pdNm, /*제품명*/
        String cntrCnfmDtm, /*계약일자*/
        String istDtm, /*설치일자*/
        String rentalAmt, /*월 렌탈료*/
        String istmMcn, /*의무약정*/
        String billing3, /*3개월전 청구금액*/
        String billing2, /*2개월전 청구금액*/
        String billing1, /*1개월전 청구금액*/
        String billingSum, /*청구합계*/
        String deposit3, /*3개월전 입금액*/
        String deposit2, /*2개월전 입금액*/
        String deposit1, /*1개월전 입금액*/
        String depositSum, /*입금합계*/
        String delinquent3, /*3개월전 연체금액*/
        String delinquent2, /*2개월전 연체금액*/
        String delinquent1, /*1개월전 연체금액*/
        String thisMonth, /*당월입금*/
        String delinquentYn /*연체여부*/
    ) {}

}
