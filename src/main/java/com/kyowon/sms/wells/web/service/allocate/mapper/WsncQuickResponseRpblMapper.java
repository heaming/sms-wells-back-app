package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncQuickResponseRpblDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncQuickResponseRpblMapper {
    PagingResult<WsncQuickResponseRpblDto.SearchRes> selectQuickResponseRpbl(
        WsncQuickResponseRpblDto.SearchReq dto, PageInfo pageInfo
    );

    List<WsncQuickResponseRpblDto.SearchRes> selectQuickResponseRpbl(
        WsncQuickResponseRpblDto.SearchReq dto
    );

    int updateQuickResponseRpbl(WsncQuickResponseRpblDto.SearchReq dto);
}
