package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstAssignSetDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstAssignSetDvo;

@Mapper(componentModel = "spring")
public interface WsnaInstAssignSetConverter {

    WsnaInstAssignSetDvo mapCreateReqToInstAssignSetDvo(
        WsnaInstAssignSetDto.CreateReq dto
    );

    WsnaInstAssignSetDvo mapRemoveReqToInstAssignSetDvo(
        WsnaInstAssignSetDto.RemoveReq dto
    );
}
