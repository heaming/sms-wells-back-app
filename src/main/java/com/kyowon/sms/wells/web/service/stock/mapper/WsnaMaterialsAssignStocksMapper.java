package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.OgRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.PrtnrRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMaterialsAssignStocksDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaMaterialsAssignStocksMapper {
    PagingResult<SearchRes> selectMaterialsAssignStocks(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectMaterialsAssignStocks(SearchReq dto);

    int insertMaterialsAssignStocks(List<WsnaMaterialsAssignStocksDvo> list);

    List<PrtnrRes> selectPartners(PrtnrRes dto);

    List<OgRes> selectOrganizations(OgRes dto);
}
