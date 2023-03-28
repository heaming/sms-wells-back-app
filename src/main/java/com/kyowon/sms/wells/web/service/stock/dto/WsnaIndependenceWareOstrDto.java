package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaIndependenceWareOstrDto {
    @Builder
    @ApiModel("WsnaIndependenceWareOstrDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String asnOjYm,
        String itmKndCd,
        String asnTnN,
        String ostrOjWareNo,
        String ostrWareNoM,
        String ostrWareNoD,
        String ostrDt,
        String itmKndCdD,
        String sapMatCdFrom,
        String sapMatCdTo
    ) {}

    @ApiModel("WsnaIndependenceWareOstrDto-SearchRes")
    public record SearchRes(
        String itemCd,
        String pdAbbrNm,
        String mgtUnt,
        String mgtUntNm,
        String matGdCd,
        String asnOjYm,
        String ostrWareNo,
        String strWareNo,
        String hgrWareNo,
        String wareNm,
        String rectOstrDt,
        String rectOstrTpCd,
        String itmQomAsnNo,
        String itemKind,
        String sapMatCd,
        int crtlStocQty,
        int thwkExpQty,
        int boxQty,
        int boxQty1,
        int cnfmQty,
        int mcbyAcuOstrQty,
        int outQty,
        int accQty,
        int accBoxQty,
        String rmks,
        String outTyp,
        String outStckCd,
        String outRegDt,
        String outSeq,
        String outSer,
        String inRegDt,
        String dlvGb,
        int pitmStocAGdQty,
        int outBoxQty
    ) {}

    public record CreateReq(
        String ostrAkNo,
        String ostrAkSn,
        String ostrAkTpCd,
        String ostrOjWareNo,
        String strOjWareNo,
        String ostrAkRgstDt,
        String strHopDt,
        String itmPdCd,
        String itmGdCd,
        String mngtUnitCd,
        String ostrAkQty,
        String rmkCn,
        String dtaDlYn
    ) {}
}
