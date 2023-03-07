package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SingleSearchRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbProductSalesDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbProductSalesDetailService {
    private final WdcbProductSalesDetailMapper mapper;

    public List<SingleSearchRes> getProductSalesSinglePaymentDetails(SearchReq dto) {
        return mapper.selectProductSalesSinglePaymentDetails(dto);
    }

    public List<RentalSearchRes> getProductSalesRentalDetails(SearchReq dto) {
        return mapper.selectProductSalesRentalDetails(dto);
    }

    public List<MembershipSearchRes> getProductSalesMembershipDetails(SearchReq dto) {
        return mapper.selectProductSalesMembershipDetails(dto);
    }
}
