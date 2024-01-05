package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 재고현황(개인창고)
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.08.11
 */
public class WsnaStockStateIndividualWareDto {

    @ApiModel(value = "WsnaStockStateIndividualWareDto-SearchReq")
    public record SearchReq(
        String stockDt,
        String baseYm,
        String wareNo,
        String wareUseYn,
        String itmKndCd,
        String itmGdCd,
        String useYn,
        String stndUnuseYn,
        String fromSapCd,
        String toSapCd
    ) {}

    @ApiModel(value = "WsnaStockStateIndividualWareDto-SearchRes")
    public record SearchRes(
        String wareNo,
        String sapMatCd,
        String kiwiPdCd,
        String pdNm,
        String lctNm,
        String ordnyHvMatYn,
        String cmnPartDvCd,
        String wareNoInStr,
        String agrgQty,
        String wareNoAgrgQtyFields,
        String wareNoAgrgQtySumFields
    ) {}

}
