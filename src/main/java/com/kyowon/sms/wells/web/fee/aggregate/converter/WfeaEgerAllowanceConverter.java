package com.kyowon.sms.wells.web.fee.aggregate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaEgerAllowanceDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaEgerAllowanceDvo;

@Mapper(componentModel = "spring")
public interface WfeaEgerAllowanceConverter {
    WfeaEgerAllowanceDvo mapSaveReqToWfeaEgerAllowanceDvo(WfeaEgerAllowanceDto.SaveReq dto);
}
