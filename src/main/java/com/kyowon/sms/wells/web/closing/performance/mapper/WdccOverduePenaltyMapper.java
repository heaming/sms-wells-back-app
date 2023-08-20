package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverduePenaltyDto.*;

@Mapper
public interface WdccOverduePenaltyMapper {

    List<FindCodeRes> selectCode();

    List<SearchPointAggregateRes> selectPointAggregates(SearchReq req);

    List<SearchPointOrderRes> selectPointOrders(SearchReq req);

    List<SearchAggregateDateRes> selectAnticipationDates(SearchReq req);

    List<SearchOrderRes> selectAnticipationSinglePayments(SearchReq req);

    List<SearchOrderRes> selectAnticipationMemberships(SearchReq req);

    List<SearchOrderRes> selectAnticipationRegularShippings(SearchReq req);
}
