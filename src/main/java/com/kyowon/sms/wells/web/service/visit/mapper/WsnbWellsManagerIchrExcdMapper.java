package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsManagerIchrExcdDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsManagerIchrExcdDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnbWellsManagerIchrExcdMapper {
    PagingResult<SearchRes> selectWellsManagerInchargeExcds(SearchReq dto, PageInfo pageInfo);
}
