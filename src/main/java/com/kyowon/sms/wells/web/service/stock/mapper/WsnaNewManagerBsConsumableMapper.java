package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.FindTmlmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.SearchItmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNewManagerBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaNewManagerBsConsumableMapper {
    List<SearchItmRes> selectItems(String mngtYm);

    PagingResult<WsnaNewManagerBsConsumableDvo> selectBuildings(SearchReq dto, PageInfo pageInfo);

    List<WsnaNewManagerBsConsumableDvo> selectItemQtys(String mngtYm, String prtnrNo);

    List<WsnaNewManagerBsConsumableDvo> selectItemFirstQtys(String mngtYm, String prtnrNo);

    FindTmlmRes selectNewManagerBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectNewManagerBsConsumableAplcFirstClose(String mngtYm);

    int mergeNewManagerBsConsumableAplcClose(WsnaNewManagerBsConsumableDvo dvo);

    int mergeNewManagerBsConsumables(List<WsnaNewManagerBsConsumableDvo> dvos);
}
