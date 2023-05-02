package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnyCustomerBaseInformationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnyCustomerBaseInformationDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnyCustomerBaseInformationMapper {
    List<SearchRes> selectCustomerBases(SearchReq dto);

    PagingResult<SearchRes> selectCustomerBases(SearchReq dto, PageInfo pageInfo);
}
