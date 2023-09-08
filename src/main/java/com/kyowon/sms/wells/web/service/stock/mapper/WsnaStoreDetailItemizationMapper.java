package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreDetailItemizationDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreDetailItemizationDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaStoreDetailItemizationMapper {

    PagingResult<SearchRes> selectStoreDetailItemizations(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectStoreDetailItemizations(SearchReq dto);
}
