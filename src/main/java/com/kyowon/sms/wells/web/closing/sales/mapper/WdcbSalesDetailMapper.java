package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchSingleRes;

@Mapper
public interface WdcbSalesDetailMapper {

    SearchRentalRes selectSalesDetailRental(SearchReq dto);

    SearchMembershipRes selectSalesDetailMembership(SearchReq dto);

    SearchSingleRes selectSalesDetailSinglePayment(SearchReq dto);
}
