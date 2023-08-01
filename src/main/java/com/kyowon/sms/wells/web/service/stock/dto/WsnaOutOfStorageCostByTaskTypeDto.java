package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 *  K-W-SV-U-0254M01 업무유형별 자재출고금액
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.07.26
 */

public class WsnaOutOfStorageCostByTaskTypeDto {

    @ApiModel(value = "WsnaOutOfStorageCostByTaskTypeDto-SearchReq")
    public record SearchReq(
        String stOstrDt,
        String edOstrDt,
        String strWareDvCd,
        String strWareNoM,
        String strWareNoD,
        String itmKndCd,
        String itmPdCdFrom,
        String itmPdCdTo,
        String pdGdCd,
        String useSel,
        String stocTypeCd
    ) {}

    @ApiModel(value = "WsnaOutOfStorageCostByTaskTypeDto-SearchRes")
    public record SearchRes(
        String itmPdCd,
        String sapMatCd,
        String pdNm,
        Long istQty,
        Long istPdctUprcSum,
        Long bsQty,
        Long bsPdctUprcSum,
        Long asFreeQty,
        Long asFreePdctUprcSum,
        Long asPayQty,
        Long asPayPdctUprcSum
    ) {}
}
