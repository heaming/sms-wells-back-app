package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaStockAcinspRgstMngtDto {

    @Builder
    @ApiModel("WsnaStockAcinspRgstMngtDto-SearchWareReq")
    public record SearchWareReq(
        @NotBlank
        String baseYm,
        // 창고구분코드
        @NotBlank
        String wareDvCd,

        // 창고세부구분코드
        String wareDtlDvCd
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 월별 재고실사 등록 관리 Search Request Dto
    @Builder
    @ApiModel("WsnaStockAcinspRgstMngtDto-SearchReq")
    public record SearchReq(
        // 기준년월
        @NotBlank
        String baseYm,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 창고상세구분
        String wareDtlDvCd,
        // 상위창고번호

        String hgrWareNo,
        // 창고번호
        String searchWareNo,
        // 상태구분
        String useYn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 재고실사 등록 관리 Search Result Dto
    @ApiModel("WsnaStockAcinspRgstMngtDto-SearchRes")
    public record SearchRes(
        String apyYm, // 적용년월
        String wareNo, // 창고번호
        String wareNm, /* 창고명 */
        String itmKnd, /* 품목구분 */
        String sapCd, /* SAP코드 */
        String itmPdCd, /* 품목상품코드 */
        String pdAbbrNm, /* 품목약어명 */
        int acinspQty, /* 실사수량 */
        int eotStoc, /* 기말수량 */
        int minusQty, /* 차이수량 */
        String acinspRmkCn, /* 실사비고 */
        String statusT, /* 상태값 */
        String stocAcinspAkId, /* 재고실사요청ID */
        String cnfmdt, /* 확정일자 */
        int cnfmPitmEotStocQty, /* 확정시점기말재고수량 */
        int cnfmPitmStrGapQty, /* 확정시점입고차이수량 */
        int cnfmPitmOstrGapQty, /* 확정시점출고차이수량 */
        int diffQty, /* 확정차이수량 */
        String iostRfdt /* 입출고반영일자 */

    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveAcinspReq")
    public record SaveAcinspReq(
        @NotBlank
        String apyYm, /* 적용년월 */
        @NotBlank
        String wareNo, /* 창고번호 */
        String wareNm, /* 창고명 */
        String itmKnd, /* 품목구분 */
        String sapCd, /* SAP코드 */
        @NotBlank
        String itmPdCd, /* 품목상품코드 */
        String pdAbbrNm, /* 품목약어명 */
        int acinspQty, /* 실사수량 */
        int eotStoc, /* 기말수량 */
        int minusQty, /* 차이수량 */
        String acinspRmkCn, /* 실사비고 */
        String cnfmdt, /* 상태값 */
        int cnfmPitmEotStocQty, /* 확정시점기말재고수량 */
        int cnfmPitmStrGapQty, /* 확정시점입고차이수량 */
        int cnfmPitmOstrGapQty, /* 확정시점출고차이수량 */
        int diffQty, /* 확정차이수량 */
        String iostRfdt, /* 입출고반영일자 */
        String stocAcinspAkId /* 재고실사요청ID */
    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveAcinspCnfmReq")
    public record SaveAcinspCnfmReq(
        @NotBlank
        String apyYm, /* 적용년월 */
        @NotBlank
        String wareNo, /* 창고변호 */
        String wareNm, /* 창고명 */
        String itmKnd, /* 품목구분 */
        String sapCd, /* SAP코드 */
        String itmPdCd, /* 품목상품코드 */
        String pdAbbrNm, /* 품목명 */
        int acinspQty, /* 실사수량 */
        int eotStoc, /* 기말수량 */
        int minusQty, /* 차이수량 */
        String acinspRmkCn, /* 실사비고 */
        String cnfmdt, /* 상태값 */
        int cnfmPitmEotStocQty, /* 확정시점기말재고수량 */
        int cnfmPitmStrGapQty, /* 확정시점입고차이수량 */
        int cnfmPitmOstrGapQty, /* 확정시점출고차이수량 */
        int diffQty, /* 확정차이수량 */
        String iostRfdt, /* 입출고반영일자 */
        String acinspAkId, /* 실사요청ID */
        //조회조건 데이터 매핑
        String baseYm, /* 기준년월 */
        String wareDvCd, /* 창고구분코드 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String searchWareNo, /* 조회창고번호 */
        String useYn /* 사용여부 */
    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveCancelReq")
    public record SaveCancelReq(
        @NotBlank
        String apyYm, /* 적용년월 */
        @NotBlank
        String wareNo, /* 창고변호 */
        String wareNm, /* 창고명 */
        String itmKnd, /* 품목구분 */
        String sapCd, /* SAP코드 */
        String itmPdCd, /* 품목상품코드*/
        String pdAbbrNm, /* 품목명 */
        int acinspQty, /* 실사수량 */
        int eotStoc, /* 기말수량 */
        int minusQty, /* 차이수량 */
        String acinspRmkCn, /* 실사비고 */
        String cnfmdt, /* 상태값 */
        int cnfmPitmEotStocQty, /* 확정시점기말재고수량 */
        int cnfmPitmStrGapQty, /* 확정시점입고차이수량 */
        int cnfmPitmOstrGapQty, /* 확정시점출고차이수량 */
        int diffQty, /* 확정차이수량 */
        String iostRfdt, /* 입출고반영일자 */
        String acinspAkId, /* 실사요청ID */
        //조회조건 데이터 매핑
        String baseYm, /* 기준년월 */
        String wareDvCd, /* 창고구분코드 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String searchWareNo, /* 조회창고번호 */
        String useYn/* 사용여부 */
    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveReq")
    public record SaveReq(
        String apyYm, /* 적용년월 */
        String wareNo, /* 창고변호 */
        String wareNm, /* 창고명 */
        String itmKnd, /* 품목구분 */
        String sapCd, /* SAP코드 */
        String itmPdCd, /* 품목상품코드*/
        String pdAbbrNm, /* 품목명 */
        int acinspQty, /* 실사수량 */
        int eotStoc, /* 기말수량 */
        int minusQty, /* 차이수량 */
        String acinspRmkCn, /* 실사비고 */
        String cnfmdt, /* 상태값 */
        int cnfmPitmEotStocQty, /* 확정시점기말재고수량 */
        int cnfmPitmStrGapQty, /* 확정시점입고차이수량 */
        int cnfmPitmOstrGapQty, /* 확정시점출고차이수량 */
        int diffQty, /* 확정차이수량 */
        String iostRfdt, /* 입출고반영일자 */
        String acinspAkId, /* 실사요청ID */
        //조회조건 데이터 매핑
        String baseYm, /* 기준년월 */
        String wareDvCd, /* 창고구분코드 */
        String wareDtlDvCd, /* 창고상세구분코드 */
        String searchWareNo, /* 조회창고번호 */
        String useYn/* 사용여부 */
    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String apyYm, /* 적용년월 */
        @NotBlank
        String wareNo, /* 창고번호 */
        String itmPdCd /* 품목상품코드 */
    ) {}
}
