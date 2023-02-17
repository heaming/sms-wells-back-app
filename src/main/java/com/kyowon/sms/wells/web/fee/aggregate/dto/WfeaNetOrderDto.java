package com.kyowon.sms.wells.web.fee.aggregate.dto;

import io.swagger.annotations.ApiModel;

import lombok.Builder;

public class WfeaNetOrderDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Request Dto

    @Builder
    @ApiModel(value = "WfeaNetOrderDto-SearchReq")
    public record SearchReq(
        //구분
        String dv,
        //제품유형
        String pdctTp,
        //상품코드 시작
        String pdCdStrt,
        //상품코드 종료
        String pdCdEnd,
        //매출일자 시작
        String slDtStrt,
        //매출일자 종료
        String slDtEnd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Result Dto
    @ApiModel(value = "WfeaNetOrderDto-SearchRes")
    public record SearchRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8,
        String col9,
        String col10,
        String col11,
        String col12,
        String col13,
        String col14,
        String col15,
        String col16,
        String col17,
        String col18,
        String col19,
        String col20,
        String col21,
        String col22,
        String col23,
        String col24,
        String col25,
        String col26,
        String col27,
        String col28,
        String col29,
        String col30,
        String col31,
        String col32,
        String col33,
        String col34,
        String col35,
        String col36,
        String col37
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfeaNetOrderDto-SaveReq")
    public record SaveReq(
        String Lcnam2, /* 수당내역 */
        String Lcgun0, /* 구분０ */
        String Lcpseq, /* 출력순서 */
        String Lcamt1a, /* 금액  */
        String Lcamt1b, /* 추가지급*/
        String Lcamt1c /* 수당공제*/
    ) {}
}
