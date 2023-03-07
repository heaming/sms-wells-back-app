package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SingleSearchRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbProductSalesMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbProductSalesService {
    private final WdcbProductSalesMapper mapper;

    public List<SingleSearchRes> getProductSalesSinglePayments(SearchReq dto) {
        return mapper.selectProductSalesSinglePayments(dto);
    }

    public List<RentalSearchRes> getProductSalesRentals(SearchReq dto) {
        return mapper.selectProductSalesRentals(dto);
    }

    public List<MembershipSearchRes> getProductSalesMemberships(SearchReq dto) {
        return mapper.selectProductSalesMemberships(dto);
    }
}
