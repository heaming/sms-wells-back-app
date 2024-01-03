package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemByStockAggregationDto.SearchWareRes;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemByStockAggregationDvo;

@Mapper
public interface WsnaItemByStockAggregationMapper {

    List<SearchWareRes> selectItemByStockWareHouses(String baseDt, String wareDvCd);

    List<HashMap<String, Object>> selectItemByStockAggs(WsnaItemByStockAggregationDvo dvo);

}
