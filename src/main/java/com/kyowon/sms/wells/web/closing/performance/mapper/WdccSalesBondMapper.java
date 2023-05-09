package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccSalesBondMapper {

    List<SearchlumpSumAggregationRes> selectSalesBondAggregate(SearchReq req);

    List<SearchLumpSumPaymentByDateRes> selectAggregateOrderDate(SearchReq req);

    List<SearchLumpSumPaymentByDateRes> selectHorizontalCalculationExpression(SearchReq req);

    List<SearchRentalAggregateRes> selectRentalAggregate(SearchReq req);

    List<SearchLeaseAggregateRes> selectLeaseAggregate(SearchReq req);

    List<SearchRentalDayPerOrderRes> selectRentalDayPerOrder(SearchReq req);

    List<SearchRentalDayPerOrderRes> selectRentalHorizontalFormula(SearchReq req);

    List<SearchLeaseDayPerOrderRes> selectLeaseDayPerOrder(SearchReq req);

    List<SearchLeaseDayPerOrderRes> selectLeaseHorizontalFormula(SearchReq req);

    List<SearchMemberAggregateRes> selectMemberAggregate(SearchReq req);

    List<SearchMemberDayPerOrderRes> selectMemberDayPerOrder(SearchReq req);

    List<SearchMemberDayPerOrderRes> selectMemberHorizontalFormula(SearchReq req);

    List<SearchRegularDeliveryAggregateRes> selectRegularDeliveryAggregate(SearchReq req);

    List<SearchRegularDeliveryDayPerOrdereRes> selectRegularDeliveryDayPerOrder(SearchReq req);

    List<SearchRegularDeliveryDayPerOrdereRes> selectRegularDeliveryHorizontalFormula(SearchReq req);
}
