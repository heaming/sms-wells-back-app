package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDelDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAssignExcludeItemMapper {

    List<WsnzWellsCodeWareHouseDvo> selectWarehouse();

    PagingResult<SearchRes> selectAssignExcludeItemsPaging(SearchReq dto, PageInfo pageInfo);

    int updateQomAsnExcdIzForRemove(WsnaAssignExcludeItemDelDvo dvo);

    int insertQomAsnExcdIz(WsnaAssignExcludeItemDvo dvo);
}
