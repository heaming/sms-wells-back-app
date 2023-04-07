package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchRentalAgrgRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchRentalPdRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchSingleAgrgRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchSinglePdRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbProductSalesMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbProductSalesService {
    private final WdcbProductSalesMapper mapper;

    public List<SearchSingleAgrgRes> getSinglePaymentAgrgs(SearchReq dto) { // 집계
        List<SearchSingleAgrgRes> result = new ArrayList<>();
        if ("1".equals(dto.taskDiv())) { //일시불
            result = mapper.selectSinglePaymentAgrgs(dto);
        } else if ("3".equals(dto.taskDiv())) { //금융리스
            result = mapper.selectLeaseAgrgs(dto);
        } else if ("5".equals(dto.taskDiv())) { //정기배송
            result = mapper.selectRegularShippingAgrgs(dto);
        }
        return result;
    }

    public List<SearchSinglePdRes> getSinglePaymentProducts(SearchReq dto) { //상품
        List<SearchSinglePdRes> result = new ArrayList<>();
        if ("1".equals(dto.taskDiv())) { //일시불
            result = mapper.selectSinglePaymentProducts(dto);
        } else if ("3".equals(dto.taskDiv())) { //금융리스
            result = mapper.selectLeaseProducts(dto);
        } else if ("5".equals(dto.taskDiv())) { //정기배송
            result = mapper.selectRegularShippingProducts(dto);
        }
        return result;
    }

    public List<SearchRentalAgrgRes> getRentalAgrgs(SearchReq dto) { //렌탈, 집계
        return mapper.selectRentalAgrgs(dto);
    }

    public List<SearchRentalPdRes> getRentalProducts(SearchReq dto) { //렌탈, 상품
        return mapper.selectRentalProducts(dto);
    }

    public List<SearchMembershipRes> getMemberships(SearchReq dto) { //멤버십
        return mapper.selectMemberships(dto);
    }
}
