package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRentalRes;

@Mapper
public interface WdcbSalesPerformanceDetailMapper {

    SearchMembershipRes selectMembershipSalesDetail(String slDt);

    SearchLeaseRes selectLeaseSalesDetail(String slDt);

    SearchRentalRes selectRentalSalesDetail(String slDt);

    SearchRegularRes selectRegularShippingDetail(String slDt);
}