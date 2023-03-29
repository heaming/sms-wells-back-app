package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEngineerToolDvo;

@Mapper(componentModel = "spring")
public interface WsnaEngineerToolConverter {
    WsnaEngineerToolDvo mapCreatReqToEngineerToolDsb(CreateReq dto);
}
