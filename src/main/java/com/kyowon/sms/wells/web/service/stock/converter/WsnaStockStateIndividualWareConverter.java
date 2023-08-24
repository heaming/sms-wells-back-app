package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStateIndividualWareDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStateIndividualWareDvo;

@Mapper(componentModel = "spring")
public interface WsnaStockStateIndividualWareConverter {

    WsnaStockStateIndividualWareDvo mapSearchReqToWsnaStockStateIndividualWareDvo(SearchReq dto);

}
