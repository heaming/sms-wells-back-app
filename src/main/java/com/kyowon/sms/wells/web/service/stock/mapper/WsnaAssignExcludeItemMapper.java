package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemRemoveDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAssignExcludeItemMapper {

    List<WsnzWellsCodeWareHouseDvo> selectWarehouses();

    PagingResult<SearchRes> selectAssignExcludeItemsPaging(SearchReq dto, PageInfo pageInfo);

    int updateQomAsnExcdIzForRemove(WsnaAssignExcludeItemRemoveDvo dvo);

    int insertQomAsnExcdIz(WsnaAssignExcludeItemDvo dvo);
}
