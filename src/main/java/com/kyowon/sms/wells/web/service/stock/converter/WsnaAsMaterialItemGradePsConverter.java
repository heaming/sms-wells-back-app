package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradePsDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradePsDvo;

@Mapper(componentModel = "spring")
public interface WsnaAsMaterialItemGradePsConverter {

    WsnaAsMaterialItemGradePsDvo mapSearchReqToWsnaAsMaterialItemGradePsDvo(WsnaAsMaterialItemGradePsDto.SearchReq dto);

}
