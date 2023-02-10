package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0141M01 출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.02
 */
public class WsnaOutOfStorageItemizationDto {

    @ApiModel(value = "WsnaOutOfStorageManagementDto-SearchReq")
    public record SearchReq(
        String stOstrDt, /*시작출고일자*/
        String edOstrDt, /*종료출고일자*/
        String ostrTpCd, /*출고유형코드*/
        String wareDvCd, /*창고구분*/
        String strOjWareNo, /*입고창고번호*/
        String ostrWareDvCd, /*출고창고구분코드*/
        String ostrWareNoD,
        String ostrWareNoM,
        String divide /*구분*/

    ) {}

    @ApiModel(value = "WsnaOutOfStorageManagementDto-SearchRes")
    public record SearchRes(
        String ostrTpCd,
        String ostrWareNo,
        String strWareNo,
        String itmOstrNo,
        String itmStrNo,
        String ostrDt,
        String ostrSn,
        String wareNm,
        String wareAdrId
        //        String newAdrZip,
        //        String txtNote

    ) {}
}
