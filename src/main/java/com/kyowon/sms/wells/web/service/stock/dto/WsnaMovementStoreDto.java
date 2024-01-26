package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0109M01 이동입고현황
 * </pre>
 *
 * @author  songTaeSung
 * @since 2023.02.13
 */
public class WsnaMovementStoreDto {
    @Builder
    @ApiModel("WsnaMovementStoreDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String stStrDt, /* 시작일자FROM */
        @NotBlank
        String edStrDt, /* 시작일자TO */
        String strTpCd, /* 접수유형 */
        String wareDvCd, /* 창고구분코드 */
        String strOjWareNo /* 입고대상창고번호 */

    ) {}

    @ApiModel("WsnaMovementStoreDto-SearchRes")
    public record SearchRes(
        String strTpCd, /* 입고유형코드 */
        String strTpNm, /* 입고유형명 */
        String strWareNo, /* 입고창고번호 */
        String strWareNm, /* 입고창고명 */
        String strRgstDt, /* 입고등록일자 */
        String dlvgDlpnrNo, /* 납품거래처번호 */
        String itmStrNo, /* 품목입고번호 */
        int strSn, /* 입고순번 */
        String ostrTpCd, /* 출고유형코드 */
        String ostrWareNo, /* 출고창고번호 */
        String ostrWareNm, /* 출고창고명 */
        String strHopDt, /* 입고희망일자 */
        String ostrDt, /* 출고일자 */
        String itmOstrNo, /* 품목출고번호 */
        int ostrSn, /* 출고순번 */

        String wareDtlDvCd /* 입고창고상세구분 */

    ) {}

    @ApiModel("WsnaMovementStoreDto-MovementRes")
    public record MovementRes(
        String strTpCd, /*입고구분코드*/
        String strRgstDt, /*입고일자*/
        String itmStrNo, /*품목입고번호*/
        String itmPdNm, /*품목상품명*/
        String strSn, /*입고일련번호*/
        String strWareNo, /*입고창고번호*/
        String wareNm, /*입고창고명*/
        String ostrWareNo, /*출고창고번호*/
        String ostrWareNm, /*출고창고명*/
        String ostrSn, /*출고일련번호*/
        String strHopDt, /* 입고희망일자 */
        String wareDtlDvCd /* 입고창고상세구분 */
    ) {}
    @ApiModel("WsnaMovementStoreDto-MovementOstrRes")
    public record MovementOstrRes(
        String itmOstrNo, /* 품목출고번호 */
        String wareMngtPrtnrNo, /* 창고관리파트너번호 */
        String ostrTpCd, /* 출고유형코드 */
        String ostrWareNo, /* 출고창고번호 */
        String ostrDt, /* 출고일자 */
        String strWareNo, /* 입고창고번호 */
        String itmStrNo, /* 품목입고번호 */
        String acbDt, /* 회계일자 */
        String evidDvCd, /* 증빙구분코드 */
        String strHopDt, /* 입고희망일자 */
        String ostrWareNm, /* 출고창고명 */
        String strWareNm, /* 입고창고명 */
        String pdNm /* 품목명 */
    ) {}

    @ApiModel("WsnaMovementStoreDto-MovementOstrMngtReq")
    public record MovementOstrMngtReq(
        String stckNoStdGb, /* 표준창고구분 */
        String itmStrNo, /* 품목입고번호 */
        String itmPdNo, /* 품목상품번호 */
        String strRgstDt, /* 입고등록일자 */
        String strDt, /* 입고일자 */
        String strHopDt, /* 입고희망일자 */
        String strTpCd, /* 입고유형코드 */
        String ostrWareNo, /* 출고창고번호 */
        String strWareNo, /* 입고창고번호 */
        String ostrSn, /* 출고순번 */
        String strSn /* 입고순번 */
    ) {}

    @ApiModel("WsnaMovementStoreDto-MovementOstrMngtRes")
    public record MovementOstrMngtRes(
        String itmStrNo, /* 품목입고번호 */
        String sapMatCd, /* SAP자재코드 */
        String itmPdCd, /* 품목상품코드 */
        String itmPdNm, /* 품목상품명 */
        String itemLoc, /* 품목위치 */
        String onQty, /* 수량 */
        String mngtUnitCd, /* 관리단위코드 */
        String mngtUnitNm, /* 관리단위명 */
        String itmGdCd, /* 품목등급코드 */
        String boxUnitQty, /* 박스단위수량 */
        String ostrAkQty, /* 출고요청수량 */
        String ostrQty, /* 출고수량 */
        String strQty, /* 입고수량 */
        String OstrCnfmAggQty, /* 출고확정누계수량 */
        String strRgstDt, /* 입고등록일자 */
        String strWareNo, /* 입고창고번호 */
        String ostrWareNo, /* 출고창고번호 */
        String baseGb, /* 기본구분 */
        String baseColorGb, /* 기본컬러구분 */
        String strHopDt, /* 입고희망일자 */
        String ostrCnfmCd, /* 출고확정코드 */
        String inSum, /* 수량합계 */
        String strConfDt, /* 입고확인일자 */
        String rmkCn, /* 비고 */
        String strSn, /* 입고순번 */
        String ostrSn /* 출고순번 */
    ) {}

    @ApiModel("WsnaMovementStoreDto-MovementStrSaveReq")
    public record MovementStrSaveReq(
        String itmStrNo, /* 품목입고번호 */
        int strSn, /* 입고순번 */
        String strWareNo, /* 입고창고번호 */
        String itmGdCd, /* 품목등급코드 */
        String itmPdCd, /* 품목상품코드 */
        int strQty /* 입고수량 */
    ) {}

    @ApiModel("WsnaMovementStoreDto-WarehouseMonthlyReq")
    public record WarehouseMonthlyReq(
        String apyYm, /* 적용년월 */
        String wareNo /* 창고번호 */
    ) {}
}
