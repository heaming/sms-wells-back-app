package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccSalesBondMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdccSalesBondService {

    private final WdccSalesBondMapper mapper;

    public List<SearchRes> getSalesBondAggregate(
        SearchReq req
    ) {
        return mapper.selectSalesBondAggregate(req);
    }

    public List<SearchRes> getSalesBondDates(
        SearchReq req
    ) {
        return mapper.selectSalesBondDates(req);
    }

    public List<SearchRes> getSalesBondOrders(
        SearchReq req
    ) {
        return mapper.selectSalesBondrders(req);
    }

    public List<SearchRes> getSalesBondMembers(
        SearchReq req
    ) {
        return mapper.selectSalesBondMembers(req);
    }
}
