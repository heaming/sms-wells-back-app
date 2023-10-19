package com.kyowon.sms.wells.web.service.allocate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductStateDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncExpProductStateMapper {

    PagingResult<SearchRes> selectExpProductState(
        SearchReq dto,
        PageInfo pageInfo
    );

}
