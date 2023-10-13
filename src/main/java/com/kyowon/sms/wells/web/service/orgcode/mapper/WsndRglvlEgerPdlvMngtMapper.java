package com.kyowon.sms.wells.web.service.orgcode.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRglvlEgerPdlvMngtDto.*;

import java.util.List;

@Mapper
public interface WsndRglvlEgerPdlvMngtMapper {
    PagingResult<SearchRes> selectRglvlEgerPdlvMngtPages(SearchReq dto, PageInfo pageInfo);
    List<SearchRes> selectRglvlEgerPdlvMngtPages(SearchReq dto);
}
