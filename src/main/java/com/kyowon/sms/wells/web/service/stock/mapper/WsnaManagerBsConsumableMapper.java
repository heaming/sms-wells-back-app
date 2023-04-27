package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.FindTmlmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchItmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaManagerBsConsumableMapper {
    List<SearchItmRes> selectItems(String mngtYm);

    PagingResult<WsnaManagerBsConsumableDvo> selectBuildings(SearchReq dto, PageInfo pageInfo);

    List<WsnaManagerBsConsumableDvo> selectItemQtys(String mngtYm, String prtnrNo);

    List<WsnaManagerBsConsumableDvo> selectItemFirstQtys(String mngtYm, String prtnrNo);

    FindTmlmRes selectManagerBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectManagerBsConsumableAplcFirstClose(String mngtYm);

    int mergeManagerBsConsumableAplcClose(WsnaManagerBsConsumableDvo dvo);

    int mergeManagerBsConsumables(List<WsnaManagerBsConsumableDvo> dvos);
}
