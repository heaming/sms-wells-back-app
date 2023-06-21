package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFinishLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFinishLocationDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnbFinishLocationMapper {
    PagingResult<SearchRes> selectFinishLocations(SearchReq dto, PageInfo pageInfo);
}
