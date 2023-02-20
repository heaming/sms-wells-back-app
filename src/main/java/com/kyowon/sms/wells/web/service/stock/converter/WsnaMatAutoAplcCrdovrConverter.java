package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMatAutoAplcCrdovrDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMatAutoAplcCrdovrDvo;

@Mapper(componentModel = "spring")
public interface WsnaMatAutoAplcCrdovrConverter {
    WsnaMatAutoAplcCrdovrDvo mapCreateReqToWsnaMatAutoAplcCrdovrDvo(CreateReq createReq);
}
