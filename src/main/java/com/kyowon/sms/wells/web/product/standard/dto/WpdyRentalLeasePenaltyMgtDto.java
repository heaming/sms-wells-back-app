package com.kyowon.sms.wells.web.product.standard.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;

public class WpdyRentalLeasePenaltyMgtDto {

    @ApiModel(value = "WpdyRentalLeasePenaltyMgtDto-SearchReq")
    public record SearchReq(
        String prdtCateHigh, /* 대분류 */
        String prdtCateMid, /* 중분류 */
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String svcStartDt, /* 적용 시작일 */
        String svcEndDt /* 적용 종료일 */
    ) {}

    @ApiModel(value = "WpdyRentalLeasePenaltyMgtDto-SearchRes")
    public record SearchRes(
        String pdNm, /* 상품명 */

        String fstRgstUsrId,
        String fnlMdfcUsrId,
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,

        String ccamId, /* 위약금ID */
        String histStrtDtm, /* 이력시작일시 */
        String histEndDtm, /* 이력종료일시 */
        String vlStrtDtm, /* 유효시작일시 */
        String vlEndDtm, /* 유효종료일시 */
        String pdCd, /* 상품코드 */
        Long csmbCs, /* 소모품비용 */
        Long reqdCs, /* 철거비용 */
        Long rentalRntf, /* 렌탈손료 */
        Long spayRntf, /* 일시불손료 */
        BigDecimal resCcamRat, /* 잔여위약금비율 */
        String tempSaveYn, /* 임시저장여부 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    @ApiModel(value = "WpdyRentalLeasePenaltyMgtDto-SaveReq")
    public record SaveReq(
        List<WpdyRentalLeasePenaltyMgtDto.CancelChargeBase> bases
    ) {}

    @ApiModel(value = "WpdyRentalLeasePenaltyMgtDto-CancelChargeBase")
    public record CancelChargeBase(
        String ccamId, /* 위약금ID */
        String histStrtDtm, /* 이력시작일시 */
        String histEndDtm, /* 이력종료일시 */
        String vlStrtDtm, /* 유효시작일시 */
        String vlEndDtm, /* 유효종료일시 */
        String pdCd, /* 상품코드 */
        Long csmbCs, /* 소모품비용 */
        Long reqdCs, /* 철거비용 */
        Long rentalRntf, /* 렌탈손료 */
        Long spayRntf, /* 일시불손료 */
        BigDecimal resCcamRat, /* 잔여위약금비율 */
        String tempSaveYn, /* 임시저장여부 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}
}
