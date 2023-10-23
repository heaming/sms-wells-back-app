package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbMonthlySalesVolumeTableDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 월 매출수량 집계표 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String sellTpCd, /* 업무구분 */
        String slStartDt, /* 매출일자From */
        String slEndDt, /* 매출일자To */
        String sppMthdTpCd, /* 배달구분 */
        String sellInflwChnlDtlCd, /* 판매채널 */
        String copnDvCd /* 개인법인구분 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 - 렌탈 조회 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchRes")
    public record SearchRentalRes(
        String sapMatCd, /* SAP상품코드 */
        String matPdCd, /* 수불코드 */
        String pdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String svPdTpCd, /* 용도구분 */
        String cnt /* 건수(건) */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 - 일시불/할부 조회 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchRes")
    public record SearchPaymentRes(
        String sapMatCd, /* SAP상품코드 */
        String matPdCd, /* 수불코드 */
        String basePdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String stlmTpCd1, /* 완불 */
        String stlmTpCd2, /* 환불 */
        String tot /* 전체 */
    ) {}

}
