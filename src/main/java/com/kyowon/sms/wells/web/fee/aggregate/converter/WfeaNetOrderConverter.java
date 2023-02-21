package com.kyowon.sms.wells.web.fee.aggregate.converter;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaNetOrderDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WfeaNetOrderConverter {

    WfeaNetOrderDvo mapSaveReqToWfeaNetOrderDvo(WfeaNetOrderDto.SaveReq dto);
}
