package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemByStockAggregationDto.SearchReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemByStockAggregationDvo;

@Mapper(componentModel = "spring")
public interface WsnaItemByStockAggregationConverter {

    WsnaItemByStockAggregationDvo mapSearchReqToWsnaItemByStockAggregationDvo(SearchReq dto);

}
