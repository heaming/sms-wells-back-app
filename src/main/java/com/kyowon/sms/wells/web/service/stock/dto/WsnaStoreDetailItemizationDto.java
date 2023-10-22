package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0133M01 입고상세내역조회
 * </pre>
 *
 * @author songTaeSung
 * @since 2023-01-25
 */
public class WsnaStoreDetailItemizationDto {

    @ApiModel(value = "WsnaStoreDetailItemizationDto-SearchReq")
    public record SearchReq(
        String stStrDt, /*입고시작일*/
        String edStrDt, /*입고종료일*/
        String strTpCd, /*입고유형*/
        String wareDvCd, /*입고창고구분*/
        String ostrWareDvCd, /*출고창고구분*/
        String pgGdCd, /*등급코드*/
        String itmKndCd, /*품목코드*/
        String itmPdCd, /*품목상품코드*/
        String useYn, /*사용여부*/
        String strWareDvCd, /* 입고창고구분코드 */
        String strWareNoD, /* 입고창고번호D*/
        String strWareNoM, /* 입고창고번호M*/
        String strWareDtlDvCd, /* 입고창고상세구분코드 */
        String ostrWareNoD, /* 출고창고번호D */
        String ostrWareNoM, /* 출고창고번호 M */
        String ostrWareDtlDvCd /* 출고창고상세구분코드 */
    ) {}

    @ApiModel(value = "WsnaStoreDetailItemizationDto-SearchRes")
    public record SearchRes(
        String sapMatCd, /*sap코드*/
        String strRgstDt, /*입고등록일자*/
        String itmPdCd, /*품목상품코드*/
        String pdAbbrNm, /*품목명*/
        String strTpNm, /*입고코드명*/
        String mngtUnitNm, /*관리단위코드명*/
        String itmGdNm, /*품목등급코드명*/
        String strQty, /*입고수량*/
        String ostrDt, /*출고일자*/
        String ostrWareNo, /*출고창고번호*/
        String strWareNo, /*입고창고번호*/
        String strWareNm, /*입고창고명*/
        String itmStrNo, /*품목입고번호*/
        String itmOstrNo, /*품목출고번호*/
        String strWareNoUp, /*입고창고의 상위테이블번호*/
        String strWareDtlDvCd, /*입고창고상세구분코드*/
        String strWareDtlDvNm, /*입고창고상세구분명*/
        String ostrWareNm, /*출고창고명*/
        String ostrWareNoUp, /*출고창고의 상위 테이블코드*/
        String ostrWareDtlDvCd, /*출고창고상세구분코드*/
        String ostrWareDtlDvNm /*출고창고상세구분명*/
    ) {}
}
