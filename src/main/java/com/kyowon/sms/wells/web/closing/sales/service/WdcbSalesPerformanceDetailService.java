package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesPerformanceDetailMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesPerformanceDetailService {
    private final WdcbSalesPerformanceDetailMapper mapper;

    public SearchMembershipRes getMembershipSalesDetail(String cntrNo, int cntrSn, String slClYm) {
        return mapper.selectMembershipSalesDetail(cntrNo, cntrSn, slClYm);
    }

    public SearchLeaseRes getLeaseSalesDetail(String cntrDtlNo, String slClYm) {
        return mapper.selectLeaseSalesDetail(cntrDtlNo, slClYm);
    }

    public SearchRentalRes getRentalSalesDetail(String cntrDtlNo, String slClYm) {
        return mapper.selectRentalSalesDetail(cntrDtlNo, slClYm).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

    public SearchRegularRes getRegularShippingDetail(String cntrDtlNo, String slClYm) {
        return mapper.selectRegularShippingDetail(cntrDtlNo, slClYm);
    }
}
