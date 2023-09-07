package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto.SearchRes;

@Mapper
public interface WsnaStockContinueMonthMgtStateMapper {
    List<SearchRes> selectStockContinueMonthMgtState(SearchReq dto);

}
