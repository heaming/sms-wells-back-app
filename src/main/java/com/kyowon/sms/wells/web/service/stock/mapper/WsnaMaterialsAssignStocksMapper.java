package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMaterialsAssignStocksDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaMaterialsAssignStocksMapper {
    PagingResult<SearchRes> selectMaterialsAssignStocksPaging(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectMaterialsAssignStocksPaging(SearchReq dto);

    int insertQomAsnPrtnrApyIz(WsnaMaterialsAssignStocksDvo dvo);

}
