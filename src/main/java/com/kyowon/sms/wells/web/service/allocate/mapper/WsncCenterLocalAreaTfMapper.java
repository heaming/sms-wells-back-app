package com.kyowon.sms.wells.web.service.allocate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCenterLocalAreaTfDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncCenterLocalAreaTfDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncCenterLocalAreaTfMapper {
    PagingResult<WsncCenterLocalAreaTfDto.SearchRes> selectCenterAreas(
        WsncCenterLocalAreaTfDto.SearchReq dto, PageInfo pageInfo
    );

    int insertCenterArea(WsncCenterLocalAreaTfDvo dvo);
}
