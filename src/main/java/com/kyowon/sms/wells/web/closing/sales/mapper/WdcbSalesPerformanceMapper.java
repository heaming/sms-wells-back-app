package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.BaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SalesSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdcbSalesPerformanceMapper {

    BaseSearchRes selectBaseInformation(SearchReq dto);

    PagingResult<SalesSearchRes> selectSalesPerformances(SearchReq dto, PageInfo pageInfo);

    List<SalesSearchRes> selectSalesPerformances(SearchReq dto);
}