package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbRentalNewRequidationAggregateDto {
    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String pdctDv,
        String adrpcTpCd,
        String prtnrBzsCd,
        String slYm
    ) {}

    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchRes")
    public record SearchRes(
        String divCdNm,
        String divDtlCdNm,
        String qty,
        String divCd,
        String divDtlCd
    ) {}

    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchDetailReq")
    public record SearchDetailReq(
        String startDt,
        String endDt,
        String adrpcTpCd,
        String pdctDv,
        String prtnrBzsCd,
        String slYm,
        String divCd,
        String divDtlCd
    ) {}

    @ApiModel(value = "WdcbRentalNewRequidationAggregateDto-SearchDetailRes")
    public record SearchDetailRes(
        String cntrNo,
        String sapAssetNo,
        String cstKnm,
        String sapMatCd,
        String matPdCd,
        String pdNm,
        String istDt,
        String cntrPdEnddt,
        String reqdDt,
        String prtnrBzsCd,
        String prtnrBzsNm
    ) {}
}
