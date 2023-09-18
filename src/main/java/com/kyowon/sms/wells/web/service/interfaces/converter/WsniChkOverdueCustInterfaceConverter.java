package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniChkOverdueCustDvo;

@Mapper(componentModel = "spring")
public interface WsniChkOverdueCustInterfaceConverter {
    WsniChkOverdueCustDvo mapFindReqToWsniChkOverdueCustDvo(WsniChkOverdueCustDto.FindReq dto);

    WsniChkOverdueCustDto.FindRes mapAllEctiChkOverdueCustlDvoToFindRes(WsniChkOverdueCustDvo dvo);
}
