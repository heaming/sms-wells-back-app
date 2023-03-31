package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCubigVisitStopDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCubigVisitStopDvo;

@Mapper(componentModel = "spring")
public interface WsniCubigVisitStopConverter {
    WsniCubigVisitStopDvo mapCreatReqToCubigVisitStopDto(CreateReq dto);
}
