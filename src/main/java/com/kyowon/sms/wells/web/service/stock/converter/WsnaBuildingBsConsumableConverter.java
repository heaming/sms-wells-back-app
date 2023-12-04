package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.CreateTmlmReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchItmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;

@Mapper(componentModel = "spring")
public interface WsnaBuildingBsConsumableConverter {

    WsnaBuildingBsConsumableDvo mapCreateTmlmReqToCsmbDblv(CreateTmlmReq dto);

    List<SearchItmRes> mapAllDvosToSearchItmRes(List<WsnaBuildingBsConsumableDvo> dvos);

    WsnaBuildingBsConsumableDvo mapSearchReqToBuildingBsConsumable(SearchReq dto);

    WsnaBuildingBsConsumableDvo mapCreateReqToWsnaBuildingBsConsumableDvo(CreateReq dto);
}
