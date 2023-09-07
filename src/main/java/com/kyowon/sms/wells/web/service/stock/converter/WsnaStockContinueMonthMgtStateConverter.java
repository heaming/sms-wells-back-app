package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto.SearchRes;

@Mapper(componentModel = "spring")
public interface WsnaStockContinueMonthMgtStateConverter {

    List<SearchRes> mapDvoToSearchRes(List<WsnaStockContinueMonthMgtStateDto> dtos);

}
