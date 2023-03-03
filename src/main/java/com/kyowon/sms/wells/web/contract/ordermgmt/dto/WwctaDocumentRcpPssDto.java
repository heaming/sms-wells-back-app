package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WwctaDocumentRcpPssDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //wells 서류접수현황(추가) - wells 서류접수현황(추가) 조회 Search Request Dto
    @ApiModel(value = "WwctaDocumentRcpPssDto-SearchReq")
    public record SearchReq(
        String cntrChRcpStrtDtm,
        String cntrChRcpFinsDtm,
        String cntrChPrgsStatCd,
        String cntrChTpCd,
        String cntrChRcpId,
        String cstKnm,
        String cralLocapaMexnoIdvTno
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //wells 서류접수현황(추가) - wells 서류접수현황(추가) 조회 Search Result Dto
    @ApiModel("WwctaDocumentRcpPssDto-SearchRes")
    public record SearchRes(
        String cntrChRcpId,
        String cntrChRcpD,
        String cntrChRcpTm,
        String cntrChPrgsStatCd,
        String cntrChPrgsStatCdEnd,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrChTypeCd,
        String fnlMdfcDtm
    ) {}
}
