package com.kyowon.sms.wells.web.promotion.manage.mapper;

import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchRes;

import java.util.List;

import com.kyowon.sms.wells.web.promotion.manage.dvo.WpmbPromotionObjectCustomerDvo;
import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpmbPromotionObjectCustomerMgtMapper {

    PagingResult<SearchRes> selectPromotionObjectCustomerPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectPromotionObjectCustomerPages(SearchReq dto);

    int insertPromotionObjectCustomer(WpmbPromotionObjectCustomerDvo dvo);

    int updatePromotionObjectCustomer(WpmbPromotionObjectCustomerDvo dvo);

    WpmbPromotionObjectCustomerDvo selectObjectCustomerContractInfo(WpmbPromotionObjectCustomerDvo dvo);

    int deletePromotionObjectCustomer(WpmbPromotionObjectCustomerDvo dvo);
}
