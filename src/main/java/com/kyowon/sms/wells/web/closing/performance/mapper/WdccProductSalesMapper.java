package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.closing.performance.dvo.WdccProductSalesSapMaterialsDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchSapMaterialsRes;

@Mapper
public interface WdccProductSalesMapper {
    List<SearchRes> selectProductSalesSinglePayments(SearchReq dto);

    List<SearchRes> selectProductSalesLeases(SearchReq dto);

    List<SearchRes> selectProductSalesRegularDeliveries(SearchReq dto);

    List<SearchRentalRes> selectProductSalesRentals(SearchReq dto); //리스, 집계

    List<SearchMembershipRes> selectProductSalesMemberships(SearchReq dto); //정기배송, 집계

    List<WdccProductSalesSapMaterialsDvo> selectProductSalesSapMaterials(SearchReq dto);
}
