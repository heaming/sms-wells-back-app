package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCenterLocalAreaTfDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncCenterLocalAreaTfDvo;

@Mapper(componentModel = "spring")
public interface WsncCenterLocalAreaTfConverter {
    WsncCenterLocalAreaTfDvo mapSaveReqToCenterLocalAreaTfDvo(WsncCenterLocalAreaTfDto.SaveReq dto) throws Exception;
}
