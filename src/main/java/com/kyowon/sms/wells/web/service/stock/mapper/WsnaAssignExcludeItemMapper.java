package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.WareRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import java.util.List;

@Mapper
public interface WsnaAssignExcludeItemMapper {
    PagingResult<SearchRes> selectAssignExcludeItems(SearchReq dto, PageInfo pageInfo);

    List<WareRes> selectWarehouse(SearchReq dto);

    int saveAssignExcludeItems(WsnaAssignExcludeItemDvo dvo);
}
