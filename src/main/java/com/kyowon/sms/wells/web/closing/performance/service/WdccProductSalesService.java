package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductSalesMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdccProductSalesService {
    private final WdccProductSalesMapper mapper;

    /**
     * 상품별 매출현황
     * @param dto 검색조건
     * @return result 조회목록
     */
    public List<SearchRes> getBasicList(
        SearchReq dto
    ) {
        return mapper.selectBasic(dto);
    }

    /**
     * 상품별 매출현황
     * @param dto 검색조건
     * @return result 조회목록
     */
    public List<SearchRentalRes> getRentalList(
        SearchReq dto
    ) {
        return mapper.selectRental(dto);
    }

    /**
     * 상품별 매출현황
     * @param dto 검색조건
     * @return result 조회목록
     */
    public List<SearchMembershipRes> getMembershipList(
        SearchReq dto
    ) {
        return mapper.selectMembership(dto);
    }
}
