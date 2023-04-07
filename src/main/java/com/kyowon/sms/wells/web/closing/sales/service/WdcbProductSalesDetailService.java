package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchSingleRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbProductSalesDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbProductSalesDetailService {
    private final WdcbProductSalesDetailMapper mapper;

    public List<SearchSingleRes> getProductSalesSinglePaymentDetails(SearchReq dto) {
        List<SearchSingleRes> result = new ArrayList<SearchSingleRes>();
        if ("1".equals(dto.taskDiv())) {
            result = mapper.selectProductSalesSinglePaymentDetails(dto);
        } else if ("3".equals(dto.taskDiv())) {
            result = mapper.selectProductSalesLeaseDetails(dto);
        } else if ("5".equals(dto.taskDiv())) {
            result = mapper.selectProductSalesFarmSaleDetails(dto);
        }
        return result;
    }

    public List<SearchRentalRes> getProductSalesRentalDetails(SearchReq dto) {
        return mapper.selectProductSalesRentalDetails(dto);
    }

    public List<SearchMembershipRes> getProductSalesMembershipDetails(SearchReq dto) {
        return mapper.selectProductSalesMembershipDetails(dto);
    }
}
