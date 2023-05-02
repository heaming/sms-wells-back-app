package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnuitmSaveInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnuitmSaveInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCenterUnuitmSaveInterfaceDvo;

@Mapper(componentModel = "spring")
public interface WsniCenterUnuitmSaveInterfaceConverter {
    WsniCenterUnuitmSaveInterfaceDvo mapCreatReqToUnuitmSaveInterfaceDvo(CreateReq dto);

    CreateRes mapCreateResToUnuitmSaveInterfaceDto(WsniCenterUnuitmSaveInterfaceDvo dvo);
}
