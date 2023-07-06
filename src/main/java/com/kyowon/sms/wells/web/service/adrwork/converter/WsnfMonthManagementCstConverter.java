package com.kyowon.sms.wells.web.service.adrwork.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfMonthManagementCstDto;
import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfMonthManagementCstDvo;

@Mapper(componentModel = "spring")
public interface WsnfMonthManagementCstConverter {
    WsnfMonthManagementCstDvo mapSaveProcessReqToDvo(WsnfMonthManagementCstDto.CreateReq dto) throws Exception;
}
