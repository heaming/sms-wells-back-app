package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitStopInquiryDvo;

@Mapper(componentModel = "spring")
public interface WsnbVisitStopInquiryConverter {
    WsnbVisitStopInquiryDvo mapCreatReqToVisitStopInquiryDto(CreateReq dto);
}
