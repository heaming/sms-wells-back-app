package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchAggregateRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchOrderUnitRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccDelinquentAdditionalChargesMapper {

    List<SearchAggregateRes> selectRentalAggregate(SearchReq req);

    List<SearchOrderUnitRes> selectRentalOrderUnit(SearchReq req);

    List<SearchAggregateRes> selectLeaseAggregate(SearchReq req);

    List<SearchOrderUnitRes> selectLeaseOrderUnit(SearchReq req);

    List<SearchAggregateRes> selectMemberShipAggregate(SearchReq req);

    List<SearchOrderUnitRes> selectMemberShipOrderUnit(SearchReq req);

    List<SearchAggregateRes> selectRegularDeliveryAggregate(SearchReq req);

    List<SearchOrderUnitRes> selectRegularDeliveryOrderUnit(SearchReq req);
}
