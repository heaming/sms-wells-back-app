package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaOutOfStorageIzDtlMapper {
    PagingResult<SearchRes> getOutOfStorageIzDtls(SearchReq dto, PageInfo pageInfo);
}
