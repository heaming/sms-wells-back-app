package com.kyowon.sms.wells.web.fee.aggregate.converter;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNchnFeeDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaNchnFeeDvo;

@Mapper(componentModel = "spring")
public interface WfeaNchnFeeConverter {

    WfeaNchnFeeDvo mapSaveReqToWfeaNchnFeeDvo(SaveReq dto);
}
