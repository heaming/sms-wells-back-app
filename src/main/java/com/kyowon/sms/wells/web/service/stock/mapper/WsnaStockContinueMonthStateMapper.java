package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto.SearchRes;

@Mapper
public interface WsnaStockContinueMonthStateMapper {
    List<SearchRes> selectStockContinueMonthState(SearchReq dto);

}
