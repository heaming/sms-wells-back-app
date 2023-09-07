package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 재고지속월현황
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.07.26
 */
public class WsnaStockContinueMonthStateDto {

    @ApiModel(value = "WsnaStocKContinueMonthStateDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String wareDvCd,
        String wareNoM,
        String wareNoD,
        String wareDtlDvCd,
        String itmKindCd,
        String useYn,
        String itmGdCd

    ) {}

    @ApiModel(value = "WsnaStocKContinueMonthStateDto-SearchRes")
    public record SearchRes(
        String pdCd,
        String sapMatCd,
        String pdNm,
        String asLdtm,
        String asMatMngTpCd,
        String minGoQty,
        String lgstBtdStocGdQty,
        String svCnrBtdStocGdQty,
        String svEgerBtdStocGdQty,
        String lstmmWkEtcOstrGdQty,
        String wkEtcOstrGdQty,
        String lgstStrQty,
        String svCnrStrQty,
        String svEgerStrQty,
        String lgstPitmStocGdQty,
        String svCnrPitmStocGdQty,
        String svEgerLgstPitmStocGdQty,
        String lgstKeppMm1,
        String lgstKeppMm2,
        String svCnrKeppMm1,
        String svCnrKeppMm2,
        String svEgerKeppMm1,
        String svEgerKeppMm2,
        String itmGdCd,
        String ordnyHvMatYn,
        String cmnPartDvCd,
        String trnovrRtOjYn,
        String lgstKeppMm1Blow1Yn,
        String lgstKeppMm1Blow2Yn,
        String lgstKeppMm2Blow1Yn,
        String lgstKeppMm2Blow2Yn,
        String svCnrKeppMm1Blow1Yn,
        String svCnrKeppMm1Blow2Yn,
        String svCnrKeppMm2Blow1Yn,
        String svCnrKeppMm2Blow2Yn,
        String svEgerKeppMm1Blow1Yn,
        String svEgerKeppMm1Blow2Yn,
        String svEgerKeppMm2Blow1Yn,
        String svEgerKeppMm2Blow2Yn

    ) {}

}
