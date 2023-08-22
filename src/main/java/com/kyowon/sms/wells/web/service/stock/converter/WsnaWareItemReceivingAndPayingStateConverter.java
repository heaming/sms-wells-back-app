package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWareItemReceivingAndPayingStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWareItemReceivingAndPayingStateDvo;

@Mapper(componentModel = "spring")
public interface WsnaWareItemReceivingAndPayingStateConverter {

    List<SearchRes> mapDvoToSearchRes(List<WsnaWareItemReceivingAndPayingStateDvo> dvos);

}
