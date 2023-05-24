package com.kyowon.sms.wells.web.service.allocate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitPeriodRecrtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncVisitPeriodRecrtDvo;

@Mapper
public interface WsncVisitPeriodRecrtMapper {
    WsncVisitPeriodRecrtDvo selectTempQuery(WsncVisitPeriodRecrtDto.SaveReq dto);

    WsncVisitPeriodRecrtDvo selectTempQuery(WsncVisitPeriodRecrtDvo dvo);
}
