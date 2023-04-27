package com.kyowon.sms.wells.web.closing.standard.converter;

import com.kyowon.sms.wells.web.closing.standard.dto.WdcyCloseHourBulkRegDto.CreateReq;
import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyCloseHourBulkRegDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WdcyCloseHourBulkRegConverter {

    WdcyCloseHourBulkRegDvo mapCreateReqToWdcyCloseHourBulkRegDvo(CreateReq dto);
}
