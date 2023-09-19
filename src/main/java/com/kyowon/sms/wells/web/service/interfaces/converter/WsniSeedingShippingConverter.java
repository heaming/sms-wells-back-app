package com.kyowon.sms.wells.web.service.interfaces.converter;

import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchRes;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WsniSeedingShippingConverter {

    List<SearchRes> mapAllDvoToSearchRes(PagingResult<SearchRes> dvos);
}
