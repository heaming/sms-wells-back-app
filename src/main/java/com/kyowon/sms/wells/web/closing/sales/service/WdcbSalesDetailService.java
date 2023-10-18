package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchSingleRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesDetailService {
    private final WdcbSalesDetailMapper mapper;

    /**
     * 매출 상세정보 조회
     * @param dto
     * @return
     */
    public SearchRentalRes getSalesDetailRental(SearchReq dto) {
        return mapper.selectSalesDetailRental(dto);
    }

    /**
     * 매출 상세정보 조회
     * @param dto
     * @return
     */
    public SearchMembershipRes getSalesDetailMembership(SearchReq dto) {
        return mapper.selectSalesDetailMembership(dto);
    }

    /**
     * 매출 상세정보 조회
     * @param dto
     * @return
     */
    public SearchSingleRes getSalesDetailSinglePayment(SearchReq dto) {
        return mapper.selectSalesDetailSinglePayment(dto);
    }

}
