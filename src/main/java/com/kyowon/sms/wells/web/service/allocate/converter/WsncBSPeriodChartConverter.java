package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBSPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBSPeriodChartDvo;

@Mapper(componentModel = "spring")
public interface WsncBSPeriodChartConverter {
    WsncBSPeriodChartDvo mapBaseInfoResToPeriodChartDvo(WsncBSPeriodChartDto.SearchBaseInfoRes res) throws Exception;
}
