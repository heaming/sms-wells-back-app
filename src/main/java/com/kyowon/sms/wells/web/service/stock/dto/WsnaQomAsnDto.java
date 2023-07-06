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
        String wareDvCd,
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

    @ApiModel(value = "WsnaQomAsnDto-IndividualWareSearchReq")
    public record IndividualWareSearchReq(
        String apyYm,
        String asnOjYm,
        String cnt,
        String ostrWare,
        String strWare,
        String itmKndCd,
        String strTpCd,
        String wareDvCd,
        String wareDtlDvCd,
        String itmCdSt, //sapMatCdFrom,
        String itmCdEd //sapMatCdTo
    ){}
    @ApiModel(value = "WsnaQomAsnDto-IndividualWareSearchRes")
    public record IndividualWareSearchRes(
        String chk,
        String apyYm,
        String asnOjYm,
        String outStckCd,
        String strWareNo,
        String seq,
        String asnTnN,
        String wareDvCd,
        String didyDvCd,
        String strWareNm,
        String itmPdCd,
        String saleCd,
        String nmAbbr1,
        String deg,
        String stckMgr,
        String stckMgrNm,
        String bldCd,
        String tell,
        String bldnam,
        String qomQtyPrvt,
        String qomQtyCrp,
        String qomQty,
        String baseQty,
        String accQtySum,
        String onQty,
        String twQty,
        String nwQty,
        String bsQty,
        String cfrmQty,
        String boxDv,
        String accQty
    ){}
    @ApiModel(value = "WsnaQomAsnDto-CreateIndependenceWareReq")
    public record CreateIndependenceWareReq(

    ){}

    @ApiModel(value = "WsnaQomAsnDto-CreateIndividualWareReq")
    public record CreateIndividualWareReq(

    ){}

    @ApiModel(value = "WsnaQomAsnDto-WareRes")
    public record WareRes(
        String codeId,
        String codeName
    ){}
}
