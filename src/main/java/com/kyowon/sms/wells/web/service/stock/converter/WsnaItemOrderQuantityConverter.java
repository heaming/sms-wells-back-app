package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemOrderQuantityDto.SearchReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantitySearchDvo;

@Mapper(componentModel = "spring")
public interface WsnaItemOrderQuantityConverter {

    WsnaItemOrderQuantitySearchDvo mapSearchReqToWsnaItemOrderQuantitySearchDvo(SearchReq dto);

}
