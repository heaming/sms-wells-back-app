package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbRentalNewRequidationAggregateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈 신규/철거 집계현황 조회 Search Request Dto
    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchReq")
    public record SearchReq(
        String startDt, /* from매출일자 */
        String endDt, /* to매출일자 */
        String adrpcTpCd, /* 주소지유형코드 */
        String prtnrBzsCd, /* 파트너업체코드 */
        String slYm /* 매출년월 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈 신규/철거 집계현황 조회 Search Result Dto
    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchRes")
    public record SearchRes(
        String divCdNm, /* 구분 */
        String divDtlCdNm, /* 상세구분 */
        String qty, /* 수량 */
        String divCd, /* 구분코드 */
        String divDtlCd /* 상세구분코드 */
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈 신규/철거 집계현황 상세조회 Search Request Dto
    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchDetailReq")
    public record SearchDetailReq(
        String startDt, /* from매출일자 */
        String endDt, /* to매출일자 */
        String adrpcTpCd, /* 주소지유형코드 */
        String prtnrBzsCd, /* 파트너업체코드 */
        String slYm, /* 매출년월 */
        String divCd, /* 구분코드 */
        String divDtlCd /* 상세구분코드 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈 신규/철거 집계현황 상세조회 Search Result Dto
    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchDetailRes")
    public record SearchDetailRes(
        String cntrNo, /* 계약번호 */
        String sapAssetNo, /* SAP주문번호 */
        String cstKnm, /* 고객명 */
        String sapMatCd, /* SAP품목코드 */
        String matPdCd, /* 수불코드 */
        String pdNm, /* 제품명 */
        String istDt, /* 설치일자 */
        String cntrCanDtm, /* 취소일자 */
        String reqdDt, /* 철거일자 */
        String prtnrBzsCd, /* 파트너업체코드 */
        String prtnrBzsNm /* 파트너업체명 */
    ) {}
}
