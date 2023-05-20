package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAssignPsicTfDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAssignPsicTfDvo;

@Mapper(componentModel = "spring")
public interface WsncAssignPsicTfConverter {
    WsncAssignPsicTfDvo mapPkReqToAssignPsicTfDvo(WsncAssignPsicTfDto.SearchProcessPkReq dto);

    WsncAssignPsicTfDvo mapReqToAssignPsicTfDvo(WsncAssignPsicTfDto.SearchProcessReq dto);
}
