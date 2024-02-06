package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * <pre>
 * 창고 품목별 수불현황
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.08.21
 */
public class WsnaWareItemReceivingAndPayingStateDto {

    @ApiModel(value = "WsnaWareItemReceivingAndPayingStateDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String wareDvCd,
        String wareDtlDvCd,
        String wareNoM,
        String wareNoD,
        String itmGdCd,
        String useYn,
        String itmKndCd,
        String pdCd,
        List<String> itmPdCds
    ) {}

    @ApiModel(value = "WsnaWareItemReceivingAndPayingStateDto-SearchRes")
    public record SearchRes(
        String wareNo,
        String wareNm,
        String btdQty,
        String prchsStrQty,
        String nomStrQty,
        String qomAsnStrQty,
        String qomMmtStrQty,
        String rtngdInsiStrQty,
        String wkRtngdStrQty,
        String etcStrQty,
        String ctrStrQty,
        String stocAcinspStrQty,
        String nomOstrQty,
        String qomAsnOstrQty,
        String qomMmtOstrQty,
        String rtngdInsiOstrQty,
        String rtngdOtsdOstrQty,
        String wkOstrQty,
        String m12WthnWkOstrQty,
        String m12AfWkOstrQty,
        String refrOstrQty,
        String sellOstrQty,
        String dsuOstrQty,
        String etcOstrQty,
        String ctrOstrQty,
        String cnfmPitmOstrGapQty,
        String eotQty,
        String mmtStocOstrQty,
        String subsumGroupkey

    ) {}

}
