package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.*;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccSalesBondMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WdccSalesBondService {

    private final WdccSalesBondMapper mapper;

    public List<SearchlumpSumAggregationRes> getSalesBondAggregate(
        SearchReq req
    ) {
        List<SearchlumpSumAggregationRes> res = new ArrayList<SearchlumpSumAggregationRes>();

        if ("1".equals(req.agrgDv())) {
            /*일시블 집계 */
            res = mapper.selectSalesBondAggregate(req);
        }

        return res;
    }

    public List<SearchLumpSumPaymentByDateRes> getAggregateOrderDate(
        SearchReq req
    ) {
        List<SearchLumpSumPaymentByDateRes> res = new ArrayList<SearchLumpSumPaymentByDateRes>();

        if ("2".equals(req.agrgDv()) || "3".equals(req.agrgDv())) {
            /*일시블 일자별, 주문별 */
            res = mapper.selectAggregateOrderDate(req);
        } else if ("4".equals(req.agrgDv())) {
            /*일시블 일자별, 가로계산식 */
            res = mapper.selectHorizontalCalculationExpression(req);
        }

        return res;
    }


    public List<SearchRentalAggregateRes> getRentalAggregate(
        SearchReq req
    ) {

        return mapper.selectRentalAggregate(req);
    }

    public List<SearchLeaseAggregateRes> getLeaseAggregate(
        SearchReq req
    ) {

        return mapper.selectLeaseAggregate(req);
    }

    public List<SearchRentalDayPerOrderRes> getRentalDayPerOrder(
        SearchReq req
    ) {
        List<SearchRentalDayPerOrderRes> res = new ArrayList<SearchRentalDayPerOrderRes>();

        if ("2".equals(req.agrgDv()) || "3".equals(req.agrgDv())) {
            /*렌탈 일별, 주문별*/
            res = mapper.selectRentalDayPerOrder(req);
        } else if ("4".equals(req.agrgDv())) {
            /*렌탈 가로계산식 */
            res = mapper.selectRentalHorizontalFormula(req);
        }

        return res;
    }

    public List<SearchLeaseDayPerOrderRes> getLeaseDayPerOrder(
        SearchReq req
    ) {
        List<SearchLeaseDayPerOrderRes> res = new ArrayList<SearchLeaseDayPerOrderRes>();

        if ("2".equals(req.agrgDv()) || "3".equals(req.agrgDv())) {
            /*리스 일별, 주문별*/
            res = mapper.selectLeaseDayPerOrder(req);
        } else if ("4".equals(req.agrgDv())) {
            res = mapper.selectLeaseHorizontalFormula(req);
        }

        return res;
    }

    public List<SearchMemberAggregateRes> getMemberAggregate(SearchReq req) {
        return mapper.selectMemberAggregate(req);
    }

    public List<SearchMemberDayPerOrderRes> getMemberDayPerOrder(SearchReq req) {

        List<SearchMemberDayPerOrderRes> res = new ArrayList<SearchMemberDayPerOrderRes>();

        if ("2".equals(req.agrgDv()) || "3".equals(req.agrgDv())) {
            /*맴버쉽 일별, 주문별*/
            res = mapper.selectMemberDayPerOrder(req);
        } else if ("4".equals(req.agrgDv())) {
            res = mapper.selectMemberHorizontalFormula(req);
        }

        return res;
    }

    public List<SearchRegularDeliveryAggregateRes> getRegularDeliveryAggregate(SearchReq req) {
        return mapper.selectRegularDeliveryAggregate(req);
    }

    public List<SearchRegularDeliveryDayPerOrdereRes> getRegularDeliveryDayPerOrder(SearchReq req) {

        List<SearchRegularDeliveryDayPerOrdereRes> res = new ArrayList<SearchRegularDeliveryDayPerOrdereRes>();

        if ("2".equals(req.agrgDv()) || "3".equals(req.agrgDv())) {
            /*정기배송 일별, 주문별*/
            res = mapper.selectRegularDeliveryDayPerOrder(req);
        } else if ("4".equals(req.agrgDv())) {
            res = mapper.selectRegularDeliveryHorizontalFormula(req);
        }

        return res;
    }
}
