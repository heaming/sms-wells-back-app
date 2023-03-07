package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SingleSearchRes;

@Mapper
public interface WdcbSalesDetailMapper {

    RentalSearchRes selectSalesDetailRental(SearchReq dto);

    MembershipSearchRes selectSalesDetailMembership(SearchReq dto);

    SingleSearchRes selectSalesDetailSinglePayment(SearchReq dto);
}