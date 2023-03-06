package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SingleSearchRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesDetailService {
    private final WdcbSalesDetailMapper mapper;

    public List<RentalSearchRes> getSalesDetailRental(SearchReq dto) {
        return mapper.selectSalesDetailRental(dto);
    }

    public List<MembershipSearchRes> getSalesDetailMembership(SearchReq dto) {
        return mapper.selectSalesDetailMembership(dto);
    }

    public List<SingleSearchRes> getSalesDetailSinglePayment(SearchReq dto) {
        return mapper.selectSalesDetailSinglePayment(dto);
    }

}
