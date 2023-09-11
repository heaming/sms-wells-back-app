package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignCprHvMatPsDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnaAssignCprHvMatPsMapper {
    PagingResult<SearchRes> selectAssignCprHvMatPss(SearchReq dto, PageInfo pageInfo);
    List<SearchRes> selectAssignCprHvMatPss(SearchReq dto);
}
