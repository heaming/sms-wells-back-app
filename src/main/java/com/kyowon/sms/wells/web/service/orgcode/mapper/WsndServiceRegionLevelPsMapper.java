package com.kyowon.sms.wells.web.service.orgcode.mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceRegionLevelPsDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsndServiceRegionLevelPsMapper {
    PagingResult<SearchRes> selectServiceRegionLevelPss(SearchReq dto, PageInfo pageInfo);
    List<SearchRes> selectServiceRegionLevelPss(SearchReq dto);
}
