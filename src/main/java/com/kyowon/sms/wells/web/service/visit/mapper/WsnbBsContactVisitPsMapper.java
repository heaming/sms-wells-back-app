package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbBsContactVisitPsMapper {
    PagingResult<SearchRes> selectBsContactVisitPs(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectBsContactVisitPs(SearchReq dto);
}
