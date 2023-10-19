package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceCancelPsDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceCancelPsDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnbServiceCancelPsMapper {
    PagingResult<WsnbServiceCancelPsDvo> selectServiceCancelPsPages(SearchReq dto, PageInfo pageInfo);
    List<WsnbServiceCancelPsDvo> selectServiceCancelPsPages(SearchReq dto);
}
