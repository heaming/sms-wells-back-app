package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsniCenterEngineerCancelMapper {
    int selectEngineerCancelInquiry(String userId);
}
