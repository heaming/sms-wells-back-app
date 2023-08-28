package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 *  월별 출고 집계 현황
 * </pre>
 *
 * @author junggheejin
 * @since 2023.08.24
 */
public class WsnaMonthlyOutOfStorageAgrgDto {

    @ApiModel(value = "WsnaMonthlyOutOfStorageAgrgDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String wareNo,
        String wareDvCd,
        String wareDtlDvCd,
        String wareUseYn,
        String itmGdCd,
        String itmKndCd,
        String itmPdCd,
        String useYn
    ) {}

    @ApiModel(value = "WsnaMonthlyOutOfStorageAgrgDto-FindWarehouseRes")
    public record FindWarehouseRes(
        String codeId,
        String codeName
    ) {}
}
