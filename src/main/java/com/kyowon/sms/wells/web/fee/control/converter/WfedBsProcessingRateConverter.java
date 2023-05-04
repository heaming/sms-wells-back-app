package com.kyowon.sms.wells.web.fee.control.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.control.dto.WfedBsProcessingRateDto.SaveReq;
import com.kyowon.sms.wells.web.fee.control.dvo.WfedBsProcessingRateDvo;

@Mapper(componentModel = "spring")
public interface WfedBsProcessingRateConverter {
    WfedBsProcessingRateDvo mapSaveReqToWfedBsProcessingRateDvo(SaveReq dto);
}
