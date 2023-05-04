package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncContactHistoryDto;

@Mapper
public interface WsncContactHistoryMapper {
    List<WsncContactHistoryDto.SearchRes> selectContactHistory(WsncContactHistoryDto.SearchReq dto);
}
