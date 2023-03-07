package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SingleSearchRes;

@Mapper
public interface WdcbProductSalesMapper {

    List<SingleSearchRes> selectProductSalesSinglePayments(SearchReq dto);

    List<RentalSearchRes> selectProductSalesRentals(SearchReq dto);

    List<MembershipSearchRes> selectProductSalesMemberships(SearchReq dto);
}