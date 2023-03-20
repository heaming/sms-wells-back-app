package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchWarehouse;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaNormalOutOfStorageMapper {
    PagingResult<SearchRes> selectNormalOutOfStorage(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectNormalOutOfStorage(SearchReq dto);

    List<SearchWarehouse> selectWarehouses(SearchReq dto);
}
