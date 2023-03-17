package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SingleSearchRes;

@Mapper
public interface WdcbProductSalesDetailMapper {

    List<SingleSearchRes> selectProductSalesSinglePaymentDetails(SearchReq dto);

    List<SingleSearchRes> selectProductSalesLeaseDetails(SearchReq dto);

    List<SingleSearchRes> selectProductSalesFarmSaleDetails(SearchReq dto);

    List<RentalSearchRes> selectProductSalesRentalDetails(SearchReq dto);

    List<MembershipSearchRes> selectProductSalesMembershipDetails(SearchReq dto);
}