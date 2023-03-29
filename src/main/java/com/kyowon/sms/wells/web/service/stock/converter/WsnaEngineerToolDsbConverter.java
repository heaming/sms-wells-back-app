package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEngineerToolDsbDvo;

@Mapper(componentModel = "spring")
public interface WsnaEngineerToolDsbConverter {
    WsnaEngineerToolDsbDvo mapCreatReqToEngineerToolDsb(CreateReq dto);
}
