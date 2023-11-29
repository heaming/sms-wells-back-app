package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsndBusinessVehiclesOperationJrnlMgtMapper {
    PagingResult<SearchRes> selectBusinessVehiclesOperationJrnl(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectBusinessVehiclesOperationJrnl(SearchReq dto);

}
