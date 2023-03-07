package com.kyowon.sms.wells.web.fee.calculation.converter;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebEgerAllowanceDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebEgerAllowanceDvo;

@Mapper(componentModel = "spring")
public interface WfebEgerAllowanceConverter {

    WfebEgerAllowanceDvo mapSaveReqToWfebEgerAllowanceDvo(SaveReq dto);

}
