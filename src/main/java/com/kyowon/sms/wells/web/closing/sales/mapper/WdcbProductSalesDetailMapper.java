package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchSingleRes;

@Mapper
public interface WdcbProductSalesDetailMapper {

    List<SearchSingleRes> selectProductSalesSinglePaymentDetails(SearchReq dto);

    List<SearchSingleRes> selectProductSalesLeaseDetails(SearchReq dto);

    List<SearchSingleRes> selectProductSalesFarmSaleDetails(SearchReq dto);

    List<SearchRentalRes> selectProductSalesRentalDetails(SearchReq dto);

    List<SearchMembershipRes> selectProductSalesMembershipDetails(SearchReq dto);
}