package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SingleSearchRes;

@Mapper
public interface WdcbProductSalesDetailMapper {

    List<SingleSearchRes> selectProductSalesSinglePaymentDetail(SearchReq dto);

    List<RentalSearchRes> selectProductSalesRentalDetail(SearchReq dto);

    List<MembershipSearchRes> selectProductSalesMembershipDetail(SearchReq dto);
}