package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.BaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SalesSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesPerformanceMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesPerformanceService {
    private final WdcbSalesPerformanceMapper mapper;

    public BaseSearchRes getBaseInformation(SearchReq dto) {
        return mapper.selectBaseInformation(dto);
    }

    public PagingResult<SalesSearchRes> getSalesPerformancePages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSalesPerformances(dto, pageInfo);
    }

    public List<SalesSearchRes> getSalesPerformanceExcelDownload(SearchReq dto) {
        return mapper.selectSalesPerformances(dto);
    }
}
