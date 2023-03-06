package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.RentalSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SingleSearchRes;

@Mapper
public interface WdcbSalesDetailMapper {

    List<RentalSearchRes> selectSalesDetailRental(SearchReq dto);

    List<MembershipSearchRes> selectSalesDetailMembership(SearchReq dto);

    List<SingleSearchRes> selectSalesDetailSinglePayment(SearchReq dto);
}