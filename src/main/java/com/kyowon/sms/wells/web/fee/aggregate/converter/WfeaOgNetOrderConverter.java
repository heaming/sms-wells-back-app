package com.kyowon.sms.wells.web.fee.aggregate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOgNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOgNetOrderDvo;

@Mapper(componentModel = "spring")
public interface WfeaOgNetOrderConverter {

    WfeaOgNetOrderDvo mapSaveOgNetOrderReqToWfeaOgNetOrderDvo(WfeaOgNetOrderDto.SaveOgNetOrderReq dto);

    WfeaOgNetOrderDvo mapSaveBsReqToWfeaOgNetOrderDvo(WfeaOgNetOrderDto.SaveBsReq dto);
}
