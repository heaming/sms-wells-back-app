package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryBaseDvo;

@Mapper(componentModel = "spring")
public interface WsnaBsCsmbDeliveryBaseConverter {
    WsnaBsCsmbDeliveryBaseDvo mapCreateReqToDeliveryBaseDto(CreateReq dto);
}
