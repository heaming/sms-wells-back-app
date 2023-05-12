package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchLeaseMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchMembershipMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRegularMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRentalMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRes;
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

    public SearchRes getBaseInformation(SearchReq dto) {
        return mapper.selectSellTpCd(dto);
    }

    public SearchRentalRes getRental(SearchReq dto) {
        return mapper.selectRental(dto);
    }

    public SearchLeaseRes getLease(SearchReq dto) {
        return mapper.selectLease(dto);
    }

    public SearchMembershipRes getMembership(SearchReq dto) {
        return mapper.selectMembership(dto);
    }

    public SearchRegularRes getRegular(SearchReq dto) {
        return mapper.selectRegular(dto);
    }

    public PagingResult<SearchRentalMonthlyRes> getRentalPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRentalPages(dto, pageInfo);
    }

    public List<SearchRentalMonthlyRes> getRentalExcelDownload(SearchReq dto) {
        return mapper.selectRentalPages(dto);
    }

    public PagingResult<SearchLeaseMonthlyRes> getLeasePages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectLeasePages(dto, pageInfo);
    }

    public List<SearchLeaseMonthlyRes> getLeaseExcelDownload(SearchReq dto) {
        return mapper.selectLeasePages(dto);
    }

    public PagingResult<SearchMembershipMonthlyRes> getMembershipPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectMembershipPages(dto, pageInfo);
    }

    public List<SearchMembershipMonthlyRes> getMembershipExcelDownload(SearchReq dto) {
        return mapper.selectMembershipPages(dto);
    }

    public PagingResult<SearchRegularMonthlyRes> getRegularPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRegularPages(dto, pageInfo);
    }

    public List<SearchRegularMonthlyRes> getRegularExcelDownload(SearchReq dto) {
        return mapper.selectRegularPages(dto);
    }
}
