package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarcodeInterfaceDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsniRegistrationBarcodeInterfaceMapper {
    WsniRegistrationBarcodeInterfaceDto.SearchRes selectRegistrationBarcode(WsniRegistrationBarcodeInterfaceDto.SearchReq dto);

}
