package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryAggregateDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaBsCsmbDeliveryAggregateConverter {
    PagingResult<SearchRes> mapDvoToSearchResPages(List<WsnaBsCsmbDeliveryAggregateDvo> dvos);

    List<SearchRes> mapDvoToSearchRes(List<WsnaBsCsmbDeliveryAggregateDvo> dvos);
}
