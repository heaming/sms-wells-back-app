package com.kyowon.sms.wells.web.product.standard.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 상품 렌탈/리스 위약금관리 DTO
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
public class WpdyRentalLeasePenaltyMgtDto {

    @ApiModel(value = "WpdyRentalLeasePenaltyMgtDto-SearchReq")
    public record SearchReq(
        String prdtCateHigh, /* 대분류 */
        String prdtCateMid, /* 중분류 */
        String prdtCateLow, /* 하분류 */
        String prdtCateLowDtl, /* 세분류 */
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String svcStartDt, /* 적용 시작일 */
        String svcEndDt /* 적용 종료일 */
    ) {}

    @ApiModel(value = "WpdyRentalLeasePenaltyMgtDto-SearchRes")
    public record SearchRes(
        String pdNm, /* 상품명 */

        String fstRgstUsrId, /* 등록자 */
        String fnlMdfcUsrId, /* 수정자 */
        String fstRgstDtm, /* 등록일 */
        String fstRgstUsrNm, /* 등록자명 */
        String fnlMdfcDtm, /* 수정일 */
        String fnlMdfcUsrNm, /* 수정자명 */

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
        @NotEmpty
        List<WpdyRentalLeasePenaltyMgtDto.CancelChargeBase> bases /* 기본정보 */
    ) {}

    @ApiModel(value = "WpdyRentalLeasePenaltyMgtDto-CancelChargeBase")
    public record CancelChargeBase(
        String ccamId, /* 위약금ID */
        String histStrtDtm, /* 이력시작일시 */
        String histEndDtm, /* 이력종료일시 */
        String vlStrtDtm, /* 유효시작일시 */
        String vlEndDtm, /* 유효종료일시 */
        @NotBlank
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
