package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarcodeInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniRegistrationBarcodeInterfaceDvo;

@Mapper(componentModel = "spring")
public interface WsniRegistrationBarcodeInterfaceConverter {
    WsniRegistrationBarcodeInterfaceDto.SearchJsonRes mapRegistrationBarcodeInterfaceDvoToJsonRes(WsniRegistrationBarcodeInterfaceDvo dvo);
}
