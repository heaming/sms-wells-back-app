package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAutoMaterialReqDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAutoMaterialReqDvo;

@Mapper(componentModel = "spring")
public interface WsnaAutoMaterialReqConverter {
    WsnaAutoMaterialReqDvo mapCreateReqToWsnaAutoMaterialReqDvo(CreateReq createReq);
}
