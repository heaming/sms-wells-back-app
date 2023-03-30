package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartDvo;

@Mapper(componentModel = "spring")
public interface WsncBsPeriodChartConverter {
    WsncBsPeriodChartDvo mapBaseInfoResToPeriodChartDvo(WsncBsPeriodChartDto.SearchBaseInfoRes res) throws Exception;
}
