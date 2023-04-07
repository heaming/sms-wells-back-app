package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchRentalAgrgRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchRentalPdRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchSingleAgrgRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDto.SearchSinglePdRes;

@Mapper
public interface WdcbProductSalesMapper {

    List<SearchSingleAgrgRes> selectSinglePaymentAgrgs(SearchReq dto); //일시불, 집계

    List<SearchSingleAgrgRes> selectLeaseAgrgs(SearchReq dto); //리스, 집계

    List<SearchSingleAgrgRes> selectRegularShippingAgrgs(SearchReq dto); //정기배송, 집계

    List<SearchSinglePdRes> selectSinglePaymentProducts(SearchReq dto); //일시불,상품

    List<SearchSinglePdRes> selectLeaseProducts(SearchReq dto); //리스, 상품

    List<SearchSinglePdRes> selectRegularShippingProducts(SearchReq dto); //정기배송, 상품

    List<SearchRentalAgrgRes> selectRentalAgrgs(SearchReq dto); //렌탈, 집계

    List<SearchRentalPdRes> selectRentalProducts(SearchReq dto); //렌탈, 상품

    List<SearchMembershipRes> selectMemberships(SearchReq dto); // 멤버십
}