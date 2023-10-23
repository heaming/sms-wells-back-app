package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaAsMaterialDailyTaskTypeOutOfStoragePsMapper {
    PagingResult<SearchRes> selectAsMaterialDailyTaskTypeOutOfStoragePss(SearchReq dto, PageInfo pageInfo);
}
