package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.CreateTmlmReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaManagerBsConsumableConverter {
    PagingResult<SearchRes> mapDvoToSearchRes(List<WsnaManagerBsConsumableDvo> dvos);

    WsnaManagerBsConsumableDvo mapCreateTmlmReqToNewManagerBsConsumable(CreateTmlmReq dto);

    List<WsnaManagerBsConsumableDvo> mapCreateReqToNewManagerBsConsumable(List<CreateReq> dtos);
}
