package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsServicePresentStateDto.*;

@Mapper
public interface WsncBsServicePresentStateMapper {
    PagingResult<SearchResList> selectBsServicePresentStates(SearchReq dto, PageInfo pageInfo);
    List<SearchResList> selectBsServicePresentStates(SearchReq dto);
    List<SearchResInfo> selectBsServicePresentStateInfo(SearchReq dto);
}
