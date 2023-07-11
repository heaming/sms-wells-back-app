package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingSowPlanDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingSowPlanDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaSeedingSowPlanMapper {

    PagingResult<SearchRes> selectSeedingSowPlansPaging(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectSeedingSowPlansPaging(SearchReq dto);

}
