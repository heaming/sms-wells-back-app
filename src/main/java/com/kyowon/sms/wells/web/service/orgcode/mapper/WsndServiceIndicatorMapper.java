package com.kyowon.sms.wells.web.service.orgcode.mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceIndicatorDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceIndicatorDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsndServiceIndicatorMapper {

    List<SearchRes> selectServiceIndicators(SearchReq dto);

    PagingResult<SearchRes> selectServiceIndicators(SearchReq dto, PageInfo pageInfo);
}
