package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaQomAsnDto {
    @ApiModel(value = "WsnaQomAsnDto-SearchReq")
    public record SearchReq(
        String apyYm,
        String asnOjYm,
        String cnt,
        String ostrWare,
        String strWare,
        String itmKndCd,
        String wareDtlDvCd,
        String itmCdSt, //sapMatCdFrom,
        String itmCdEd //sapMatCdTo
    ){}

    @ApiModel(value = "WsnaQomAsnDto-SearchRes")
    public record SearchRes(
        String chk,
        String asnOjYm,
        String ostrWareNo,
        String strWareNo,
        String seq,
        String asnTnN,
        String strWareDvCd,
        String strWareNm,
        String itemCd,
        String itemCode,
        String saleCd,
        String nmAbbr1,
        String mgtUntNm,
        String deg,
        String onQty,
        String useQty,
        String cnfrmQty,
        String accQty,
        String boxQty,
        String boxCnt,
        String boxMgtQty,
        String stckMgrGb,
        String stckMgr,
        String bldCd,
        String stckBldCd,
        String stckBldNm,
        String wareAdrId,
        String adrUseYn,
        String puQty,
        String fullPuQty,
        String pitmStocAGdQtY
    ){}

    @ApiModel(value = "WsnaQomAsnDto-CreateIndependenceWareReq")
    public record CreateIndependenceWareReq(

    ){}

    @ApiModel(value = "WsnaQomAsnDto-CreateIndividualWareReq")
    public record CreateIndividualWareReq(

    ){}
}
