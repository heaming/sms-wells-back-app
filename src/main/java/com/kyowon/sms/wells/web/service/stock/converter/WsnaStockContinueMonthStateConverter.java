package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto.SearchRes;

@Mapper(componentModel = "spring")
public interface WsnaStockContinueMonthStateConverter {

    List<SearchRes> mapDvoToSearchRes(List<WsnaStockContinueMonthStateDto> dtos);

}
