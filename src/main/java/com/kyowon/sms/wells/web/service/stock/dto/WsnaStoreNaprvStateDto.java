package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 *  K-W-SV-U-0126M01 입고 미승인 현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.06.20
 */

public class WsnaStoreNaprvStateDto {

    @ApiModel(value = "WsnaStoreNaprvStateDto-SearchReq")
    public record SearchReq(
        String strWareDvCd,
        String strWareNoM,
        String strWareNoD
    ) {}

    @ApiModel(value = "WsnaStoreNaprvStateDto-SearchRes")
    public record SearchRes(
        String strWareNo,
        String wareNm,
        String itmPdCd,
        String pdNm,
        String naprvQty
    ) {}
}
