package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyBsProcessStatusDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnyBsProcessStatusMapper {

    String selectBsProcessStatusHomeCardCode(WsnyBsProcessStatusDto.SearchReq dto);

    WsnyBsProcessStatusDto.SearchRes selectBsProcessStatusForOg(WsnyBsProcessStatusDto.SearchReq dto);

    WsnyBsProcessStatusDto.SearchRes selectBsProcessStatusForEngineer(WsnyBsProcessStatusDto.SearchReq dto);

    WsnyBsProcessStatusDto.SearchRes selectBsProcessStatusForManager(WsnyBsProcessStatusDto.SearchReq dto);
}
