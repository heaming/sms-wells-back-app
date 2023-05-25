package com.kyowon.sms.wells.web.service.orgcode.mapper;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndHumanResourcesDto.SearchReq;
import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndHumanResourcesDto.SearchRes;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsndHumanResourcesMapper {
    PagingResult<SearchRes> selectHumanResources(SearchReq dto, PageInfo pageInfo);
}
