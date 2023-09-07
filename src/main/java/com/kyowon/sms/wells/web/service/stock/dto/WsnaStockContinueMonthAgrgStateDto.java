package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 재고지속월집계현황
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.08.03
 */
public class WsnaStockContinueMonthAgrgStateDto {

    @ApiModel(value = "WsnaStocKContinueMonthMgtStateDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String useYn,
        String stockTpCd,
        String itmKndCd,
        String itmGdCd

    ) {}

    @ApiModel(value = "WsnaStocKContinueMonthAgrgStateDto-SearchRes")
    public record SearchRes(
        String wareNoInStr,
        String wareNoFields,
        String wareNoPitmFields,
        String wareNoKeppMmFields,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String summ,
        String ordnyHvMatYn,
        String cmnPartDvCd,
        String trnovrRtOjYn

    ) {}

}
