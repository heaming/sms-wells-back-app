package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageDetailDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageDetailDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaFilterOutOfStorageDetailMapper {

    PagingResult<SearchRes> selectFilterOutOfStorageDetails(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectFilterOutOfStorageDetails(SearchReq dto);
}
