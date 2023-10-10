package com.kyowon.sms.wells.web.closing.sales.service;

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

    /**
     * 매출상세조회(일시불,금융리스,정기구매)
     * @param dto
     * @return
     */
    public List<SearchSingleRes> getProductSalesSinglePaymentDetails(SearchReq dto) {
        return mapper.selectProductSalesSinglePaymentDetails(dto);
    }

    /**
     * 매출상세조회(렌탈)
     * @param dto
     * @return
     */
    public List<SearchRentalRes> getProductSalesRentalDetails(SearchReq dto) {
        return mapper.selectProductSalesRentalDetails(dto);
    }

    /**
     * 매출상세조회(멤버십)
     * @param dto
     * @return
     */
    public List<SearchMembershipRes> getProductSalesMembershipDetails(SearchReq dto) {
        return mapper.selectProductSalesMembershipDetails(dto);
    }
}
