package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchSingleRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesDetailService {
    private final WdcbSalesDetailMapper mapper;

    public SearchRentalRes getSalesDetailRental(String cntrDtlNo) {
        return mapper.selectSalesDetailRental(cntrDtlNo);
    }

    public SearchMembershipRes getSalesDetailMembership(String cntrDtlNo) {
        return mapper.selectSalesDetailMembership(cntrDtlNo);
    }

    public SearchSingleRes getSalesDetailSinglePayment(String cntrDtlNo) {
        return mapper.selectSalesDetailSinglePayment(cntrDtlNo);
    }

}
