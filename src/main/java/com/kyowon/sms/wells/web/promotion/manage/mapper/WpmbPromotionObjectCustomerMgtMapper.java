package com.kyowon.sms.wells.web.promotion.manage.mapper;

import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.promotion.manage.dvo.WpmbPromotionObjectCustomerMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpmbPromotionObjectCustomerMgtMapper {

    PagingResult<SearchRes> selectPromotionObjectCustomerPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectPromotionObjectCustomerPages(SearchReq dto);

    int insertPromotionObjectCustomer(WpmbPromotionObjectCustomerMgtDvo dvo);

    int updatePromotionObjectCustomer(WpmbPromotionObjectCustomerMgtDvo dvo);

    WpmbPromotionObjectCustomerMgtDvo selectObjectCustomerContractInfo(WpmbPromotionObjectCustomerMgtDvo dvo);

    int deletePromotionObjectCustomer(WpmbPromotionObjectCustomerMgtDvo dvo);
}
