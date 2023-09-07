package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingByFilterTypeDto.EditReq;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingByFilterTypeDvo;

@Mapper(componentModel = "spring")
public interface WsnaShippingByFilterTypeConverter {

    List<WsnaShippingByFilterTypeDvo> mapAllEditRegToWsnaShippingByFilterTypeDvo(List<EditReq> dtos);

}
