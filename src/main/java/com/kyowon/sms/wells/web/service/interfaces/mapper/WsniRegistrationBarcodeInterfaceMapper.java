package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarcodeInterfaceDto;

@Mapper
public interface WsniRegistrationBarcodeInterfaceMapper {
    WsniRegistrationBarcodeInterfaceDto.SearchRes selectRegistrationBarcode(
        WsniRegistrationBarcodeInterfaceDto.SearchReq dto
    );
}
