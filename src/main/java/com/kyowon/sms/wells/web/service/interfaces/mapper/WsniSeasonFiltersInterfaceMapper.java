package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeasonFiltersInterfaceDto;

@Mapper
public interface WsniSeasonFiltersInterfaceMapper {
    Optional<List<WsniSeasonFiltersInterfaceDto.SearchRes>> selectSeasonFilter(WsniSeasonFiltersInterfaceDto.SearchReq dto);
}
