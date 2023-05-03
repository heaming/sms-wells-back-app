package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyCustomersDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyCustomersDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnyCustomersMapper {
    List<SearchRes> selectCustomers(SearchReq dto);

    PagingResult<SearchRes> selectCustomers(SearchReq dto, PageInfo pageInfo);
}
