package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.CreateTmlmReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.SearchItmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNewManagerBsConsumableDvo;

@Mapper(componentModel = "spring")
public interface WsnaNewManagerBsConsumableConverter {

    WsnaNewManagerBsConsumableDvo mapCreateTmlmReqToNewManagerBsConsumable(CreateTmlmReq dto);

    List<WsnaNewManagerBsConsumableDvo> mapAllCreateReqToNewManagerBsConsumable(List<CreateReq> dtos);

    List<SearchItmRes> mapAllDvosToSearchItmRes(List<WsnaNewManagerBsConsumableDvo> dvos);

    WsnaNewManagerBsConsumableDvo mapSearchReqToNewManagerBsConsumable(SearchReq dto);

    @Mapping(source = "itmPdCd", target = "csmbPdCd")
    WsnaNewManagerBsConsumableDvo mapWsnaBsConsumablesAskReqDvoToWsnaNewManagerBsConsumableDvo(
        WsnaBsConsumablesAskReqDvo dvo
    );
}
