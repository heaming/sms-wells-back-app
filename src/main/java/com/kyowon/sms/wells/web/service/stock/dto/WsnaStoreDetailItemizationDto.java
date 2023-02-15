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
        String strWareDvCd,
        String strWareNoD,
        String strWareNoM,
        String ostrWareNoD,
        String ostrWareNoM
    ) {}

    @ApiModel(value = "WsnaStoreDetailItemizationDto-SearchRes")
    public record SearchRes(
        String strRgstDt, /*입고등록일자*/
        String itmPdCd, /*품목상품코드*/
        String pdAbbrNm, /*품목명*/
        String cdCntn, /*코드명*/
        String mngtUnitCd, /*관리단위코드*/
        String itmGdCd, /*품목등급코드*/
        String strQty, /*입고수량*/
        String acbDt, /*회계일자*/
        String ostrDt, /*출고일자*/
        String ostrWareNo, /*출고창고번호*/
        String ostrWareNm, /*출고창고명*/
        String strWareNo, /*입고창고번호*/
        String strWareNm, /*입고창고명*/
        String itmStrNo, /*품목입고번호*/
        String itmOstrNo, /*품목출고번호*/
        String strWareNoUp, /*입고창고의 상위테이블번호*/
        String strWareChgCd, /*입고창고의 창고구분*/
        String ostrWareNoUp, /*출고창고의 상위테이블번호*/
        String ostrWareChgCd, /*출고창고의 창고구분*/
        String sapMatCd /*sap코드*/
    ) {}
}
