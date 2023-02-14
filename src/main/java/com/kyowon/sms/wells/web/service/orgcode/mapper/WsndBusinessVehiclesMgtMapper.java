package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsndBusinessVehiclesMgtMapper {
    List<SearchRes> selectBusinessVehicles(SearchReq dto);

    PagingResult<SearchRes> selectBusinessVehicles(
        SearchReq dto, PageInfo pageInfo
    );
}
