package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * <pre>
 * 재고지속월관리현황
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.08.03
 */
public class WsnaStockContinueMonthMgtStateDto {

    @ApiModel(value = "WsnaStocKContinueMonthMgtStateDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String wareDvCd,
        String wareNoM,
        String wareNoD,
        String wareDtlDvCd,
        String stockTpCd,
        String itmGdCd,
        String useYn,
        String itmKindCd,
        String matDiv,
        String itmPdCd,
        String strtSapCd,
        String endSapCd,
        String itmGrpCd,        // 품목코드 리스트
        List<String> itmPdCds // 품목코드
    ) {}

    @ApiModel(value = "WsnaStocKContinueMonthMgtStateDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd,
        String pdNm,
        String ordnyHvMatYn,
        String asMatMngTpCd,
        String cmnPartDvCd,
        String trnovrRtOjYn,
        String stockCost,
        String btdStocGdQty,
        String btdStocGdAmt,
        String strQty,
        String strAmt,
        String wkOstrGdQty,
        String wkOstrGdAmt,
        String etcOstrGdQty,
        String etcOstrGdAmt,
        String qomMmtStrQty,
        String qomMmtOstrQty,
        String hgrWareOstrQty,
        String onQty,
        String onAmt,
        String keppMm,
        String tnorRt

    ) {}

}
