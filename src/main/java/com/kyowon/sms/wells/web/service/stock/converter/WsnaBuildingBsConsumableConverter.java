package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaBuildingBsConsumableConverter {
    List<SearchRes> mapAllDvoToSearchRes(List<WsnaBuildingBsConsumableDvo> dvos);

    PagingResult<SearchRes> mapDvoToSearchRes(List<WsnaBuildingBsConsumableDvo> dvos);

    WsnaBuildingBsConsumableDvo mapCreateTmlmReqToCsmbDblv(CreateTmlmReq dto);

    WsnaBuildingBsConsumableDvo mapCreateReqToCsmbDblv(CreateReq dto);

    List<SearchItmRes> mapAllDvosToSearchItmRes(List<WsnaBuildingBsConsumableDvo> dvos);

    WsnaBuildingBsConsumableDvo mapSearchReqToBuildingBsConsumable(SearchReq dto);
}
