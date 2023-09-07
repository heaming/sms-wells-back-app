package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageCostByTaskTypeDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageCostByTaskTypeDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaOutOfStorageCostByTaskTypeMapper {
    PagingResult<SearchRes> selectOutOfStorageCostByTaskType(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectOutOfStorageCostByTaskType(SearchReq dto);
}
