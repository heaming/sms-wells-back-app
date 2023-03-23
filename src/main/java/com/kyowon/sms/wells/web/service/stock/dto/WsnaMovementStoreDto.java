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
    @ApiModel("WsnaMovementStrControllerDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String stStrDt,
        @NotBlank
        String edStrDt,
        String strTpCd,
        String wareDvCd,
        String strOjWareNo

    ) {}

    @ApiModel("WsnaMovementStrControllerDto-SearchRes")
    public record SearchRes(
        String strTpCd, /*입고유형코드,*/
        String strWareNo, /*입고창고번호*/
        String strSn, /*입고일련번호*/
        String strRgstDt, /*입고등록일자*/
        String dlvgDlpnrNo, /*납품거래처번호*/
        String itmStrNo, /*품목입고번호*/
        String ostrTpCd, /*출고유형코드*/
        String ostrWareNo, /*출고창고번호*/
        String ostrDt, /*출고일자*/
        String ostrSn, /*출고일련번호*/
        String itmOstrNo, /*품목출고번호*/
        String wareNm /*창고명*/
        //        String strDelButn
    ) {}

    @ApiModel("WsnaMovementStrControllerDto-MovementRes")
    public record MovementRes(
        String strTpCd, /*입고구분코드*/
        String strRgstDt, /*입고일자*/
        String itmStrNo, /*품목입고번호*/
        String itmPdNm, /*품목상품명*/
        String strSn, /*입고일련번호*/
        String strWareNo, /*입고창고번호*/
        String wareNm /*창고명*/
    ) {}
}
