package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNewManagerBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaNewManagerBsConsumableConverter {
    PagingResult<SearchRes> mapDvoToSearchRes(List<WsnaNewManagerBsConsumableDvo> dvos);

    List<SearchRes> mapAllDvoToListSearchRes(List<WsnaNewManagerBsConsumableDvo> dvos);

    WsnaNewManagerBsConsumableDvo mapCreateTmlmReqToNewManagerBsConsumable(CreateTmlmReq dto);

    List<WsnaNewManagerBsConsumableDvo> mapCreateReqToNewManagerBsConsumable(List<CreateReq> dtos);

    List<SearchItmRes> mapAllDvosToSearchItmRes(List<WsnaNewManagerBsConsumableDvo> dvos);

    WsnaNewManagerBsConsumableDvo mapSearchReqToNewManagerBsConsumable(SearchReq dto);
}
