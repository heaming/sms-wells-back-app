package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCenterCustomerAgrgByProductDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCenterCustomerAgrgByProductDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbCenterCustomerAgrgByProductMapper {
    PagingResult<SearchRes> selectCenterCustomerAgrgByProduct(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectCenterCustomerAgrgByProduct(SearchReq dto);

}
