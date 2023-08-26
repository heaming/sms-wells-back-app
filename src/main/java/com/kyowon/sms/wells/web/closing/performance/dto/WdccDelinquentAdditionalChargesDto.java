package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdccDelinquentAdditionalChargesDto {
    /**
     * 매출채권-연체가산금 요청
     * @param slClYm 기준년월
     * @param agrgDv 집계구분
     * @param sellTpCd 판매유형
     * @param sellTpDtlCd 판매상세유형
     * @param sellChnlDtlCd 판매채널
     * @param sapPdDvCd sap상품구분코드
     */
    @ApiModel(value = "WdccDelinquentAdditionalChargesDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String slClYm,
        @NotBlank
        String agrgDv,
        @NotBlank
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtlCd, /*판매채널*/
        String sapPdDvCd /*SAP상품구분코드*/
    ) {}

    /**
     * 매출채권-연체가산금 응답
     * @param perfYm 실적년월
     * @param sellTpCd 판매유형코드
     * @param sellTpCdNm 판매유형명
     * @param sellTpDtlCd 판매유형상세코드
     * @param sellTpDtlCdNm 판매유형상세명
     * @param sapPdDvCd sap상품구분코드
     * @param sapPdAtcNm sap상품구분명
     * @param cntrNo 계약상세번호(cntrNO-cntrSn)
     * @param cstKnm 고객명
     * @param btdDlqAddAmt 전기이월
     * @param thmOcDlqAddAmt 당월발생
     * @param thmCtrDlqAddAmt 당월공제
     * @param thmDlqAddDpSumAmt 당월입금
     * @param thmDlqAddRfndSumAmt 당월환불
     * @param eotDlqAddAmt 기말잔액
     */
    @ApiModel(value = "WdccDelinquentAdditionalChargesDto-SearchRes")
    public record SearchRes(
        String perfYm,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdAtcNm,
        String cntrNo,
        String cstKnm,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt
    ) {}

}
