package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.BaseSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.DepositSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.SalesSearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSinglePaymentDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdcbSinglePaymentMapper {

    BaseSearchRes selectBaseInformation(SearchReq dto);

    PagingResult<SalesSearchRes> selectSalesPerformances(SearchReq dto, PageInfo pageInfo);

    PagingResult<DepositSearchRes> selectDepositItemizations(SearchReq dto, PageInfo pageInfo);

    List<SalesSearchRes> selectSalesPerformances(SearchReq dto);

    List<DepositSearchRes> selectDepositItemizations(SearchReq dto);
}