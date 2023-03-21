package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.LeaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.MembershipSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchReq;

@Mapper
public interface WdcbSalesPerformanceDetailMapper {

    MembershipSearchRes selectMembershipSalesDetail(SearchReq dto);

    LeaseSearchRes selectLeaseSalesDetail(SearchReq dto);
}