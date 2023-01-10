package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyCalendarDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyCalendarDvo;

@Mapper
public interface WsnyCalendarMapper {
    List<WsnyCalendarDto.SearchRes> selectCalendar(
        WsnyCalendarDto.SearchReq dto
    );

    int updateCalendar(WsnyCalendarDvo dvo);

    int saveCalendar(WsnyCalendarDvo dvo);

    WsnyCalendarDto.FindRegRes selectCalendarDay(
        WsnyCalendarDto.FineRegReq dto
    );
}
