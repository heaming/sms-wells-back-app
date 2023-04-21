package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverduePenaltyDto.*;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccOverduePenaltyService {

    private final WdccOverduePenaltyMapper mapper;

    public List<FindCodeRes> getCode() {
        return mapper.selectCode();
    }

    public List<SearchMainGridRes> getMainGridAggregate(
        SearchReq req
    ) {
        return mapper.selectMainGridAggregate(req);
    }

    public List<SearchMainGridRes> getMainGridDates(
        SearchReq req
    ) {
        return mapper.selectMainGridDates(req);
    }

    public List<SearchSubGridRes> getSubGridOrder(
        SearchReq req
    ) {
        return mapper.selectSubGridOrder(req);
    }

    public List<SearchThirdGridRes> getThirdGridAggregate(
        SearchReq req
    ) {
        return mapper.selectThirdGridAggregate(req);
    }

    public List<SearchFourthGridRes> getFourthGridOrder(
        SearchReq req
    ) {
        return mapper.selectFourthGridOrder(req);
    }
}
