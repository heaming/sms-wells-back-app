package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WwctaDocumentReceiptPssDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //wells 서류접수현황(추가) - wells 서류접수현황(추가) 조회 Search Request Dto
    @ApiModel(value = "WwctaDocumentReceiptPssDto-SearchReq")
    public record SearchReq(
        String cntrChRcpStrtDtm,
        String cntrChRcpFinsDtm,
        String cntrChPrgsStatCd,
        String cntrChTpCd,
        String cntrChRcpId,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //wells 서류접수현황(추가) - wells 서류접수현황(추가) 조회 Search Result Dto
    @ApiModel("WwctaDocumentReceiptPssDto-SearchRes")
    public record SearchRes(
        String cntrChRcpId,
        String cntrChRcpD,
        String cntrChRcpTm,
        String cntrChPrgsStatCd,
        String cntrChPrgsStatNm,
        String cntrChPrgsStatCdEnd,
        String cntrChPrgsStatNmEnd,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrChTpCd,
        String cntrChTpNm,
        String fnlMdfcDtm
    ) {}
}
