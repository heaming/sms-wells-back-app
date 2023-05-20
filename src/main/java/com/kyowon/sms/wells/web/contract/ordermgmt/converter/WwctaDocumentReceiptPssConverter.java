package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WwctaDocumentReceiptPssDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WwctaDocumentReceiptPssRequestDvo;

@Mapper(componentModel = "spring")
public interface WwctaDocumentReceiptPssConverter {
    WwctaDocumentReceiptPssRequestDvo mapSearchReqToWwctaDocumentReceiptPssDvo(SearchReq dto);

    List<SearchRes> mapWwctaDocumentReceiptPssDvoToSearchRes(List<WwctaDocumentReceiptPssDvo> dvos);

}
