package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.CreateTfReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodCustomerTfCreateDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncBsPeriodCustomerTfConverter {
    WsncBsPeriodCustomerTfCreateDvo mapCreateTfReqToBsPeriodCustomerTfCreateDvo(CreateTfReq dto) throws Exception;
}
