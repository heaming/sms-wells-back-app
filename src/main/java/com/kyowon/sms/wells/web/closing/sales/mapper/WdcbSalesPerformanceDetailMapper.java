package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;

@Mapper
public interface WdcbSalesPerformanceDetailMapper {

    SearchMembershipRes selectMembershipSalesDetail(String slDt);

    SearchLeaseRes selectLeaseSalesDetail(String slDt);
}