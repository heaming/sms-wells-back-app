package com.kyowon.sms.wells.web.service.visit.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFalseVisitDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFalseVisitDto.SearchRes;

@Mapper
public interface WsnbFalseVisitMapper {
    PagingResult<SearchRes> selectFalseVisits(SearchReq dto, PageInfo pageInfo);
}
