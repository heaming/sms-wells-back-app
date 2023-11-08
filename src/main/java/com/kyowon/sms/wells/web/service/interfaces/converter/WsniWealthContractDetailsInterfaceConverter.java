package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniWealthContractDetailsDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniWealthContractDetailsDvo;

@Mapper(componentModel = "spring")
public interface WsniWealthContractDetailsInterfaceConverter {
    WsniWealthContractDetailsDvo mapFindReqToWsniWealthContractDetailsDvo(WsniWealthContractDetailsDto.FindReq dto);

    WsniWealthContractDetailsDto.FindRes mapAllWsniWealthContractDetailsDvoToFindRes(WsniWealthContractDetailsDvo dvo);
}
