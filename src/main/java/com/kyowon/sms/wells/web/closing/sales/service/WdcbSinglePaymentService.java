package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.BaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.DepositSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.SalesSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSinglePaymentMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSinglePaymentService {
    private final WdcbSinglePaymentMapper mapper;

    public BaseSearchRes getBaseInformation(SearchReq dto) {
        return mapper.selectBaseInformation(dto);
    }

    public PagingResult<SalesSearchRes> getSalesPerformances(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSalesPerformances(dto, pageInfo);
    }

    public PagingResult<DepositSearchRes> getDepositItemizations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectDepositItemizations(dto, pageInfo);
    }

    public List<SalesSearchRes> getSalesPerformanceExcelDownload(SearchReq dto) {
        return mapper.selectSalesPerformances(dto);
    }

    public List<DepositSearchRes> getDepositItemizationExcelDownload(SearchReq dto) {
        return mapper.selectDepositItemizations(dto);
    }
}
