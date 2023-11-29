package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaManagerBsConsumableConverter {
    List<SearchRes> mapAllDvoToSearchRes(List<WsnaManagerBsConsumableDvo> dvos);

    PagingResult<SearchRes> mapDvoToSearchRes(List<WsnaManagerBsConsumableDvo> dvos);

    WsnaManagerBsConsumableDvo mapCreateTmlmReqToNewManagerBsConsumable(CreateTmlmReq dto);

    List<WsnaManagerBsConsumableDvo> mapCreateReqToNewManagerBsConsumable(List<CreateReq> dtos);

    List<SearchItmRes> mapAllDvosToSearchItmRes(List<WsnaManagerBsConsumableDvo> dvos);

    WsnaManagerBsConsumableDvo mapSearchReqToManagerBsConsumable(SearchReq dto);
}
