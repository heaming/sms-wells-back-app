package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarCodeDto;

@Mapper
public interface WsniRegistrationBarCodeMapper {
    WsniRegistrationBarCodeDto.SearchRes selectRegistrationBarCode(
        WsniRegistrationBarCodeDto.SearchReq dto
    );
}
