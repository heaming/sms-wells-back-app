package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialOutOfStoragePsDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaAsMaterialOutOfStoragePsConverter {

    PagingResult<SearchRes> mapDvoToSearchResPages(List<WsnaAsMaterialOutOfStoragePsDvo> dvos);

    List<SearchRes> mapDvoToSearchRes(List<WsnaAsMaterialOutOfStoragePsDvo> dvos);
}
