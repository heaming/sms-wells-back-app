package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyByStockPsDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaMonthlyByStockPsMapper {

    List<SearchWareRes> selectMonthlyStateWareHouses(SearchWareReq dto);

    PagingResult<SearchRes> selectMonthlyByStocksState(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectMonthlyByStocksState(SearchReq dto);

}
