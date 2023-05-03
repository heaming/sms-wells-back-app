package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterFilterShpadrInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterFilterShpadrInterfaceDto.SearchRes;

@Mapper
public interface WsniCenterFilterShpadrInterfaceMapper {
    List<SearchRes> selectFilterShpadrs(SearchReq dto);
}
