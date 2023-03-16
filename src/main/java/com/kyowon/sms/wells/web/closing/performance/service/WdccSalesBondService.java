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

    public List<SearchRes> getSalesBondAggregateList(
        SearchReq req
    ) {
        return mapper.selectSalesBondAggregateList(req);
    }

    public List<SearchRes> getSalesBondDateList(
        SearchReq req
    ) {
        return mapper.selectSalesBondDateList(req);
    }

    public List<SearchRes> getSalesBondOrderList(
        SearchReq req
    ) {
        return mapper.selectSalesBondrderList(req);
    }

    public List<SearchRes> getSalesBondMemberList(
        SearchReq req
    ) {
        return mapper.selectSalesBondMemberList(req);
    }
}
