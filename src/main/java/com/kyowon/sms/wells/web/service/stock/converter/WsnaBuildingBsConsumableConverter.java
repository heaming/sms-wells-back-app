package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.CreateTmlmReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaBuildingBsConsumableConverter {
    PagingResult<SearchRes> mapDvoToSearchRes(List<WsnaBuildingBsConsumableDvo> dvos);

    WsnaBuildingBsConsumableDvo mapCreateTmlmReqToCsmbDblv(CreateTmlmReq dto);

    WsnaBuildingBsConsumableDvo mapCreateReqToCsmbDblv(CreateReq dto);
}
