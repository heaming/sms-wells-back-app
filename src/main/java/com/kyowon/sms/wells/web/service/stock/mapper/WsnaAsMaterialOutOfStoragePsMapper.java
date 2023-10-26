package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaAsMaterialOutOfStoragePsMapper {
    PagingResult selectAsMaterialOutOfStorages(SearchReq dto, PageInfo pageInfo);

    List selectAsMaterialOutOfStorages(SearchReq dto);
}
