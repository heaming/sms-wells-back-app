package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageByTaskTypeDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageByTaskTypeDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageByTaskTypeDto.WareField;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageByTaskTypeConvertDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaOutOfStorageByTaskTypeMapper {

    List<HashMap<String, Object>> selectOutOfStorageByTaskType(WsnaOutOfStorageByTaskTypeConvertDvo dvo);

    PagingResult<SearchRes> selectOutOfStorageByTaskType(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectOutOfStorageByTaskType(SearchReq dto);

    List<WareField> selectWareField(SearchReq dto);
}
