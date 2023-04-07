package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesDetailDto.SearchSingleRes;

@Mapper
public interface WdcbSalesDetailMapper {

    SearchRentalRes selectSalesDetailRental(String cntrDtlNo);

    SearchMembershipRes selectSalesDetailMembership(String cntrDtlNo);

    SearchSingleRes selectSalesDetailSinglePayment(String cntrDtlNo);
}