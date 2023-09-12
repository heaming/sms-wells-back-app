package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniMyKPaymentInfoDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniMyKPaymentInfoDvo;

@Mapper(componentModel = "spring")
public interface WsniMyKPaymentInfoInterfaceConverter {
    WsniMyKPaymentInfoDvo mapFindReqToWsniMyKPaymentInfoDvo(WsniMyKPaymentInfoDto.FindReq dto);

    WsniMyKPaymentInfoDto.FindRes mapAllEctiMyKPaymentInfolDvoToFindRes(WsniMyKPaymentInfoDvo dvo);
}
