package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.CreateTmlmReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchItmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;

@Mapper(componentModel = "spring")
public interface WsnaManagerBsConsumableConverter {

    WsnaManagerBsConsumableDvo mapCreateTmlmReqToNewManagerBsConsumable(CreateTmlmReq dto);

    List<WsnaManagerBsConsumableDvo> mapCreateReqToNewManagerBsConsumable(List<CreateReq> dtos);

    List<SearchItmRes> mapAllDvosToSearchItmRes(List<WsnaManagerBsConsumableDvo> dvos);

    WsnaManagerBsConsumableDvo mapSearchReqToManagerBsConsumable(SearchReq dto);

    @Mapping(source = "itmPdCd", target = "csmbPdCd")
    WsnaManagerBsConsumableDvo mapWsnaBsConsumablesAskReqDvoToWsnaManagerBsConsumableDvo(
        WsnaBsConsumablesAskReqDvo dvo
    );
}
