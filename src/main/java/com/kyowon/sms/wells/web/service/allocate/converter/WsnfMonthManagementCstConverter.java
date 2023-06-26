package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsnfMonthManagementCstDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsnfMonthManagementCstDvo;

@Mapper(componentModel = "spring")
public interface WsnfMonthManagementCstConverter {
    WsnfMonthManagementCstDvo mapSaveProcessReqToDvo(WsnfMonthManagementCstDto.CreateReq dto) throws Exception;
}
