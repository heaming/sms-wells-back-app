package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbBsPeriodChangeHistoryListMapper {

    PagingResult<SearchRes> selectBsPeriodChangeHistoryList(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectBsPeriodChangeHistoryList(SearchReq dto);
}
