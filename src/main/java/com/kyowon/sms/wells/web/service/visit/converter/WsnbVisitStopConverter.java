package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitStopDvo;

@Mapper(componentModel = "spring")
public interface WsnbVisitStopConverter {
    WsnbVisitStopDvo mapCreatReqToVisitStopInquiryDto(CreateReq dto);
}
