package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniParcelServiceRegDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniParcelServiceRegDvo;

@Mapper(componentModel = "spring")
public interface WsniParcelServiceConverter {
    WsniParcelServiceRegDvo mapRgstPrclServiceReqDtoToRgstPrclServiceReqDvo(
        WsniParcelServiceRegDto.RegistParcelServiceReq dto
    );
}
