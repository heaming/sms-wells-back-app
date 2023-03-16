package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondAtamDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondAtamDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccSalesBondAtamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccSalesBondAtamService {

    private final WdccSalesBondAtamMapper mapper;

    public List<SearchRes> getSalesBondAtamAggregateList(
        SearchReq req
    ) {
        return mapper.selectSalesBondAtamAggregateList(req);
    }

    public List<SearchRes> getSalesBondAtamDateList(
        SearchReq req
    ) {
        return mapper.selectSalesBondAtamDateList(req);
    }

    public List<SearchRes> getSalesBondAtamOrderList(
        SearchReq req
    ) {
        return mapper.selectSalesBondAtamOrderList(req);
    }

    public List<SearchRes> getSalesBondAtamMemberList(
        SearchReq req
    ) {
        return mapper.selectSalesBondAtamMemberList(req);
    }
}
