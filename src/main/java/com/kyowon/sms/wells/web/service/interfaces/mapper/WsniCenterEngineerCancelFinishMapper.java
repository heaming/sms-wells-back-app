package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelFinishInterfaceDto.EditReq;

@Mapper
public interface WsniCenterEngineerCancelFinishMapper {
    int updateCenterEngineerCancelFinish(EditReq dto);
}
