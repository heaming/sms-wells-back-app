package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemLocationDvo;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.*;

@Mapper
public interface WsnaItemLocationMapper {
    PagingResult<SearchRes> selectItemLocations(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectItemLocations(SearchReq dto);

    int saveItemLocations(List<WsnaItemLocationDvo> voList);

    List<SearchStockCdRes> selectItemLocationsStockCd();

    PagingResult<SearchLocationRes> selectStockItemLocations(SearchLocationReq dto, PageInfo pageInfo);

    List<SearchLocationRes> selectStockItemLocations(SearchLocationReq dto);

    int saveStockItemLocations(List<WsnaItemLocationDvo> voList);

    int updateStandarWarehouse(WsnaItemLocationDvo dvo);
}
