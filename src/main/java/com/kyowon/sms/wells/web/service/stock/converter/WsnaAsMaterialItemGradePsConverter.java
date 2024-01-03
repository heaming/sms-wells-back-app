package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradePsDto.SearchReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradePsDvo;

@Mapper(componentModel = "spring")
public interface WsnaAsMaterialItemGradePsConverter {

    WsnaAsMaterialItemGradePsDvo mapSearchReqToWsnaAsMaterialItemGradePsDvo(SearchReq dto);

}
