package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRentalRes;

@Mapper
public interface WdcbSalesPerformanceDetailMapper {

    SearchMembershipRes selectMembershipSalesDetail(String cntrDtlNo, String slClYm);

    SearchLeaseRes selectLeaseSalesDetail(String cntrDtlNo, String slClYm);

    SearchRentalRes selectRentalSalesDetail(String cntrDtlNo, String slClYm);

    SearchRegularRes selectRegularShippingDetail(String cntrDtlNo, String slClYm);
}