package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchRgrpRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchSnRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbSnProcessingAgrgMapper {
    PagingResult<SearchSnRes> selectSnProcessingAgrgBySn(SearchReq dto, PageInfo pageInfo);

    List<SearchSnRes> selectSnProcessingAgrgBySn(SearchReq dto);

    PagingResult<SearchRgrpRes> selectSnProcessingAgrgByRgrp(SearchReq dto, PageInfo pageInfo);

    List<SearchRgrpRes> selectSnProcessingAgrgByRgrp(SearchReq dto);

}
