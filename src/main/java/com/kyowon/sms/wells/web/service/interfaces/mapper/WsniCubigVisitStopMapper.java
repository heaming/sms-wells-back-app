package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCubigVisitStopDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCubigVisitStopDvo;

@Mapper
public interface WsniCubigVisitStopMapper {
    CreateRes insertCubigVisitStop(WsniCubigVisitStopDvo dvo);
}
