package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRes;

@Mapper
public interface WdccProductSalesMapper {
    List<SearchRes> selectBasic(SearchReq dto); //일시불, 금융리스, 정기구매

    List<SearchRentalRes> selectRental(SearchReq dto); //리스, 집계

    List<SearchMembershipRes> selectMembership(SearchReq dto); //정기배송, 집계

    SearchRes selectBasicSummary(SearchReq dto); //일시불, 금융리스, 정기구매

    SearchRentalRes selectRentalSummary(SearchReq dto); //리스, 집계

    SearchMembershipRes selectMembershipSummary(SearchReq dto); //정기배송, 집계
}
