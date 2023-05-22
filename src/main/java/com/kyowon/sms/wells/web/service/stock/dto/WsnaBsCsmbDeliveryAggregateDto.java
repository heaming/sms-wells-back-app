package com.kyowon.sms.wells.web.service.stock.dto;

public class WsnaBsCsmbDeliveryAggregateDto {
    public record SearchReq(
        String mngtYmFrom,
        String mngtYmTo,
        String bldCds,
        String itmCds,
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
        String aclBldCd,
        String pdNm,
        String nwcmr,
        String indv,
        String bld,
        String bfsvcCsmbDdlvSum,
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
        String strWareNo,
        String bfsvcCsmbDdlvQty,
        String bldCd,
        String bldNm
    ) {}
}
