package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthAgrgStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockContinueMonthAgrgStateDvo;

@Mapper(componentModel = "spring")
public interface WsnaStockContinueMonthAgrgStateConverter {

    WsnaStockContinueMonthAgrgStateDvo mapSearchReqToWsnaStockContinueMonthAgrgStateDvo(SearchReq dto);

}
