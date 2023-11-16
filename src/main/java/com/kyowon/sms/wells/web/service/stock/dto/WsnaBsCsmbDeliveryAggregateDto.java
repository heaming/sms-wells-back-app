package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

public class WsnaBsCsmbDeliveryAggregateDto {
    public record SearchReq(
        String mngtYmFrom,
        String mngtYmTo,
        List<String> bldCds,
        List<String> itmCds,
        String bfsvcCsmbDdlvOjCd,
        String sapCdFrom,
        String sapCdTo
    ) {}

    public record SearchRes(
        String csmbPdCd,
        String strWareNo,
        String sapMatCd,
        String bldNm,
        String bldCd,
        String pdNm,
        String nwcmr,
        String indv,
        String bld,
        String ddlvQtySum,
        String mm1Qty,
        String mm2Qty,
        String mm3Qty,
        String mm4Qty,
        String mm5Qty,
        String mm6Qty,
        String vstAccSum,
        String bdtIndv,
        String bdtCrp,
        String wrfr,
        String arcleIndv,
        String arcleCrp,
        String wtrSftnr,
        String msgcr,
        String cffMchn,
        String dryr,
        String wash,
        String ardrssr,
        String sscling
    ) {}

    public record SearchQtysRes(
        String mngtYm,
        String csmbPdCd,
        String bfsvcCsmbDdlvQty,
        String bldCd,
        String bldNm
    ) {}
}
