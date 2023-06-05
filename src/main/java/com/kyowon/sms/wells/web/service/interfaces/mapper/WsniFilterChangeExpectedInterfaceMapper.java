package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFilterChangeExpectedInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFilterChangeExpectedInterfaceDto.SearchRes;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsniFilterChangeExpectedInterfaceMapper {
    List<SearchRes> selectFilterChangeExpectedInfos(SearchReq dto);
}
