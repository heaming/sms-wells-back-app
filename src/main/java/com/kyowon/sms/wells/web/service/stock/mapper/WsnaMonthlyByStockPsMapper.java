package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyByStockPsDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaMonthlyByStockPsMapper {

    List<SearchWareRes> selectMonthlyStateWareHouses(SearchWareReq dto);

    List<SearchRes> selectMonthlyByStocksState(SearchReq dto);

}
