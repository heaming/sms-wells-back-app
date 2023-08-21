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

    public List<SearchAggregateDateRes> getAnticipationDates(SearchReq req) {
        return mapper.selectAnticipationDates(req);
    }

    public List<SearchOrderRes> getAnticipationSinglePayments(SearchReq req) {
        return mapper.selectAnticipationSinglePayments(req);
    }

    public List<SearchOrderRes> getAnticipationMemberships(SearchReq req) {
        return mapper.selectAnticipationMemberships(req);
    }

    public List<SearchOrderRes> getAnticipationRegularShippings(SearchReq req) {
        return mapper.selectAnticipationRegularShippings(req);
    }

    public List<SearchOrderRes> getAnticipationRentals(SearchReq req) {
        return mapper.selectAnticipationRentals(req);
    }

    public List<SearchOrderRes> getAnticipationLeases(SearchReq req) {
        return mapper.selectAnticipationLeases(req);
    }
}
