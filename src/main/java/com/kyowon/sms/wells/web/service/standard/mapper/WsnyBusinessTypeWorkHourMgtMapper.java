package com.kyowon.sms.wells.web.service.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SearchRes;

@Mapper
public interface WsnyBusinessTypeWorkHourMgtMapper {
    List<SearchRes> selectBusinessTypeWorkHour(SearchReq dto);

}
