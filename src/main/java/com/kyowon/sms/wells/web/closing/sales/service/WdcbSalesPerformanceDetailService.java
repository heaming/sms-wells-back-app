package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesPerformanceDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesPerformanceDetailService {
    private final WdcbSalesPerformanceDetailMapper mapper;

    public SearchMembershipRes getMembershipSalesDetail(SearchReq dto) {
        return mapper.selectMembershipSalesDetail(dto);
    }

    public SearchLeaseRes getLeaseSalesDetail(SearchReq dto) {
        return mapper.selectLeaseSalesDetail(dto);
    }
}
