package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchRes;

@Mapper
public interface WsnaAsMaterialDailyTaskTypeOutOfStoragePsMapper {
    List<SearchRes> selectAsMaterialDailyTaskTypeOutOfStoragePss(SearchReq dto);
}
