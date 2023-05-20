package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnusualItemInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnusualItemInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCenterUnusualItemInterfaceDvo;

@Mapper(componentModel = "spring")
public interface WsniCenterUnusualItemInterfaceConverter {
    WsniCenterUnusualItemInterfaceDvo mapCreatReqToUnuitmSaveInterfaceDvo(CreateReq dto);

    CreateRes mapCreateResToUnuitmSaveInterfaceDto(WsniCenterUnusualItemInterfaceDvo dvo);
}
