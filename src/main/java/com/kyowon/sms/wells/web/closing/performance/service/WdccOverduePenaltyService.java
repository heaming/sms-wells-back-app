package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverduePenaltyDto.*;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccOverduePenaltyService {

    private final WdccOverduePenaltyMapper mapper;

    public List<FindCodeRes> getCode() {
        return mapper.selectCode();
    }

    public List<SearchPointAggregateRes> getPointAggregates(
        SearchReq req
    ) {
        return mapper.selectPointAggregates(req);
    }

    public List<SearchPointOrderRes> getPointOrders(
        SearchReq req
    ) {
        return mapper.selectPointOrders(req);
    }

    public List<SearchAggregateDateRes> getRegularShippingAggregates(
        SearchReq req
    ) {
        return mapper.selectRegularShippingAggregates(req);
    }

    public List<SearchOrderRes> getRegularShippingOrders(
        SearchReq req
    ) {
        return mapper.selectRegularShippingOrders(req);
    }

    public List<SearchAggregateDateRes> getRegularShippingExceptAggregates(
        SearchReq req
    ) {
        return mapper.selectRegularShippingExceptAggregates(req);
    }

    public List<SearchOrderRes> getRegularShippingExceptOrders(
        SearchReq req
    ) {
        return mapper.selectRegularShippingExceptOrders(req);
    }
}
