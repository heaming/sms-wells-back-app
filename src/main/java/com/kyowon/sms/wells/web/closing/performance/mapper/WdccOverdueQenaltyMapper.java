package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverdueQenaltyDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverdueQenaltyDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccOverdueQenaltyMapper {
    List<SearchRes> selectSalesBondAdditionalChargesAggregate(SearchReq req);

    List<SearchRes> selectSalesBondAdditionalChargesDates(SearchReq req);

    List<SearchRes> selectSalesBondAdditionalChargesOrders(SearchReq req);

    List<SearchRes> selectSalesBondAdditionalChargesMembers(SearchReq req);
}
