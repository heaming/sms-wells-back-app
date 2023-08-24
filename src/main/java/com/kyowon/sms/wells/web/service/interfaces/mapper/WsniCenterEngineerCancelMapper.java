package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelDto.FindReq;

@Mapper
public interface WsniCenterEngineerCancelMapper {
    int selectEngineerCancelInquiry(FindReq dto);
}
