package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDetailDto.SearchRentalRes;

@Mapper
public interface WdcbSalesPerformanceDetailMapper {

    SearchMembershipRes selectMembershipSalesDetail(String cntrNo, int cntrSn, String slClYm);

    Optional<SearchRentalRes> selectRentalSalesDetail(String cntrNo, int cntrSn, String slClYm);

    SearchRegularRes selectRegularShippingDetail(String cntrNo, int cntrSn, String slClYm);
}
