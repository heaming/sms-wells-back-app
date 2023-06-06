package com.kyowon.sms.wells.web.service.interfaces.mapper;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchReq;
import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsniServiceHistoryInterfaceMapper {
    List<SearchRes> selectServiceHistory(SearchReq dto);
}
