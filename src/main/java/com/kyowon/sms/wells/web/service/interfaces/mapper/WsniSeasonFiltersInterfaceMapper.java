package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeasonFiltersInterfaceDto;

@Mapper
public interface WsniSeasonFiltersInterfaceMapper {
    List<WsniSeasonFiltersInterfaceDto.SearchRes> selectSeasonFilter(WsniSeasonFiltersInterfaceDto.SearchReq dto);
}
