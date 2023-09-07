package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaQomAsnStockAggregationMapper {

    List<SearchPdRes> selectProducts();

    List<SearchRes> selectQomAsnStockAggs(SearchReq dto);

}
