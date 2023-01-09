package com.kyowon.sms.wells.web.service.common.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyCalendarDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyCalendarDvo;

@Mapper(componentModel = "spring")
public interface WsnyCalendarConverter {
    WsnyCalendarDvo mapCalendarReqToCalendarDvo(WsnyCalendarDto.SaveRegReq dto) throws Exception;
}
