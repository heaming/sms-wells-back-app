package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerPsByProductDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerPsByProductDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbCustomerPsByProductMapper {
    PagingResult<SearchRes> selectCustomerPsByProduct(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectCustomerPsByProduct(SearchReq dto);

}
