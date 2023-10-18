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

    /**
     * 매출채권/선수금 현황 - SAP상품구분코드 조회
     * @return
     */
    public List<FindCodeRes> getCode() {
        return mapper.selectCode();
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트/집계)
     * @param req
     * @return
     */
    public List<SearchPointAggregateRes> getPointAggregates(
        SearchReq req
    ) {
        return mapper.selectPointAggregates(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트/주문별)
     * @param req
     * @return
     */
    public List<SearchPointOrderRes> getPointOrders(
        SearchReq req
    ) {
        return mapper.selectPointOrders(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일자별)
     * @param req
     * @return
     */
    public List<SearchAggregateDateRes> getAnticipationDates(SearchReq req) {
        return mapper.selectAnticipationDates(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일시불)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationSinglePayments(SearchReq req) {
        return mapper.selectAnticipationSinglePayments(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/멤버십)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationMemberships(SearchReq req) {
        return mapper.selectAnticipationMemberships(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/정기배송)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationRegularShippings(SearchReq req) {
        return mapper.selectAnticipationRegularShippings(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/렌탈)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationRentals(SearchReq req) {
        return mapper.selectAnticipationRentals(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/리스)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationLeases(SearchReq req) {
        return mapper.selectAnticipationLeases(req);
    }
}
