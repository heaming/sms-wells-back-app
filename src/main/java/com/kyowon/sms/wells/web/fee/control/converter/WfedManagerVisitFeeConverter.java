package com.kyowon.sms.wells.web.fee.control.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto;
import com.kyowon.sms.wells.web.fee.control.dvo.WfedManagerVisitFeeDvo;

@Mapper(componentModel = "spring")
public interface WfedManagerVisitFeeConverter {
    WfedManagerVisitFeeDvo mapSaveReqToWfedManagerVisitFeeDvo(WfedManagerVisitFeeDto.SearchReq dto);
}
