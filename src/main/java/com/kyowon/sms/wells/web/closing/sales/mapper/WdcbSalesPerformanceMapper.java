package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchLeaseMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchMembershipMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRegularMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRentalMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdcbSalesPerformanceMapper {

    SearchRes selectSellTpCd(SearchReq dto);

    SearchRentalRes selectRental(SearchReq dto);

    SearchLeaseRes selectLease(SearchReq dto);

    SearchMembershipRes selectMembership(SearchReq dto);

    SearchRegularRes selectRegular(SearchReq dto);

    PagingResult<SearchRentalMonthlyRes> selectRentalPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRentalMonthlyRes> selectRentalPages(SearchReq dto);

    PagingResult<SearchLeaseMonthlyRes> selectLeasePages(SearchReq dto, PageInfo pageInfo);

    List<SearchLeaseMonthlyRes> selectLeasePages(SearchReq dto);

    PagingResult<SearchMembershipMonthlyRes> selectMembershipPages(SearchReq dto, PageInfo pageInfo);

    List<SearchMembershipMonthlyRes> selectMembershipPages(SearchReq dto);

    PagingResult<SearchRegularMonthlyRes> selectRegularPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRegularMonthlyRes> selectRegularPages(SearchReq dto);
}